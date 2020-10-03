package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateTimeTypeRule extends AbstractColumnRule {

    private final DateTimeFormatter dateTimeFormatter;

    public LocalDateTimeTypeRule(int position, DateTimeFormatter dateTimeFormatter) {
        super(position);
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        try {
            return fileColumn.getText().isEmpty() || fileColumn.getLocalDateTime(dateTimeFormatter) != null;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return true;
    }
}
