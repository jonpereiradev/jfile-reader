package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeBeforeRule extends AbstractColumnRule {

    private final DateTimeFormatter dateTimeFormatter;
    private final LocalDateTime max;
    private final int refColumn;

    public LocalDateTimeBeforeRule(int position, DateTimeFormatter dateTimeFormatter, LocalDateTime max) {
        super(position);
        this.dateTimeFormatter = dateTimeFormatter;
        this.max = max;
        this.refColumn = -1;
    }

    public LocalDateTimeBeforeRule(int position, DateTimeFormatter dateTimeFormatter, int refColumn) {
        super(position);
        this.dateTimeFormatter = dateTimeFormatter;
        this.max = null;
        this.refColumn = refColumn;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        LocalDateTime date = fileColumn.getLocalDateTime(dateTimeFormatter);
        return date.compareTo(getComparingDate()) < 0;
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return fileColumn.getLocalDateTime(dateTimeFormatter) != null && getComparingDate() != null;
    }

    private LocalDateTime getComparingDate() {
        if (refColumn == -1) {
            return max;
        }

        return getLineValue().getColumnValue(refColumn).getLocalDateTime(dateTimeFormatter);
    }
}
