package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

import java.util.regex.Pattern;

public class RegexRule extends AbstractColumnRule {

    private final Pattern regex;

    public RegexRule(int position, Pattern pattern) {
        super(position);
        this.regex = pattern;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return regex.matcher(fileColumn.getText()).matches();
    }
}
