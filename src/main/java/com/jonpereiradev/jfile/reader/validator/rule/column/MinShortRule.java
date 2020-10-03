package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

public class MinShortRule extends AbstractColumnRule {

    private final short min;

    public MinShortRule(int position, short min) {
        super(position);
        this.min = min;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return fileColumn.getShort() >= min;
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return fileColumn.getShort() != null;
    }
}
