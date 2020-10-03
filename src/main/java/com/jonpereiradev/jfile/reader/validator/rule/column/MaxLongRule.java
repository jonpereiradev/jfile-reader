package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

public class MaxLongRule extends AbstractColumnRule {

    private final long max;

    public MaxLongRule(int position, long max) {
        super(position);
        this.max = max;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return fileColumn.getLong() <= max;
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return fileColumn.getLong() != null;
    }
}
