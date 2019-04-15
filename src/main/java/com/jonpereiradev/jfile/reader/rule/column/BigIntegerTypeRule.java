package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

public class BigIntegerTypeRule extends AbstractColumnRule {

    public BigIntegerTypeRule(int position) {
        super(position);
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return fileColumn.getText().isEmpty() || fileColumn.getBigInteger() != null;
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return true;
    }
}
