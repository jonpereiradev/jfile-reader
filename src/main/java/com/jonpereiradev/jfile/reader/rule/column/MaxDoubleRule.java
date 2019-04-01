package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

public class MaxDoubleRule extends AbstractColumnRule {

    private final double max;

    public MaxDoubleRule(int position, double max) {
        super(position);
        this.max = max;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return fileColumn.getDouble() <= max;
    }
}
