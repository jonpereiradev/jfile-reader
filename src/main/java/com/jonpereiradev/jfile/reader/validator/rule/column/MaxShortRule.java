package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

public class MaxShortRule extends AbstractColumnRule {

    private final short max;

    public MaxShortRule(int position, short max) {
        super(position);
        this.max = max;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return fileColumn.getShort() <= max;
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return fileColumn.getShort() != null;
    }
}
