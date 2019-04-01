package com.jonpereiradev.jfile.reader;

import com.jonpereiradev.jfile.reader.file.JFileLine;
import com.jonpereiradev.jfile.reader.rule.RuleViolation;
import com.jonpereiradev.jfile.reader.stream.StreamReader;
import com.jonpereiradev.jfile.reader.validation.JFileValidatorEngine;
import com.jonpereiradev.jfile.reader.validation.ReportValidation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

final class JFileReaderImpl implements JFileReader {

    private final JFileReaderContext context;
    private final StreamReader streamReader;

    private JFileReaderIterator iterator;

    JFileReaderImpl(JFileReaderContext context) {
        this.context = context;
        this.streamReader = context.getStreamReader();
    }

    @Override
    public JFileReaderIterator iterator() {
        if (iterator == null) {
            iterator = new JFileReaderIteratorImpl(context, streamReader);
        }

        return iterator;
    }

    @Override
    public ReportValidation validate() {
        Objects.requireNonNull(context.getRuleConfiguration(), "No rule configuration provided.");

        if (iterator != null) {
            throw new IllegalStateException("Iterator already initialized. You can't use this method after called iterator().");
        }

        List<JFileLine> lines = new ArrayList<>();
        ReportValidation report = ReportValidation.defaultReport();

        iterator().forEachRemaining(line -> {
            report.put(line.getRow(), validateLine(line));
            lines.add(line);
        });

        iterator = new InMemoryJFileReaderIterator(lines);

        return report;
    }

    @Override
    public ReportValidation validate(JFileLine line) {
        ReportValidation report = ReportValidation.defaultReport();
        report.put(line.getRow(), validateLine(line));
        return report;
    }

    private List<RuleViolation> validateLine(JFileLine line) {
        Objects.requireNonNull(context.getRuleConfiguration(), "No rule configuration provided.");

        if (line == null) {
            throw new IllegalStateException("No line selected in the iterator.");
        }

        return JFileValidatorEngine.defaultEngine(context).validate(line);
    }

    @Override
    public <T> T parse(JFileLine line, Class<T> clazz) {
        return context.getFileLineParser().parse(line, clazz);
    }

    @Override
    public void close() throws IOException {
        streamReader.close();
    }

    private class InMemoryJFileReaderIterator implements JFileReaderIterator {

        private final List<JFileLine> lines;

        private int index = 0;

        private InMemoryJFileReaderIterator(List<JFileLine> lines) {
            this.lines = lines;
        }

        @Override
        public boolean hasNext() {
            return index < lines.size();
        }

        @Override
        public JFileLine next() {
            return lines.get(index++);
        }
    }
}
