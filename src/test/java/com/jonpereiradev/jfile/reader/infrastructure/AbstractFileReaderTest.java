package com.jonpereiradev.jfile.reader.infrastructure;

import com.github.javafaker.Faker;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class AbstractFileReaderTest {

    protected static final char SEPARATOR = ';';

    protected static Path createFileWithContent(String content) throws IOException {
        Path path = Files.createTempFile("tmp", "1");

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.append(content);
        }

        return path;
    }

    protected static Path createFakeTempFile(int numberOfLines) throws IOException {
        Faker faker = new Faker();
        Path path = Files.createTempFile("tmp", ".csv");

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (int i = 1; i <= numberOfLines; i++) {
                StringBuilder lineBuilder = new StringBuilder();

                lineBuilder.append(faker.name().fullName()).append(SEPARATOR);
                lineBuilder.append(faker.company().name()).append(SEPARATOR);
                lineBuilder.append(faker.number().randomDigit()).append(SEPARATOR);
                lineBuilder.append(faker.gameOfThrones().character()).append(SEPARATOR);
                lineBuilder.append(faker.chuckNorris().fact()).append(SEPARATOR);
                lineBuilder.append(faker.food().measurement());

                writer.append(lineBuilder.toString());
                writer.append('\n');
            }

            writer.flush();
        }

        return path;
    }

}
