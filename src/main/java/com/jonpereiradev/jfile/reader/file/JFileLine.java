package com.jonpereiradev.jfile.reader.file;

import java.util.Collections;
import java.util.Objects;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class JFileLine implements Comparable<JFileLine> {

    private final ConcurrentMap<Integer, JFileColumn> columnsByPosition = new ConcurrentHashMap<>();

    private final int row;
    private final String content;
    private final SortedSet<JFileColumn> columns;

    public JFileLine(int row, String content, SortedSet<JFileColumn> columns) {
        this.row = row;
        this.content = content;
        this.columns = Collections.unmodifiableSortedSet(columns);
    }

    @Override
    public int compareTo(JFileLine o) {
        return Integer.compare(row, o.row);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JFileLine jFileLine = (JFileLine) o;
        return row == jFileLine.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row);
    }

    public JFileColumn getColumn(int position) {
        if (!columnsByPosition.containsKey(position)) {
            columnsByPosition.put(position, getColumns().stream().filter(o -> o.getPosition() == position).findFirst().orElse(null));
        }

        return columnsByPosition.get(position);
    }

    public int getRow() {
        return row;
    }

    public String getContent() {
        return content;
    }

    public SortedSet<JFileColumn> getColumns() {
        return columns;
    }

}
