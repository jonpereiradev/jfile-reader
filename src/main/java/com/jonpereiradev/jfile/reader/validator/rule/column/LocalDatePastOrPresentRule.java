package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDatePastOrPresentRule extends AbstractColumnRule {

    private final DateTimeFormatter dateTimeFormatter;

    public LocalDatePastOrPresentRule(int position, DateTimeFormatter dateTimeFormatter) {
        super(position);
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        LocalDate date = fileColumn.getLocalDate(dateTimeFormatter);
        LocalDate current = LocalDate.now();

        return current.compareTo(date) >= 0;
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return fileColumn.getLocalDate(dateTimeFormatter) != null;
    }
}
