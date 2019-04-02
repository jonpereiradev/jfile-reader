package com.jonpereiradev.jfile.reader.infrastructure;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class AbstractFileReaderTest {

    protected Path createFileWithContent(String content) throws IOException {
        Path path = Files.createTempFile("tmp", "1");

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.append(content);
        }

        return path;
    }

}
