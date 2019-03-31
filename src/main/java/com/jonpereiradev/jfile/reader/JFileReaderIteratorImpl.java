package com.jonpereiradev.jfile.reader;

import com.jonpereiradev.jfile.reader.file.JFileColumn;
import com.jonpereiradev.jfile.reader.file.JFileLine;
import com.jonpereiradev.jfile.reader.parser.FileLineParser;
import com.jonpereiradev.jfile.reader.rules.RuleViolation;
import com.jonpereiradev.jfile.reader.stream.StreamReader;
import com.jonpereiradev.jfile.reader.validation.JFileValidatorEngine;

import java.util.*;

final class JFileReaderIteratorImpl implements JFileReaderIterator {

    private final JFileReaderContext context;
    private final Iterator<String> iterator;
    private final List<JFileLine> lines;
    private final List<RuleViolation> violations;

    private int index;
    private boolean cached;

    JFileReaderIteratorImpl(JFileReaderContext context, StreamReader streamReader) {
        this.context = context;
        this.iterator = streamReader.iterator();
        this.lines = new ArrayList<>();
        this.violations = new ArrayList<>();
    }

    @Override
    public boolean hasNext() {
        if (cached) {
            return index < lines.size();
        }

        return iterator.hasNext();
    }

    @Override
    public JFileLine next() {
        if (cached) {
            return lines.get(index++);
        }

        String content = iterator.next();
        String[] columns = context.getPattern().split(content);

        SortedSet<JFileColumn> fileColumns = new TreeSet<>();

        for (int i = 0; i < columns.length; i++) {
            fileColumns.add(new JFileColumn(context, i + 1, columns[i]));
        }

        JFileLine line = new JFileLine(lines.size() + 1, content, fileColumns);
        lines.add(line);

        return line;
    }

    @Override
    public <T> T next(Class<T> clazz) {
        JFileLine fileLine = next();
        FileLineParser fileLineParser = context.getFileLineParser();

        return fileLineParser.parse(fileLine, clazz);
    }

    @Override
    public List<RuleViolation> validate() {
        Objects.requireNonNull(context.getRuleConfiguration(), "No rule configuration provided.");

        if (!cached) {
            JFileValidatorEngine engine = JFileValidatorEngine.defaultEngine(context);

            forEachRemaining(line -> {
                List<RuleViolation> currentViolations = engine.validate(line);

                if (!currentViolations.isEmpty()) {
                    violations.addAll(currentViolations);
                }
            });

            cached = true;
        }

        return violations;
    }
}
