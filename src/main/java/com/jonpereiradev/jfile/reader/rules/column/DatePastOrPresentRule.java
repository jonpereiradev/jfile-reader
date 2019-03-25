package com.jonpereiradev.jfile.reader.rules.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class DatePastOrPresentRule extends AbstractColumnRule {

    private final DateFormat dateFormat;

    public DatePastOrPresentRule(int position, DateFormat dateFormat) {
        super(position);
        this.dateFormat = dateFormat;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        try {
            Date date = fileColumn.getDate(dateFormat);
            Date current = Calendar.getInstance().getTime();

            return current.compareTo(date) >= 0;
        } catch (ParseException e) {
            return true;
        }
    }
}
