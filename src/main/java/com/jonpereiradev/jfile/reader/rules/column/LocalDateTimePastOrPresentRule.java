package com.jonpereiradev.jfile.reader.rules.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimePastOrPresentRule extends AbstractColumnRule {

    private final DateTimeFormatter dateFormat;

    public LocalDateTimePastOrPresentRule(int position, DateTimeFormatter dateFormat) {
        super(position);
        this.dateFormat = dateFormat;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        LocalDateTime date = fileColumn.getLocalDateTime(dateFormat);
        LocalDateTime current = LocalDateTime.now();

        return current.compareTo(date) >= 0;
    }
}
