package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

import java.text.DateFormat;
import java.util.Date;

public class DateAfterRule extends AbstractColumnRule {

    private final DateFormat dateFormat;
    private final Date min;
    private final int columnPosition;

    public DateAfterRule(int position, DateFormat dateFormat, Date min) {
        super(position);
        this.dateFormat = dateFormat;
        this.min = min;
        this.columnPosition = -1;
    }

    public DateAfterRule(int position, DateFormat dateFormat, int columnPosition) {
        super(position);
        this.dateFormat = dateFormat;
        this.min = null;
        this.columnPosition = columnPosition;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        Date date = fileColumn.getDate(dateFormat);
        return date.compareTo(getComparingDate()) > 0;
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return fileColumn.getDate(dateFormat) != null && getComparingDate() != null;
    }

    private Date getComparingDate() {
        if (columnPosition == -1) {
            return min;
        }

        return getLineValue().getColumnValue(columnPosition).getDate(dateFormat);
    }
}
