package com.jonpereiradev.jfile.reader.rules.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

public class BooleanTypeRule extends AbstractColumnRule {

    public BooleanTypeRule(int position) {
        super(position);
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return fileColumn.getBoolean() != null;
    }
}
