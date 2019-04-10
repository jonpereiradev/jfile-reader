package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

public class IntegerTypeRule extends AbstractColumnRule {

    public IntegerTypeRule(int position) {
        super(position);
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        try {
            return fileColumn.getText().isEmpty() || fileColumn.getInt() != null;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return true;
    }
}
