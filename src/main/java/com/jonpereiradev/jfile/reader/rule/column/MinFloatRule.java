package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

public class MinFloatRule extends AbstractColumnRule {

    private final float min;

    public MinFloatRule(int position, float min) {
        super(position);
        this.min = min;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return fileColumn.getFloat() >= min;
    }
}
