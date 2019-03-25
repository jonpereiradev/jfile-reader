package com.jonpereiradev.jfile.reader.rules.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateFutureRule extends AbstractColumnRule {

    private final DateTimeFormatter formatter;

    public LocalDateFutureRule(int position, DateTimeFormatter formatter) {
        super(position);
        this.formatter = formatter;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        LocalDate date = fileColumn.getLocalDate(formatter);
        LocalDate current = LocalDate.now();

        return current.compareTo(date) < 0;
    }
}
