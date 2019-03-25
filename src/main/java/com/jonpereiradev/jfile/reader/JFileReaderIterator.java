package com.jonpereiradev.jfile.reader;

import com.jonpereiradev.jfile.reader.file.JFileLine;
import com.jonpereiradev.jfile.reader.rules.RuleViolation;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public interface JFileReaderIterator extends Iterator<JFileLine> {

    <T> T next(Class<T> clazz);

    List<RuleViolation> validate();

    default <T> void forEachRemaining(Class<T> clazz, Consumer<T> consumer) {
        Objects.requireNonNull(consumer);

        while (hasNext()) {
            consumer.accept(next(clazz));
        }
    }
}
