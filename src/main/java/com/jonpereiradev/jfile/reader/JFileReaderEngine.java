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

import com.jonpereiradev.jfile.reader.converter.LineValueConverter;
import com.jonpereiradev.jfile.reader.file.ColumnValue;
import com.jonpereiradev.jfile.reader.file.LineValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Pattern;

import static com.jonpereiradev.jfile.reader.file.ColumnValue.newColumnValue;
import static com.jonpereiradev.jfile.reader.file.LineValue.newLineValue;

final class JFileReaderEngine implements JFileReader {

    private final JFileReaderConfig readerConfig;
    private final LineValueConverter lineValueConverter;
    private final BufferedReader bufferedReader;
    private final Iterator<LineValue> iterator;

    private JFileReaderEngine(InputStream inputStream, JFileReaderConfig readerConfig) {
        this.readerConfig = readerConfig;
        this.lineValueConverter = readerConfig.getLineValueConverter();
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream, readerConfig.getCharset()));
        this.iterator = new JFileReaderIterator();
    }

    static JFileReaderEngine newInstance(InputStream inputStream, JFileReaderConfig readerConfig) throws IOException {
        validateInputStream(inputStream);
        return new JFileReaderEngine(inputStream, readerConfig);
    }

    private static void validateInputStream(InputStream inputStream) throws IOException {
        if (inputStream.available() == 0) {
            throw new IOException("InputStream doesn't have bytes to read");
        }
    }

    @Override
    public Iterator<LineValue> iterator() {
        return iterator;
    }

    @Override
    public <T> T converted(Class<T> toClass) {
        JFileReaderIterator iterator = (JFileReaderIterator) iterator();
        LineValue lastLineValue = iterator.lastLineValue;

        if (lastLineValue == null) {
            throw new NoSuchElementException("The iterator has no line value present");
        }

        return convert(lastLineValue, toClass);
    }

    @Override
    public <T> T convert(LineValue lineValue, Class<T> toClass) {
        return lineValueConverter.convert(lineValue, toClass);
    }

    @Override
    public void close() throws IOException {
        bufferedReader.close();
    }

    final class JFileReaderIterator implements Iterator<LineValue> {

        private int lineNumber;
        private String lastLineFromReader;
        private LineValue lastLineValue;

        @Override
        public boolean hasNext() {
            setCurrentLine();
            return lastLineFromReader != null;
        }

        @Override
        public LineValue next() {
            setCurrentLine();
            String contentCurrentLine = getContentCurrentLine();
            lastLineValue = getCurrentLineValue(contentCurrentLine);
            return lastLineValue;
        }

        private LineValue getCurrentLineValue(String contentCurrentLine) {
            Pattern pattern = readerConfig.getPattern();
            String[] columns = pattern.split(contentCurrentLine);
            SortedSet<ColumnValue> columnValues = new TreeSet<>();

            for (int i = 0; i < columns.length; i++) {
                int columnNumber = i + 1;
                ColumnValue columnValue = newColumnValue(readerConfig, columnNumber, columns[i]);
                columnValues.add(columnValue);
            }

            return newLineValue(++lineNumber, contentCurrentLine, columnValues);
        }

        private void setCurrentLine() {
            if (lastLineFromReader == null) {
                try {
                    lastLineFromReader = bufferedReader.readLine();
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }
        }

        private String getContentCurrentLine() {
            String currentLine = lastLineFromReader;
            lastLineFromReader = null;

            if (currentLine == null) {
                throw new NoSuchElementException("No more lines to read.");
            }

            return currentLine;
        }

    }

}
