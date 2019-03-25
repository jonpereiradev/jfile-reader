package com.jonpereiradev.jfile.reader.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.NoSuchElementException;

final class DefaultStreamReader extends StreamReader {

    private BufferedReader reader;

    DefaultStreamReader(InputStream inputStream, Charset charset) {
        super(inputStream, charset);
        this.reader = new BufferedReader(new InputStreamReader(inputStream, charset));
    }

    @Override
    public Iterator<String> iterator() {
        return new DefaultStreamReaderIterator();
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

    private class DefaultStreamReaderIterator implements Iterator<String> {

        private String currentLine;

        @Override
        public boolean hasNext() {
            if (currentLine == null) {
                try {
                    currentLine = reader.readLine();
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }

            return currentLine != null;
        }

        @Override
        public String next() {
            if (currentLine == null) {
                try {
                    currentLine = reader.readLine();
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }

            String line = currentLine;
            currentLine = null;

            if (line == null) {
                throw new NoSuchElementException("No more lines to read.");
            }

            return line;
        }
    }
}
