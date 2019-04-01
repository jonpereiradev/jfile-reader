package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

public class MaxFloatRule extends AbstractColumnRule {

    private final float max;

    public MaxFloatRule(int position, float max) {
        super(position);
        this.max = max;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return fileColumn.getFloat() <= max;
    }
}
