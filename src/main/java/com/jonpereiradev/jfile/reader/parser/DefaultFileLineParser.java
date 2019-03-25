package com.jonpereiradev.jfile.reader.parser;

import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;
import com.jonpereiradev.jfile.reader.file.JFileLine;

import java.util.Map;

public class DefaultFileLineParser implements FileLineParser {

    private final ReaderConfiguration configuration;

    public DefaultFileLineParser(ReaderConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T parse(JFileLine fileLine, Class<T> clazz) {
        T object = newInstance(clazz);
        ObjectReader objectReader = new ObjectReader();
        Map<Integer, GetterSetterPair> pair = objectReader.read(clazz);

        ObjectWriter objectWriter = new ObjectWriter(configuration);

        fileLine.getColumns().forEach(o -> {
            if (pair.containsKey(o.getPosition())) {
                objectWriter.write(object, o, pair.get(o.getPosition()));
            }
        });

        return object;
    }

    private <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
