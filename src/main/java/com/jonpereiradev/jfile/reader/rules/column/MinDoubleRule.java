package com.jonpereiradev.jfile.reader.rules.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

public class MinDoubleRule extends AbstractColumnRule {

    private final double min;

    public MinDoubleRule(int position, double min) {
        super(position);
        this.min = min;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return fileColumn.getDouble() >= min;
    }
}
