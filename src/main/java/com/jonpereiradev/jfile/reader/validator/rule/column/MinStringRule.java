package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

public class MinStringRule extends AbstractColumnRule {

    private final int min;

    public MinStringRule(int position, int min) {
        super(position);
        this.min = min;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return fileColumn.getText().trim().length() >= min;
    }
}
