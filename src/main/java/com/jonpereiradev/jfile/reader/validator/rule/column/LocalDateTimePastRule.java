package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimePastRule extends AbstractColumnRule {

    private final DateTimeFormatter dateTimeFormatter;

    public LocalDateTimePastRule(int position, DateTimeFormatter dateTimeFormatter) {
        super(position);
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        LocalDateTime date = fileColumn.getLocalDateTime(dateTimeFormatter);
        LocalDateTime current = LocalDateTime.now();

        return current.compareTo(date) > 0;
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return fileColumn.getLocalDateTime(dateTimeFormatter) != null;
    }
}
