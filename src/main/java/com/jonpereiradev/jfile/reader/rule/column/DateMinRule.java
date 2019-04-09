package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.text.DateFormat;
import java.util.Date;

public class DateMinRule extends AbstractColumnRule {

    private final DateFormat dateFormat;
    private final Date min;

    public DateMinRule(int position, DateFormat dateFormat, Date min) {
        super(position);
        this.dateFormat = dateFormat;
        this.min = min;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        Date date = fileColumn.getDate(dateFormat);
        return date.compareTo(min) >= 0;
    }
}
