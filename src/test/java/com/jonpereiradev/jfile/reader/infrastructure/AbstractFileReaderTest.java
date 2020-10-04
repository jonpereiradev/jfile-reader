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
