package com.jonpereiradev.jfile.reader.validator.rule.line;

import com.jonpereiradev.jfile.reader.file.LineValue;

public class LineColumnSizeRule implements LineRule {

    private final int size;

    public LineColumnSizeRule(int size) {
        this.size = size;
    }

    public boolean isValid(LineValue lineValue) {
        return lineValue.getColumns().size() == size;
    }
}
