package com.jonpereiradev.jfile.reader;

import com.jonpereiradev.jfile.reader.file.JFileLine;
import com.jonpereiradev.jfile.reader.rule.RuleViolation;
import com.jonpereiradev.jfile.reader.stream.StreamReader;
import com.jonpereiradev.jfile.reader.validation.JFileValidatorEngine;
import com.jonpereiradev.jfile.reader.validation.Report;

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
    public Report validate() {
        Objects.requireNonNull(context.getRuleConfiguration(), "No rule configuration provided.");

        if (iterator != null) {
            throw new IllegalStateException("Iterator already initialized. You can't use this method after called iterator().");
        }

        List<JFileLine> lines = new ArrayList<>();
        Report report = Report.defaultReport();
        int maxViolationSize = context.getReaderConfiguration().getMaxViolationSize();
        JFileReaderIterator iterator = iterator();

        while (iterator.hasNext()) {
            JFileLine line = iterator.next();
            List<RuleViolation> violations = validateLine(line);

            report.put(line.getRow(), violations);
            lines.add(line);

            if (maxViolationSize > 0 && report.getViolations().size() > maxViolationSize) {
                break;
            }
        }

        this.iterator = new InMemoryJFileReaderIterator(lines);

        return report;
    }

    @Override
    public Report validate(JFileLine line) {
        Report report = Report.defaultReport();
        List<RuleViolation> violations = validateLine(line);

        report.put(line.getRow(), violations);

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
    public <T> T parse(JFileLine line, Class<T> toClass) {
        return context.getFileLineParser().parse(line, toClass);
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
