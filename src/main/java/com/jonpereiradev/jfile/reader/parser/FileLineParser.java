package com.jonpereiradev.jfile.reader.parser;

import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;
import com.jonpereiradev.jfile.reader.file.JFileLine;

public interface FileLineParser {

    static FileLineParser defaultParser(ReaderConfiguration configuration) {
        return new DefaultFileLineParser(configuration);
    }

    <T> T parse(JFileLine fileLine, Class<T> clazz);

}
