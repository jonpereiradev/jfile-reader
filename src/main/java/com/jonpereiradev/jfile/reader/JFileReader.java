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
package com.jonpereiradev.jfile.reader;

import com.jonpereiradev.jfile.reader.file.LineValue;
import com.jonpereiradev.jfile.reader.validator.JFileValidator;
import com.jonpereiradev.jfile.reader.validator.ValidationReport;

import java.io.Closeable;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Point of access for file reading and validation.
 */
public interface JFileReader extends Iterable<LineValue>, Closeable {

    /**
     * Creates an iterator to read the file.
     *
     * @return returns a new iterator to read the file.
     */
    @Override
    Iterator<LineValue> iterator();

    /**
     * Parses the current line to the type of the class.
     *
     * @param toClass the type of object to parse the line.
     * @param <T> the object type.
     *
     * @return the line parsed into the object.
     *
     * @throws java.util.NoSuchElementException when no current line is present in the iterator
     */
    <T> T converted(Class<T> toClass);

    /**
     * Parses a line to the type of the class.
     *
     * @param lineValue the line that will be transformed in object.
     * @param toClass the type of object to parse the line.
     * @param <T> the object type.
     *
     * @return the line parsed into the object.
     */
    <T> T convert(LineValue lineValue, Class<T> toClass);

    /**
     * Iterates over the lines converting the line to the class type.
     *
     * @param clazz the class type of the object.
     * @param consumer the execution for each iteration.
     * @param <T> the type of the object.
     */
    default <T> void forEach(Class<T> clazz, Consumer<T> consumer) {
        Objects.requireNonNull(consumer);
        Iterator<LineValue> iterator = iterator();

        while (iterator.hasNext()) {
            LineValue line = iterator.next();
            T object = convert(line, clazz);
            consumer.accept(object);
        }
    }

    /**
     * Iterates over the lines converting the line to the class type.
     *
     * @param fileValidator the validator that applies validation to the line.
     * @param consumer the execution for each iteration.
     */
    default void forEach(JFileValidator fileValidator, BiConsumer<LineValue, ValidationReport> consumer) {
        Objects.requireNonNull(consumer);
        Iterator<LineValue> iterator = iterator();

        while (iterator.hasNext()) {
            LineValue lineValue = iterator.next();
            ValidationReport validationReport = fileValidator.validate(lineValue);
            consumer.accept(lineValue, validationReport);
        }
    }

    /**
     * Iterates over the valid lines.
     *
     * @param fileValidator the validator that applies validation to the line.
     * @param consumer the execution for each iteration.
     */
    default void forEachValid(JFileValidator fileValidator, Consumer<LineValue> consumer) {
        Objects.requireNonNull(consumer);
        Iterator<LineValue> iterator = iterator();

        while (iterator.hasNext()) {
            LineValue line = iterator.next();

            if (fileValidator.validate(line).isValid()) {
                consumer.accept(line);
            }
        }
    }

    /**
     * Iterates over the valid lines converting the line to the class type.
     *
     * @param clazz the class type of the object.
     * @param fileValidator the validator that applies validation to the line.
     * @param consumer the execution for each iteration.
     * @param <T> the type of the object.
     */
    default <T> void forEachValid(Class<T> clazz, JFileValidator fileValidator, Consumer<T> consumer) {
        Objects.requireNonNull(consumer);
        Iterator<LineValue> iterator = iterator();

        while (iterator.hasNext()) {
            LineValue line = iterator.next();

            if (fileValidator.validate(line).isValid()) {
                T object = convert(line, clazz);
                consumer.accept(object);
            }
        }
    }

    /**
     * Iterates over the valid lines.
     *
     * @param fileValidator the validator that applies validation to the line.
     * @param consumer the execution for each iteration.
     */
    default void forEachNotValid(JFileValidator fileValidator, Consumer<LineValue> consumer) {
        Objects.requireNonNull(consumer);
        Iterator<LineValue> iterator = iterator();

        while (iterator.hasNext()) {
            LineValue line = iterator.next();

            if (fileValidator.validate(line).isNotValid()) {
                consumer.accept(line);
            }
        }
    }

    default Stream<LineValue> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

}
