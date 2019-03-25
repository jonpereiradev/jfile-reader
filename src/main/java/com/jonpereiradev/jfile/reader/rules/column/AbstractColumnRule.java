package com.jonpereiradev.jfile.reader.rules.column;

abstract class AbstractColumnRule implements ColumnRule {

    private final int position;

    public AbstractColumnRule(int position) {
        this.position = position;
    }

    @Override
    public int getPosition() {
        return position;
    }
}
