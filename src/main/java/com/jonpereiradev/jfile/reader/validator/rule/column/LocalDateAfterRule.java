package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAfterRule extends AbstractColumnRule {

    private final DateTimeFormatter dateTimeFormatter;
    private final LocalDate min;
    private final int refColumn;

    public LocalDateAfterRule(int position, DateTimeFormatter dateTimeFormatter, LocalDate min) {
        super(position);
        this.dateTimeFormatter = dateTimeFormatter;
        this.min = min;
        this.refColumn = -1;
    }

    public LocalDateAfterRule(int position, DateTimeFormatter dateTimeFormatter, int refColumn) {
        super(position);
        this.dateTimeFormatter = dateTimeFormatter;
        this.min = null;
        this.refColumn = refColumn;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        LocalDate date = fileColumn.getLocalDate(dateTimeFormatter);
        return date.compareTo(getComparingDate()) > 0;
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return fileColumn.getLocalDate(dateTimeFormatter) != null && getComparingDate() != null;
    }

    private LocalDate getComparingDate() {
        if (refColumn == -1) {
            return min;
        }

        return getLineValue().getColumnValue(refColumn).getLocalDate(dateTimeFormatter);
    }
}
