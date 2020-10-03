package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

public class MinIntegerRule extends AbstractColumnRule {

    private final int min;

    public MinIntegerRule(int position, int min) {
        super(position);
        this.min = min;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return fileColumn.getInt() >= min;
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return fileColumn.getInt() != null;
    }
}
