package com.jonpereiradev.jfile.reader.rules.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDatePastOrPresentRule extends AbstractColumnRule {

    private final DateTimeFormatter dateTimeFormatter;

    public LocalDatePastOrPresentRule(int position, DateTimeFormatter dateTimeFormatter) {
        super(position);
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        LocalDate date = fileColumn.getLocalDate(dateTimeFormatter);
        LocalDate current = LocalDate.now();

        return current.compareTo(date) >= 0;
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return new LocalDateTypeRule(getPosition(), dateTimeFormatter).isValid(fileColumn);
    }
}
