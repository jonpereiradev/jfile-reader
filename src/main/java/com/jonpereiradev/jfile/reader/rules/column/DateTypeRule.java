package com.jonpereiradev.jfile.reader.rules.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.text.DateFormat;
import java.text.ParseException;

public class DateTypeRule extends AbstractColumnRule {

    private final DateFormat pattern;

    public DateTypeRule(int position, DateFormat pattern) {
        super(position);
        this.pattern = pattern;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        try {
            return fileColumn.getDate(pattern) != null;
        } catch (ParseException e) {
            return false;
        }
    }

}
