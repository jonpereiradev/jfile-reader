package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateBeforeRule extends AbstractColumnRule {

    private final DateTimeFormatter dateTimeFormatter;
    private final LocalDate max;
    private final int refColumn;

    public LocalDateBeforeRule(int position, DateTimeFormatter dateTimeFormatter, LocalDate max) {
        super(position);
        this.dateTimeFormatter = dateTimeFormatter;
        this.max = max;
        this.refColumn = -1;
    }

    public LocalDateBeforeRule(int position, DateTimeFormatter dateTimeFormatter, int refColumn) {
        super(position);
        this.dateTimeFormatter = dateTimeFormatter;
        this.max = null;
        this.refColumn = refColumn;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        LocalDate date = fileColumn.getLocalDate(dateTimeFormatter);
        return date.compareTo(getComparingDate()) < 0;
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return fileColumn.getLocalDate(dateTimeFormatter) != null && getComparingDate() != null;
    }

    private LocalDate getComparingDate() {
        if (refColumn == -1) {
            return max;
        }

        return getLineValue().getColumnValue(refColumn).getLocalDate(dateTimeFormatter);
    }
}
