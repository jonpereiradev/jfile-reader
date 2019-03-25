package com.jonpereiradev.jfile.reader.rules.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

public class ShortTypeRule extends AbstractColumnRule {

    public ShortTypeRule(int position) {
        super(position);
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        try {
            return fileColumn.getShort() != null;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
