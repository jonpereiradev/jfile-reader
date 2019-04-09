package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateMaxRule extends AbstractColumnRule {

    private final DateTimeFormatter dateTimeFormatter;
    private final LocalDate max;

    public LocalDateMaxRule(int position, DateTimeFormatter dateTimeFormatter, LocalDate max) {
        super(position);
        this.dateTimeFormatter = dateTimeFormatter;
        this.max = max;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        LocalDate date = fileColumn.getLocalDate(dateTimeFormatter);
        return date.compareTo(max) <= 0;
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return new LocalDateTypeRule(getPosition(), dateTimeFormatter).isValid(fileColumn);
    }
}
