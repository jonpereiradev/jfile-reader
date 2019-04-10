package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

public class StringTypeRule extends AbstractColumnRule {

    public StringTypeRule(int position) {
        super(position);
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return true;
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return true;
    }
}
