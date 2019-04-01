package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

public class MinStringRule extends AbstractColumnRule {

    private final int min;

    public MinStringRule(int position, int min) {
        super(position);
        this.min = min;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return fileColumn.getText().trim().length() >= min;
    }
}
