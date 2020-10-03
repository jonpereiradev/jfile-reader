package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

import java.math.BigInteger;

public class MinBigIntegerRule extends AbstractColumnRule {

    private final BigInteger min;

    public MinBigIntegerRule(int position, BigInteger min) {
        super(position);
        this.min = min;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return fileColumn.getBigInteger().compareTo(min) >= 0;
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return fileColumn.getBigInteger() != null;
    }
}
