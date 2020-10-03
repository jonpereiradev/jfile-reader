package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

public class MaxStringRule extends AbstractColumnRule {

    private final int max;

    public MaxStringRule(int position, int max) {
        super(position);
        this.max = max;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return fileColumn.getText().trim().length() <= max;
    }
}
