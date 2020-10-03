package com.jonpereiradev.jfile.reader.file;

import java.util.SortedSet;

public interface LineValue extends Comparable<LineValue> {

    static LineValueImpl newLineValue(int lineNumber, String content, SortedSet<ColumnValue> fileColumns) {
        return new LineValueImpl(lineNumber, content, fileColumns);
    }

    int getLineNumber();

    String getContent();

    ColumnValue getColumnValue(int position);

    SortedSet<ColumnValue> getColumns();

}
