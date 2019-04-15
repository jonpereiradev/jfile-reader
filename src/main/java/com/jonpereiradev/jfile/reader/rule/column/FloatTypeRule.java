package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

public class FloatTypeRule extends AbstractColumnRule {

    public FloatTypeRule(int position) {
        super(position);
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return fileColumn.getText().isEmpty() || fileColumn.getFloat() != null;
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return true;
    }
}
