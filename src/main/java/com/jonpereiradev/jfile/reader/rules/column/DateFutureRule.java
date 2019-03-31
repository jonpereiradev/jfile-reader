package com.jonpereiradev.jfile.reader.rules.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFutureRule extends AbstractColumnRule {

    private final DateFormat formatter;

    public DateFutureRule(int position, DateFormat formatter) {
        super(position);
        this.formatter = formatter;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        Date date = fileColumn.getDate(formatter);
        Date current = Calendar.getInstance().getTime();

        return current.before(date);
    }
}
