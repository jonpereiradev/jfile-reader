package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

public class MinLongRule extends AbstractColumnRule {

    private final long min;

    public MinLongRule(int position, long min) {
        super(position);
        this.min = min;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return fileColumn.getLong() >= min;
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return fileColumn.getLong() != null;
    }
}
