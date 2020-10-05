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
package com.jonpereiradev.jfile.reader;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.regex.Pattern;


/**
 * <p>Factory to produces {@link JFileReader} and {@link JFileReaderConfig} objects to configure and read files.</p>
 *
 * @author jonpereiradev
 * @see JFileReader
 * @see JFileReaderConfig
 * @since 0.1.0
 */
public final class JFileReaderFactory {

    private JFileReaderFactory() {
        throw new UnsupportedOperationException("Instantiation not supported");
    }

    /**
     * Creates a {@link JFileReader} object configured with file and regex split pattern.
     *
     * @param file the file instance used to create the {@link InputStream}.
     * @param regex the pattern used to split a line into columns.
     *
     * @return a {@link JFileReader} object configured for file reading.
     *
     * @throws IOException if a problem occurs when creating {@link InputStream} for the file.
     */
    public static JFileReader newUtf8JFileReader(File file, String regex) throws IOException {
        return newUtf8JFileReader(file.toPath(), regex);
    }

    /**
     * Creates a {@link JFileReader} object configured with file and configuration.
     *
     * @param file the file instance used to create the {@link InputStream}.
     * @param readerConfig the configuration for reading the file.
     *
     * @return a {@link JFileReader} object configured for file reading.
     *
     * @throws IOException if a problem occurs when creating {@link InputStream} for the file.
     */
    public static JFileReader newJFileReader(File file, JFileReaderConfig readerConfig) throws IOException {
        return newJFileReader(file.toPath(), readerConfig);
    }

    /**
     * Creates a {@link JFileReader} object configured with file and configuration.
     *
     * @param path the path used to create the {@link InputStream}.
     * @param regex the pattern used to split a line into columns.
     *
     * @return a {@link JFileReader} object configured for file reading.
     *
     * @throws IOException if a problem occurs when creating {@link InputStream} for the path.
     */
    public static JFileReader newUtf8JFileReader(Path path, String regex) throws IOException {
        return newUtf8JFileReader(Files.newInputStream(path), regex);
    }

    /**
     * Creates a {@link JFileReader} object configured with file and configuration.
     *
     * @param path the path used to create the {@link InputStream}.
     * @param readerConfig the configuration for reading the file.
     *
     * @return a {@link JFileReader} object configured for file reading.
     *
     * @throws IOException if a problem occurs when creating {@link InputStream} for the path.
     */
    public static JFileReader newJFileReader(Path path, JFileReaderConfig readerConfig) throws IOException {
        return newJFileReader(Files.newInputStream(path), readerConfig);
    }

    /**
     * Creates a {@link JFileReader} object configured with file and configuration.
     *
     * @param inputStream the stream to read the file content.
     * @param regex the pattern used to split a line into columns.
     *
     * @return a {@link JFileReader} object configured for file reading.
     *
     * @throws IOException if a problem occurs when creating {@link InputStream} for the path.
     */
    public static JFileReader newUtf8JFileReader(InputStream inputStream, String regex) throws IOException {
        return newJFileReader(inputStream, newUtf8ReaderConfig(regex));
    }

    /**
     * Creates a {@link JFileReader} object configured with file and configuration.
     *
     * @param inputStream the file instance to creates the stream.
     * @param readerConfig the configuration for reading the file.
     *
     * @return a {@link JFileReader} object configured for file reading.
     *
     * @throws IOException if a problem occurs when validating the {@link InputStream}.
     */
    public static JFileReader newJFileReader(
        InputStream inputStream,
        JFileReaderConfig readerConfig) throws IOException {
        Objects.requireNonNull(inputStream, "InputStream is required");
        Objects.requireNonNull(readerConfig, "JFileReaderConfig is required");
        return JFileReaderEngine.newInstance(inputStream, readerConfig);
    }

    /**
     * Creates a {@link JFileReaderConfig} object configured with UTF-8 encoding.
     *
     * @param regex the pattern used to split a line into columns.
     *
     * @return a {@link JFileReaderConfig} object configured with UTF-8 encoding.
     */
    public static JFileReaderConfig newUtf8ReaderConfig(String regex) {
        return newReaderConfig(regex, StandardCharsets.UTF_8);
    }

    /**
     * Creates a {@link JFileReaderConfig} object configured with regex and charset.
     *
     * @param regex the pattern used to split a line into columns.
     * @param charset the encode of the file content.
     *
     * @return a {@link JFileReaderConfig} object.
     */
    public static JFileReaderConfig newReaderConfig(String regex, Charset charset) {
        return new JFileReaderConfigImpl(Pattern.compile(regex), charset);
    }

}
