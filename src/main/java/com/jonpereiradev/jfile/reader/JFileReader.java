package com.jonpereiradev.jfile.reader;

import com.jonpereiradev.jfile.reader.file.JFileLine;
import com.jonpereiradev.jfile.reader.validation.ReportValidation;

import java.io.Closeable;

/**
 * Point of access for file reading and validation.
 */
public interface JFileReader extends Iterable<JFileLine>, Closeable {

    /**
     * Creates an iterator for read the file line by line.
     *
     * @return iterator for read the file.
     */
    @Override
    JFileReaderIterator iterator();

    /**
     * Validates all lines of the file with the configurated rule.
     *
     * @return all violations of the file.
     */
    ReportValidation validate();

    /**
     * Validates all lines of the file with the configurated rule.
     *
     * @return all violations of the file.
     */
    ReportValidation validate(JFileLine line);

    /**
     * Parses a line to the type of the class.
     *
     * @param line the line that will be transformed in object.
     * @param clazz the type of object to parse the line.
     * @param <T> the object type.
     *
     * @return the line parsed in object.
     */
    <T> T parse(JFileLine line, Class<T> clazz);

}
