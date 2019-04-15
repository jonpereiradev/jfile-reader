package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

public class MinLongRule extends AbstractColumnRule {

    private final long min;

    public MinLongRule(int position, long min) {
        super(position);
        this.min = min;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return fileColumn.getLong() >= min;
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return fileColumn.getLong() != null;
    }
}
