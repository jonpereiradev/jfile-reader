package com.jonpereiradev.jfile.reader.rules.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.math.BigInteger;

public class BigIntegerTypeRule extends AbstractColumnRule {

    public BigIntegerTypeRule(int position) {
        super(position);
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        try {
            new BigInteger(fileColumn.getText());
            return fileColumn.getBigInteger() != null;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
