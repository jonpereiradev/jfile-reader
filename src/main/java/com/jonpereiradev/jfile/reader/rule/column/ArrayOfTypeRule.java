package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayOfTypeRule extends AbstractColumnRule {

    private final Pattern pattern;

    public ArrayOfTypeRule(int position, Pattern pattern) {
        super(position);
        this.pattern = pattern;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return true;
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return true;
    }

    public List<JFileColumn> split(JFileColumn fileColumn) {
        String[] values = pattern.split(fileColumn.getText());
        Stream<JFileColumn> stream = Arrays.stream(values).map(o -> new JFileColumn(fileColumn.getContext(), getPosition(), o));
        return stream.collect(Collectors.toList());
    }
}
