package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

public class MaxDoubleRule extends AbstractColumnRule {

    private final double max;

    public MaxDoubleRule(int position, double max) {
        super(position);
        this.max = max;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return fileColumn.getDouble() <= max;
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return fileColumn.getDouble() != null;
    }
}
