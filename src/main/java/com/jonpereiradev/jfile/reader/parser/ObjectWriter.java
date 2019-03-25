package com.jonpereiradev.jfile.reader.parser;


import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;
import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;


final class ObjectWriter {


    private static final ConcurrentMap<Class<?>, Function<JFileColumn, ?>> MAPPER = new ConcurrentHashMap<>();

    static {
        MAPPER.putIfAbsent(String.class, JFileColumn::getText);
        MAPPER.putIfAbsent(short.class, JFileColumn::getShort);
        MAPPER.putIfAbsent(Short.class, JFileColumn::getShort);
        MAPPER.putIfAbsent(int.class, JFileColumn::getInt);
        MAPPER.putIfAbsent(Integer.class, JFileColumn::getInt);
        MAPPER.putIfAbsent(long.class, JFileColumn::getLong);
        MAPPER.putIfAbsent(Long.class, JFileColumn::getLong);
        MAPPER.putIfAbsent(boolean.class, JFileColumn::getBoolean);
        MAPPER.putIfAbsent(Boolean.class, JFileColumn::getBoolean);
        MAPPER.putIfAbsent(float.class, JFileColumn::getFloat);
        MAPPER.putIfAbsent(Float.class, JFileColumn::getFloat);
        MAPPER.putIfAbsent(double.class, JFileColumn::getDouble);
        MAPPER.putIfAbsent(Double.class, JFileColumn::getDouble);
    }

    private final ReaderConfiguration configuration;

    ObjectWriter(ReaderConfiguration configuration) {
        this.configuration = configuration;
    }

    public void write(Object instance, JFileColumn fileColumn, GetterSetterPair getterSetterPair) {
        try {
            writeOnInstance(instance, fileColumn, getterSetterPair);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeOnInstance(Object instance, JFileColumn fileColumn, GetterSetterPair getterSetterPair)
        throws IllegalAccessException, InvocationTargetException {
        if (getterSetterPair.hasGetterAndSetter()) {
            Class<?> parameterType = getterSetterPair.getSetter().getParameterTypes()[0];
            Object newObject = createObject(fileColumn, getterSetterPair.getField(), parameterType);

            getterSetterPair.getSetter().invoke(instance, newObject);
        } else if (getterSetterPair.getGetter() != null) {
            Field field = getterSetterPair.getField();
            Object newObject = createObject(fileColumn, field, getterSetterPair.getGetter().getReturnType());

            field.setAccessible(true);
            field.set(instance, newObject);
            field.setAccessible(false);
        }
    }

    private Object createObject(JFileColumn fileColumn, Field field, Class<?> clazz) {
        if (clazz == BigDecimal.class) {
            return createBigDecimalObject(fileColumn, field);
        }

        if (clazz == LocalDate.class) {
            return createLocalDateObject(fileColumn, field);
        }

        if (clazz == LocalDateTime.class) {
            return createLocalDateTimeObject(fileColumn, field);
        }

        return MAPPER.get(clazz).apply(fileColumn);
    }

    private BigDecimal createBigDecimalObject(JFileColumn fileColumn, Field field) {
        DecimalFormat numberFormatter = configuration.getBigDecimalFormatter();

        if (field.isAnnotationPresent(DecimalFormatter.class)) {
            DecimalFormatter annotation = field.getAnnotation(DecimalFormatter.class);
            DecimalFormat format = new DecimalFormat(annotation.value());
            DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();

            symbols.setDecimalSeparator(annotation.decimalSeparator());
            symbols.setGroupingSeparator(annotation.groupingSeparator());

            format.setDecimalFormatSymbols(symbols);
            format.setParseBigDecimal(true);

            numberFormatter = format;
        }

        return fileColumn.getBigDecimal(numberFormatter);
    }

    private LocalDate createLocalDateObject(JFileColumn fileColumn, Field field) {
        java.time.format.DateTimeFormatter formatter = configuration.getLocalDateFormatter();

        if (field.isAnnotationPresent(DateTimeFormatter.class)) {
            DateTimeFormatter annotation = field.getAnnotation(DateTimeFormatter.class);
            formatter = java.time.format.DateTimeFormatter.ofPattern(annotation.value());
        }

        return fileColumn.getLocalDate(formatter);
    }

    private LocalDateTime createLocalDateTimeObject(JFileColumn fileColumn, Field field) {
        java.time.format.DateTimeFormatter formatter = configuration.getLocalDateTimeFormatter();

        if (field.isAnnotationPresent(DateTimeFormatter.class)) {
            DateTimeFormatter annotation = field.getAnnotation(DateTimeFormatter.class);
            formatter = java.time.format.DateTimeFormatter.ofPattern(annotation.value());
        }

        return fileColumn.getLocalDateTime(formatter);
    }
}
