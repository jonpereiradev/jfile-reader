package com.jonpereiradev.jfile.reader.rules.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

public class FloatTypeRule extends AbstractColumnRule {

    public FloatTypeRule(int position) {
        super(position);
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        try {
            return fileColumn.getFloat() != null;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
