package com.jonpereiradev.jfile.reader.validator;

import com.jonpereiradev.jfile.reader.JFileReader;
import com.jonpereiradev.jfile.reader.file.LineValue;

/**
 * Point of access for file validation.
 */
public interface JFileValidator {

    /**
     * Validates all lines of the file with the configured rule.<br><br>
     *
     * <b>IMPORTANT!</b> This execution will position the Reader in the end of the stream.
     *
     * @param fileReader the reader that will be validated.
     *
     * @return all violations of the file.
     */
    ValidationReport validate(JFileReader fileReader);

    /**
     * Validates all lines of the file with the configured rule.
     *
     * @param line the object that will be validated.
     *
     * @return all violations of the file.
     */
    ValidationReport validate(LineValue line);

}
