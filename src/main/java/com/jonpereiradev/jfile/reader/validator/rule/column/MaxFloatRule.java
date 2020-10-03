package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

public class MaxFloatRule extends AbstractColumnRule {

    private final float max;

    public MaxFloatRule(int position, float max) {
        super(position);
        this.max = max;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return fileColumn.getFloat() <= max;
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return fileColumn.getFloat() != null;
    }
}
