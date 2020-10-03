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
    private final LineValueConverter fileLineParser;
    private final BufferedReader bufferedReader;
    private final Iterator<LineValue> iterator;

    JFileReaderEngine(InputStream inputStream, JFileReaderConfig readerConfig) {
        this.readerConfig = readerConfig;
        this.fileLineParser = readerConfig.getLineValueConverter();
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream, readerConfig.getCharset()));
        this.iterator = new JFileReaderIterator();
    }

    @Override
    public Iterator<LineValue> iterator() {
        return iterator;
    }

    @Override
    public <T> T parse(LineValue line, Class<T> toClass) {
        return fileLineParser.convert(line, toClass);
    }

    @Override
    public void close() throws IOException {
        bufferedReader.close();
    }

    final class JFileReaderIterator implements Iterator<LineValue> {

        private int lineNumber;
        private String lastLineFromReader;

        @Override
        public boolean hasNext() {
            setCurrentLine();
            return lastLineFromReader != null;
        }

        @Override
        public LineValue next() {
            setCurrentLine();

            String contentCurrentLine = getContentCurrentLine();
            Pattern pattern = readerConfig.getPattern();
            String[] columns = pattern.split(contentCurrentLine);
            SortedSet<ColumnValue> fileColumns = new TreeSet<>();

            for (int i = 0; i < columns.length; i++) {
                int position = i + 1;
                ColumnValue columnValue = newColumnValue(readerConfig, position, columns[i]);
                fileColumns.add(columnValue);
            }

            return newLineValue(++lineNumber, contentCurrentLine, fileColumns);
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
