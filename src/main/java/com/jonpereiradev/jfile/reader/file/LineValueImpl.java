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
    private final SortedSet<ColumnValue> columns;

    LineValueImpl(int lineNumber, String content, SortedSet<ColumnValue> columns) {
        this.lineNumber = lineNumber;
        this.content = content;
        this.columns = Collections.unmodifiableSortedSet(columns);
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
    public ColumnValue getColumnValue(int position) {
        if (!columnsByPosition.containsKey(position)) {
            Stream<ColumnValue> stream = getColumns().stream().filter(o -> o.getPosition() == position);
            ColumnValue column = stream.findFirst().orElseThrow(() -> new IllegalArgumentException(POSITION_ERROR));
            columnsByPosition.put(position, column);
        }

        return columnsByPosition.get(position);
    }

    @Override
    public SortedSet<ColumnValue> getColumns() {
        return columns;
    }

}
