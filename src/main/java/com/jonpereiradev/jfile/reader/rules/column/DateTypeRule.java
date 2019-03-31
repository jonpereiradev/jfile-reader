package com.jonpereiradev.jfile.reader.rules.column;

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
        return fileColumn.getDate(pattern) != null;
    }

}
