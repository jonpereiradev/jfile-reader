package com.jonpereiradev.jfile.reader.rules.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateTypeRule extends AbstractColumnRule {

    private final DateTimeFormatter pattern;

    public LocalDateTypeRule(int position, DateTimeFormatter pattern) {
        super(position);
        this.pattern = pattern;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        try {
            return fileColumn.getLocalDate(pattern) != null;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}
