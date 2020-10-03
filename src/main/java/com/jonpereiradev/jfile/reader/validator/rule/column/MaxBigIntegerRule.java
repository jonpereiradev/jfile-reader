package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

import java.math.BigInteger;

public class MaxBigIntegerRule extends AbstractColumnRule {

    private final BigInteger max;

    public MaxBigIntegerRule(int position, BigInteger max) {
        super(position);
        this.max = max;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return fileColumn.getBigInteger().compareTo(max) <= 0;
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return fileColumn.getBigInteger() != null;
    }
}
