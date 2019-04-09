package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeMaxRule extends AbstractColumnRule {

    private final DateTimeFormatter dateTimeFormatter;
    private final LocalDateTime max;

    public LocalDateTimeMaxRule(int position, DateTimeFormatter dateTimeFormatter, LocalDateTime max) {
        super(position);
        this.dateTimeFormatter = dateTimeFormatter;
        this.max = max;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        LocalDateTime date = fileColumn.getLocalDateTime(dateTimeFormatter);
        return date.compareTo(max) <= 0;
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return new LocalDateTypeRule(getPosition(), dateTimeFormatter).isValid(fileColumn);
    }
}
