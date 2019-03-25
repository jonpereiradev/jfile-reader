package com.jonpereiradev.jfile.reader.rules.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

public class IntegerTypeRule extends AbstractColumnRule {

    public IntegerTypeRule(int position) {
        super(position);
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        try {
            return fileColumn.getInt() != null;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
