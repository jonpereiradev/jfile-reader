/*
 * MIT License
 *
 * Copyright (c) 2020 Jonathan de Almeida Pereira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ArrayOfTypeRule extends AbstractColumnRule {

    private final Pattern pattern;

    public ArrayOfTypeRule(int columnNumber, Pattern pattern) {
        super(columnNumber);
        this.pattern = pattern;
    }

    @Override
    public boolean isValid(ColumnValue columnValue) {
        return true;
    }

    @Override
    public boolean canValidate(ColumnValue columnValue) {
        return true;
    }

    public List<ColumnValue> split(ColumnValue columnValue) {
        String[] values = pattern.split(columnValue.getText());

        return Arrays
            .stream(values)
            .map(columnContent -> getColumnValue(columnValue, columnContent))
            .collect(Collectors.toList());
    }

    private ColumnValue getColumnValue(ColumnValue columnValue, String columnContent) {
        return ColumnValue.newColumnValue(columnValue.getPatternConfig(), getColumnNumber(), columnContent);
    }

}
