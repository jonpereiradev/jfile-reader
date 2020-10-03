package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFutureRule extends AbstractColumnRule {

    private final DateFormat dateFormat;

    public DateFutureRule(int position, DateFormat dateFormat) {
        super(position);
        this.dateFormat = dateFormat;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        Date date = fileColumn.getDate(dateFormat);
        Date current = Calendar.getInstance().getTime();

        return current.before(date);
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return fileColumn.getDate(dateFormat) != null;
    }
}
