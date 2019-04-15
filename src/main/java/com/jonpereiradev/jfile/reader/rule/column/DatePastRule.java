package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePastRule extends AbstractColumnRule {

    private final DateFormat dateFormat;

    public DatePastRule(int position, DateFormat dateFormat) {
        super(position);
        this.dateFormat = dateFormat;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        Date date = fileColumn.getDate(dateFormat);
        Date current = Calendar.getInstance().getTime();

        return current.after(date);
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return fileColumn.getDate(dateFormat) != null;
    }
}
