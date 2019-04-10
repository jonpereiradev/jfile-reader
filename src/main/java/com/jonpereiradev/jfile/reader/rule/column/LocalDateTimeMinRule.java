package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeMinRule extends AbstractColumnRule {

    private final DateTimeFormatter dateTimeFormatter;
    private final LocalDateTime min;

    public LocalDateTimeMinRule(int position, DateTimeFormatter dateTimeFormatter, LocalDateTime min) {
        super(position);
        this.dateTimeFormatter = dateTimeFormatter;
        this.min = min;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        LocalDateTime date = fileColumn.getLocalDateTime(dateTimeFormatter);
        return date.compareTo(min) >= 0;
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return new LocalDateTypeRule(getPosition(), dateTimeFormatter).isValid(fileColumn);
    }
}