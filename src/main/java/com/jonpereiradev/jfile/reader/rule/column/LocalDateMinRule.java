package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateMinRule extends AbstractColumnRule {

    private final DateTimeFormatter dateTimeFormatter;
    private final LocalDate min;

    public LocalDateMinRule(int position, DateTimeFormatter dateTimeFormatter, LocalDate min) {
        super(position);
        this.dateTimeFormatter = dateTimeFormatter;
        this.min = min;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        LocalDate date = fileColumn.getLocalDate(dateTimeFormatter);
        return date.compareTo(min) >= 0;
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return new LocalDateTypeRule(getPosition(), dateTimeFormatter).isValid(fileColumn);
    }
}
