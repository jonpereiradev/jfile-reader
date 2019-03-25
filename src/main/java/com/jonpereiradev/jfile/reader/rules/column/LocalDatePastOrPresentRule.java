package com.jonpereiradev.jfile.reader.rules.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDatePastOrPresentRule extends AbstractColumnRule {

    private final DateTimeFormatter dateFormat;

    public LocalDatePastOrPresentRule(int position, DateTimeFormatter dateFormat) {
        super(position);
        this.dateFormat = dateFormat;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        LocalDate date = fileColumn.getLocalDate(dateFormat);
        LocalDate current = LocalDate.now();

        return current.compareTo(date) >= 0;
    }
}
