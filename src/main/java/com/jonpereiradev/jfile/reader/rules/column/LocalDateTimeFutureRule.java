package com.jonpereiradev.jfile.reader.rules.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeFutureRule extends AbstractColumnRule {

    private final DateTimeFormatter dateTimeFormatter;

    public LocalDateTimeFutureRule(int position, DateTimeFormatter dateTimeFormatter) {
        super(position);
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        LocalDateTime date = fileColumn.getLocalDateTime(dateTimeFormatter);
        LocalDateTime current = LocalDateTime.now();

        return current.compareTo(date) < 0;
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return new LocalDateTimeTypeRule(getPosition(), dateTimeFormatter).isValid(fileColumn);
    }
}
