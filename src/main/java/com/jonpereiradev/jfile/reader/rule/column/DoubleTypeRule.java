package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

public class DoubleTypeRule extends AbstractColumnRule {

    public DoubleTypeRule(int position) {
        super(position);
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return fileColumn.getText().isEmpty() || fileColumn.getDouble() != null;
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return true;
    }
}
