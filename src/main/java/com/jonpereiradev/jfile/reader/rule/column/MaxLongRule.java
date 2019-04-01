package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

public class MaxLongRule extends AbstractColumnRule {

    private final long max;

    public MaxLongRule(int position, long max) {
        super(position);
        this.max = max;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return fileColumn.getLong() <= max;
    }
}
