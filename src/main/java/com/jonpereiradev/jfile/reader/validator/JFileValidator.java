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
     * @param lineValue the object that will be validated.
     *
     * @return all violations of the file.
     */
    ValidationReport validate(LineValue lineValue);

}
