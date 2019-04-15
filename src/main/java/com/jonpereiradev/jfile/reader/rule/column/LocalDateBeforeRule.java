package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

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
    public boolean isValid(JFileColumn fileColumn) {
        LocalDate date = fileColumn.getLocalDate(dateTimeFormatter);
        return date.compareTo(getComparingDate()) < 0;
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return fileColumn.getLocalDate(dateTimeFormatter) != null && getComparingDate() != null;
    }

    private LocalDate getComparingDate() {
        if (refColumn == -1) {
            return max;
        }

        return getFileLine().getColumn(refColumn).getLocalDate(dateTimeFormatter);
    }
}
