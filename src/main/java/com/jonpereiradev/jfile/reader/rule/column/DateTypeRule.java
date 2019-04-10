package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.text.DateFormat;

public class DateTypeRule extends AbstractColumnRule {

    private final DateFormat pattern;

    public DateTypeRule(int position, DateFormat pattern) {
        super(position);
        this.pattern = pattern;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return fileColumn.getText().isEmpty() || fileColumn.getDate(pattern) != null;
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return true;
    }
}
