package com.jonpereiradev.jfile.reader.rules.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

public abstract class AbstractColumnRule implements ColumnRule {

    private final int position;
    private final RefRule refRule;

    public AbstractColumnRule(int position) {
        this.position = position;
        this.refRule = new RefRuleImpl(-1, position);
    }

    public AbstractColumnRule(int position, RefRule refRule) {
        this.position = position;
        this.refRule = refRule;
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return refRule.canValidate(fileColumn);
    }

    @Override
    public int getPosition() {
        return position;
    }
}
