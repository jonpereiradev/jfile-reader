package com.jonpereiradev.jfile.reader.rules.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class DatePastRule extends AbstractColumnRule {

    private final DateFormat formatter;

    public DatePastRule(int position, DateFormat formatter) {
        super(position);
        this.formatter = formatter;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        try {
            Date date = fileColumn.getDate(formatter);
            Date current = Calendar.getInstance().getTime();

            return current.after(date);
        } catch (ParseException e) {
            return true;
        }
    }
}
