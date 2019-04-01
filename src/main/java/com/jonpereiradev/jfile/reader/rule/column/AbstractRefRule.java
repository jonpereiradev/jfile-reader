package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

public abstract class AbstractRefRule implements RefRule {

    private final int refPosition;
    private final int position;

    public AbstractRefRule(int refPosition, int position) {
        this.refPosition = refPosition;
        this.position = position;
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return true;
    }

    @Override
    public int getRefPosition() {
        return refPosition;
    }

    @Override
    public int getPosition() {
        return position;
    }
}
