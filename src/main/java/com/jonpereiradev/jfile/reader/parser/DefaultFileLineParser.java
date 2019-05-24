package com.jonpereiradev.jfile.reader.parser;

import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;
import com.jonpereiradev.jfile.reader.file.JFileLine;

import java.util.HashMap;
import java.util.Map;

final class DefaultFileLineParser implements FileLineParser {

    private static final Map<Class<?>, Map<Integer, GetterSetterPair>> CACHE_CLASS_REFLECTIONS = new HashMap<>();

    private final ReaderConfiguration configuration;

    DefaultFileLineParser(ReaderConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T parse(JFileLine fileLine, Class<T> clazz) {
        T object = newInstance(clazz);
        Map<Integer, GetterSetterPair> pair = getGetterSetterPair(clazz);

        ObjectWriter objectWriter = new ObjectWriter(configuration);

        fileLine.getColumns().forEach(o -> {
            if (pair.containsKey(o.getPosition())) {
                objectWriter.write(object, o, pair.get(o.getPosition()));
            }
        });

        return object;
    }

    private <T> Map<Integer, GetterSetterPair> getGetterSetterPair(Class<T> clazz) {
        if (!CACHE_CLASS_REFLECTIONS.containsKey(clazz)) {
            ObjectReader objectReader = new ObjectReader();
            CACHE_CLASS_REFLECTIONS.put(clazz, objectReader.read(clazz));
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
