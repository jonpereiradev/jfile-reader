package com.jonpereiradev.jfile.reader;

import com.jonpereiradev.jfile.reader.rules.RuleViolation;
import com.jonpereiradev.jfile.reader.stream.StreamReader;

import java.io.IOException;
import java.util.List;

final class CsvFileReader implements JFileReader {

    private final JFileReaderContext context;
    private final StreamReader streamReader;

    private JFileReaderIterator iterator;

    CsvFileReader(JFileReaderContext context) {
        this.context = context;
        this.streamReader = context.getStreamReader();
    }

    @Override
    public List<RuleViolation> validate() {
        return iterator().validate();
    }

    @Override
    public JFileReaderIterator iterator() {
        if (iterator == null) {
            iterator = new JFileReaderIteratorImpl(context, streamReader);
        }

        return iterator;
    }

    @Override
    public void close() throws IOException {
        streamReader.close();
    }
}
