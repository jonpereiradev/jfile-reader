package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.math.BigInteger;

public class MinBigIntegerRule extends AbstractColumnRule {

    private final BigInteger min;

    public MinBigIntegerRule(int position, BigInteger min) {
        super(position);
        this.min = min;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return fileColumn.getBigInteger().compareTo(min) >= 0;
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return fileColumn.getBigInteger() != null;
    }
}
