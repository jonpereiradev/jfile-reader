package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

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
    public boolean isValid(ColumnValue columnValue) {
        return true;
    }

    @Override
    public boolean canValidate(ColumnValue columnValue) {
        return true;
    }

    public List<ColumnValue> split(ColumnValue columnValue) {
        String[] values = pattern.split(columnValue.getText());
        Stream<ColumnValue> stream = Arrays.stream(values).map(o -> getColumnValue(columnValue, o));
        return stream.collect(Collectors.toList());
    }

    private ColumnValue getColumnValue(ColumnValue columnValue, String o) {
        return ColumnValue.newColumnValue(columnValue.getPatternConfig(), getPosition(), o);
    }

}
