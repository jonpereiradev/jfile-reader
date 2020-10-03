package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePastOrPresentRule extends AbstractColumnRule {

    private final DateFormat dateFormat;

    public DatePastOrPresentRule(int position, DateFormat dateFormat) {
        super(position);
        this.dateFormat = dateFormat;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        Date date = fileColumn.getDate(dateFormat);
        Date current = Calendar.getInstance().getTime();

        return current.compareTo(date) >= 0;
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return fileColumn.getDate(dateFormat) != null;
    }
}
