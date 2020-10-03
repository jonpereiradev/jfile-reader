package com.jonpereiradev.jfile.reader.converter;

import com.jonpereiradev.jfile.reader.JFileReaderConfig;
import com.jonpereiradev.jfile.reader.file.LineValue;

import java.util.HashMap;
import java.util.Map;

public final class ReflectionLineValueConverter implements LineValueConverter {

    private static final Map<Class<?>, Map<Integer, GetterSetterPair>> CACHE_CLASS_REFLECTIONS = new HashMap<>();

    private final JFileReaderConfig readerConfig;

    public ReflectionLineValueConverter(JFileReaderConfig readerConfig) {
        this.readerConfig = readerConfig;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T convert(LineValue lineValue, Class<T> classType) {
        if (classType.isAssignableFrom(String.class)) {
            return (T) lineValue.getContent();
        }

        if (classType.equals(LineValue.class)) {
            return (T) lineValue;
        }

        return convertLineValueToObject(lineValue, classType);
    }

    private <T> T convertLineValueToObject(LineValue lineValue, Class<T> classType) {
        T object = newInstance(classType);
        Map<Integer, GetterSetterPair> pair = getGetterSetterPair(classType);
        ReflectionObjectWriter reflectionObjectWriter = new ReflectionObjectWriter(readerConfig);

        lineValue.getColumns().forEach(o -> {
            if (pair.containsKey(o.getPosition())) {
                reflectionObjectWriter.write(object, o, pair.get(o.getPosition()));
            }
        });

        return object;
    }

    private <T> Map<Integer, GetterSetterPair> getGetterSetterPair(Class<T> clazz) {
        if (!CACHE_CLASS_REFLECTIONS.containsKey(clazz)) {
            ReflectionObjectReader reflectionObjectReader = new ReflectionObjectReader();
            CACHE_CLASS_REFLECTIONS.put(clazz, reflectionObjectReader.read(clazz));
        }

        return CACHE_CLASS_REFLECTIONS.get(clazz);
    }

    private <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
