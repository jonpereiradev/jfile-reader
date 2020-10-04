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
package com.jonpereiradev.jfile.reader.file;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.SortedSet;
import java.util.stream.Stream;

final class LineValueImpl implements LineValue {

    private static final String POSITION_ERROR = "Position doesn't exists in line.";

    private final Map<Integer, ColumnValue> columnsByPosition = new HashMap<>();

    private final int lineNumber;
    private final String content;
    private final SortedSet<ColumnValue> columnValues;

    LineValueImpl(int lineNumber, String content, SortedSet<ColumnValue> columnValues) {
        this.lineNumber = lineNumber;
        this.content = content;
        this.columnValues = Collections.unmodifiableSortedSet(columnValues);
    }

    @Override
    public int compareTo(LineValue o) {
        return Integer.compare(lineNumber, o.getLineNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineValueImpl lineValue = (LineValueImpl) o;
        return lineNumber == lineValue.lineNumber;
    }

    @Override
    public String toString() {
        return "[lineNumber=" + lineNumber + ", content=" + content + "]";
    }

    @Override
    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public ColumnValue getColumnValue(int columnNumber) {
        if (!columnsByPosition.containsKey(columnNumber)) {
            Stream<ColumnValue> stream = getColumnValues().stream().filter(o -> o.getColumnNumber() == columnNumber);
            ColumnValue column = stream.findFirst().orElseThrow(() -> new IllegalArgumentException(POSITION_ERROR));
            columnsByPosition.put(columnNumber, column);
        }

        return columnsByPosition.get(columnNumber);
    }

    @Override
    public SortedSet<ColumnValue> getColumnValues() {
        return columnValues;
    }

}
