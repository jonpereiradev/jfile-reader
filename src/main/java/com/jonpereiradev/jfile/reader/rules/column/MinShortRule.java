package com.jonpereiradev.jfile.reader.rules.column;

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
}
