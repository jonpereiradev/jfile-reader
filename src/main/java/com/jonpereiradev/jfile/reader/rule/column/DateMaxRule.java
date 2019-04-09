package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.text.DateFormat;
import java.util.Date;

public class DateMaxRule extends AbstractColumnRule {

    private final DateFormat dateFormat;
    private final Date max;

    public DateMaxRule(int position, DateFormat dateFormat, Date max) {
        super(position);
        this.dateFormat = dateFormat;
        this.max = max;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        Date date = fileColumn.getDate(dateFormat);
        return date.compareTo(max) <= 0;
    }
}
