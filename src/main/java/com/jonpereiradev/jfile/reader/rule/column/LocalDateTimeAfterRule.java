package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAfterRule extends AbstractColumnRule {

    private final DateTimeFormatter dateTimeFormatter;
    private final LocalDateTime min;
    private final int refColumn;

    public LocalDateTimeAfterRule(int position, DateTimeFormatter dateTimeFormatter, LocalDateTime min) {
        super(position);
        this.dateTimeFormatter = dateTimeFormatter;
        this.min = min;
        this.refColumn = -1;
    }

    public LocalDateTimeAfterRule(int position, DateTimeFormatter dateTimeFormatter, int refColumn) {
        super(position);
        this.dateTimeFormatter = dateTimeFormatter;
        this.min = null;
        this.refColumn = refColumn;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        LocalDateTime date = fileColumn.getLocalDateTime(dateTimeFormatter);
        return date.compareTo(getComparingDate()) > 0;
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return fileColumn.getLocalDateTime(dateTimeFormatter) != null && getComparingDate() != null;
    }

    private LocalDateTime getComparingDate() {
        if (refColumn == -1) {
            return min;
        }

        return getFileLine().getColumn(refColumn).getLocalDateTime(dateTimeFormatter);
    }
}
