package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

public class MinDoubleRule extends AbstractColumnRule {

    private final double min;

    public MinDoubleRule(int position, double min) {
        super(position);
        this.min = min;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return fileColumn.getDouble() >= min;
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return fileColumn.getDouble() != null;
    }
}
