package com.jonpereiradev.jfile.reader.rules.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

public class MinIntegerRule extends AbstractColumnRule {

    private final int min;

    public MinIntegerRule(int position, int min) {
        super(position);
        this.min = min;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return fileColumn.getInt() >= min;
    }
}
