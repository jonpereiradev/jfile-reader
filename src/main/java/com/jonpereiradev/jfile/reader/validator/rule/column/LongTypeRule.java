package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

public class LongTypeRule extends AbstractColumnRule {

    public LongTypeRule(int position) {
        super(position);
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        try {
            return fileColumn.getText().isEmpty() || fileColumn.getLong() != null;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return true;
    }
}
