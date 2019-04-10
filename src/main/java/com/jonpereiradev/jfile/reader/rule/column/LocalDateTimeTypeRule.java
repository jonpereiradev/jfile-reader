package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateTimeTypeRule extends AbstractColumnRule {

    private final DateTimeFormatter dateTimeFormatter;

    public LocalDateTimeTypeRule(int position, DateTimeFormatter dateTimeFormatter) {
        super(position);
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        try {
            return fileColumn.getText().isEmpty() || fileColumn.getLocalDateTime(dateTimeFormatter) != null;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return true;
    }
}
