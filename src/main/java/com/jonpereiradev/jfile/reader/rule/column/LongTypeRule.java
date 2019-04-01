package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

public class LongTypeRule extends AbstractColumnRule {

    public LongTypeRule(int position) {
        super(position);
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        try {
            return fileColumn.getLong() != null;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
