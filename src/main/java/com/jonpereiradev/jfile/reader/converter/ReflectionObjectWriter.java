/*
 * MIT License
 *
 * Copyright (c) 2020 Jonathan de Almeida Pereira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.jonpereiradev.jfile.reader.converter;


import com.jonpereiradev.jfile.reader.JFileReaderConfig;
import com.jonpereiradev.jfile.reader.file.ColumnValue;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;


final class ReflectionObjectWriter {

    private final JFileReaderConfig readerConfig;

    ReflectionObjectWriter(JFileReaderConfig readerConfig) {
        this.readerConfig = readerConfig;
    }

    void write(Object instance, ColumnValue columnValue, GetterSetterPair getterSetterPair) {
        try {
            writeOnInstance(instance, columnValue, getterSetterPair);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeOnInstance(Object instance, ColumnValue columnValue, GetterSetterPair getterSetterPair)
        throws IllegalAccessException, InvocationTargetException {
        if (getterSetterPair.hasGetterAndSetter()) {
            Class<?> parameterType = getterSetterPair.getSetter().getParameterTypes()[0];
            Object newObject = createObject(columnValue, getterSetterPair.getField(), parameterType);

            getterSetterPair.getSetter().invoke(instance, newObject);
        } else if (getterSetterPair.getGetter() != null) {
            Field field = getterSetterPair.getField();
            Object newObject = createObject(columnValue, field, getterSetterPair.getGetter().getReturnType());

            AccessibleObject.setAccessible(new Field[]{field}, true);
            field.set(instance, newObject);
            AccessibleObject.setAccessible(new Field[]{field}, false);
        }
    }

    private Object createObject(ColumnValue columnValue, Field field, Class<?> classType) {
        if (classType == BigDecimal.class) {
            return createBigDecimalObject(columnValue, field);
        }

        if (classType == LocalDate.class) {
            return createLocalDateObject(columnValue, field);
        }

        if (classType == LocalDateTime.class) {
            return createLocalDateTimeObject(columnValue, field);
        }

        return columnValue.getContent(classType);
    }

    private BigDecimal createBigDecimalObject(ColumnValue columnValue, Field field) {
        DecimalFormat numberFormatter = readerConfig.getBigDecimalFormatter();

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

        return columnValue.getBigDecimal(numberFormatter);
    }

    private LocalDate createLocalDateObject(ColumnValue columnValue, Field field) {
        java.time.format.DateTimeFormatter formatter = readerConfig.getLocalDateFormatter();

        if (field.isAnnotationPresent(DateTimeFormatter.class)) {
            DateTimeFormatter annotation = field.getAnnotation(DateTimeFormatter.class);
            formatter = java.time.format.DateTimeFormatter.ofPattern(annotation.value());
        }

        return columnValue.getLocalDate(formatter);
    }

    private LocalDateTime createLocalDateTimeObject(ColumnValue columnValue, Field field) {
        java.time.format.DateTimeFormatter formatter = readerConfig.getLocalDateTimeFormatter();

        if (field.isAnnotationPresent(DateTimeFormatter.class)) {
            DateTimeFormatter annotation = field.getAnnotation(DateTimeFormatter.class);
            formatter = java.time.format.DateTimeFormatter.ofPattern(annotation.value());
        }

        return columnValue.getLocalDateTime(formatter);
    }

}
