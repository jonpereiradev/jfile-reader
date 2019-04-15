package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateFutureOrPresentRule extends AbstractColumnRule {

    private final DateTimeFormatter dateTimeFormatter;

    public LocalDateFutureOrPresentRule(int position, DateTimeFormatter dateTimeFormatter) {
        super(position);
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        LocalDate date = fileColumn.getLocalDate(dateTimeFormatter);
        LocalDate current = LocalDate.now();

        return current.compareTo(date) <= 0;
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return fileColumn.getLocalDate(dateTimeFormatter) != null;
    }
}
