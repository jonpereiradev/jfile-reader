package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

public class MinFloatRule extends AbstractColumnRule {

    private final float min;

    public MinFloatRule(int position, float min) {
        super(position);
        this.min = min;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return fileColumn.getFloat() >= min;
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return fileColumn.getFloat() != null;
    }
}
