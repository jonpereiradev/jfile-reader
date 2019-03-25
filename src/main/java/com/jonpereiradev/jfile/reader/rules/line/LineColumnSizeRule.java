package com.jonpereiradev.jfile.reader.rules.line;

import com.jonpereiradev.jfile.reader.file.JFileLine;

public class LineColumnSizeRule implements LineRule {

    private final int size;

    public LineColumnSizeRule(int size) {
        this.size = size;
    }

    public boolean isValid(JFileLine fileLine) {
        return fileLine.getColumns().size() == size;
    }
}
