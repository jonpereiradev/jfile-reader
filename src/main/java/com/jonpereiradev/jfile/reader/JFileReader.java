package com.jonpereiradev.jfile.reader;

import com.jonpereiradev.jfile.reader.file.LineValue;
import com.jonpereiradev.jfile.reader.validator.JFileValidator;

import java.io.Closeable;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

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
     * Parses a line to the type of the class.
     *
     * @param line the line that will be transformed in object.
     * @param toClass the type of object to parse the line.
     * @param <T> the object type.
     *
     * @return the line parsed in object.
     */
    <T> T parse(LineValue line, Class<T> toClass);

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
            T object = parse(line, clazz);
            consumer.accept(object);
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
                T object = parse(line, clazz);
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

}
