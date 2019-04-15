package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.text.DateFormat;
import java.util.Date;

public class DateBeforeRule extends AbstractColumnRule {

    private final DateFormat dateFormat;
    private final Date max;
    private final int columnPosition;

    public DateBeforeRule(int position, DateFormat dateFormat, Date max) {
        super(position);
        this.dateFormat = dateFormat;
        this.max = max;
        this.columnPosition = -1;
    }

    public DateBeforeRule(Integer position, DateFormat dateFormat, int columnPosition) {
        super(position);
        this.dateFormat = dateFormat;
        this.max = null;
        this.columnPosition = columnPosition;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        Date date = fileColumn.getDate(dateFormat);
        return date.compareTo(getComparingDate()) < 0;
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return fileColumn.getDate(dateFormat) != null && getComparingDate() != null;
    }

    private Date getComparingDate() {
        if (columnPosition == -1) {
            return max;
        }

        return getFileLine().getColumn(columnPosition).getDate(dateFormat);
    }
}
