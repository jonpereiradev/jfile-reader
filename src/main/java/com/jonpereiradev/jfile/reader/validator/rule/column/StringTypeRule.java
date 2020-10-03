package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

public class StringTypeRule extends AbstractColumnRule {

    public StringTypeRule(int position) {
        super(position);
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return true;
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return true;
    }
}
