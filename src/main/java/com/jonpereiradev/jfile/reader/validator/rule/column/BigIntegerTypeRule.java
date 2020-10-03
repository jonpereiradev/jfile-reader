package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

public class BigIntegerTypeRule extends AbstractColumnRule {

    public BigIntegerTypeRule(int position) {
        super(position);
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return fileColumn.getText().isEmpty() || fileColumn.getBigInteger() != null;
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return true;
    }
}
