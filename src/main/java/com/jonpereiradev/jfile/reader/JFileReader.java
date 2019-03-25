package com.jonpereiradev.jfile.reader;

import com.jonpereiradev.jfile.reader.file.JFileLine;
import com.jonpereiradev.jfile.reader.rules.RuleViolation;

import java.io.Closeable;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public interface JFileReader extends Iterable<JFileLine>, Closeable {

    @Override
    JFileReaderIterator iterator();

    List<RuleViolation> validate();

    default <T> void forEach(Class<T> clazz, Consumer<T> consumer) {
        Objects.requireNonNull(consumer);
        JFileReaderIterator iterator = iterator();

        while (iterator.hasNext()) {
            T object = iterator.next(clazz);
            consumer.accept(object);
        }
    }
}
