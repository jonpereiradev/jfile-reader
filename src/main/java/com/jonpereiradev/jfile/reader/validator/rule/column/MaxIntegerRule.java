package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

public class MaxIntegerRule extends AbstractColumnRule {

    private final int max;

    public MaxIntegerRule(int position, int max) {
        super(position);
        this.max = max;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return fileColumn.getInt() <= max;
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return fileColumn.getInt() != null;
    }
}
