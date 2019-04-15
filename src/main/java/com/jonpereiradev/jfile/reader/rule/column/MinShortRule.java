package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

public class MinShortRule extends AbstractColumnRule {

    private final short min;

    public MinShortRule(int position, short min) {
        super(position);
        this.min = min;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return fileColumn.getShort() >= min;
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return fileColumn.getShort() != null;
    }
}
