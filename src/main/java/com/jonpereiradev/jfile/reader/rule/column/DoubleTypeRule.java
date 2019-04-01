package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

public class DoubleTypeRule extends AbstractColumnRule {

    public DoubleTypeRule(int position) {
        super(position);
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        try {
            return fileColumn.getDouble() != null;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
