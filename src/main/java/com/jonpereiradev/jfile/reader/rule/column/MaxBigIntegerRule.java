package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.math.BigInteger;

public class MaxBigIntegerRule extends AbstractColumnRule {

    private final BigInteger max;

    public MaxBigIntegerRule(int position, BigInteger max) {
        super(position);
        this.max = max;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return fileColumn.getBigInteger().compareTo(max) <= 0;
    }
}
