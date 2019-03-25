package com.jonpereiradev.jfile.reader;

import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public final class JFileReaderFactory {

    private JFileReaderFactory() {
    }

    public static JFileReader newInstance(File file, ReaderConfiguration configuration) {
        return newInstance(file.toPath(), configuration);
    }

    public static JFileReader newInstance(Path path, ReaderConfiguration configuration) {
        try {
            return newInstance(Files.newInputStream(path), configuration);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static JFileReader newInstance(InputStream inputStream, ReaderConfiguration configuration) {
        Objects.requireNonNull(inputStream, "InputStream is required.");
        JFileReaderContext context = new JFileReaderContext(inputStream, configuration);
        return new CsvFileReader(context);
    }

}
