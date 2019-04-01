package com.jonpereiradev.jfile.reader;

import com.jonpereiradev.jfile.reader.file.JFileColumn;
import com.jonpereiradev.jfile.reader.file.JFileLine;
import com.jonpereiradev.jfile.reader.stream.StreamReader;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

final class JFileReaderIteratorImpl implements JFileReaderIterator {

    private final JFileReaderContext context;
    private final Iterator<String> iterator;

    private int row;

    JFileReaderIteratorImpl(JFileReaderContext context, StreamReader streamReader) {
        this.context = context;
        this.iterator = streamReader.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public JFileLine next() {
        String content = iterator.next();
        String[] columns = context.getPattern().split(content);

        SortedSet<JFileColumn> fileColumns = new TreeSet<>();

        for (int i = 0; i < columns.length; i++) {
            fileColumns.add(new JFileColumn(context, i + 1, columns[i]));
        }

        return new JFileLine(++row, content, fileColumns);
    }
}
