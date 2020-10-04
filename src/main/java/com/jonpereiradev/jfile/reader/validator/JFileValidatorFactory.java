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

import com.jonpereiradev.jfile.reader.JFilePatternConfig;

/**
 * Factory to create the {@link JFileValidator} implementation.
 */
public final class JFileValidatorFactory {

    private JFileValidatorFactory() {
        throw new UnsupportedOperationException();
    }

    /**
     * Create a File Validator with the desired parameters.
     *
     * @param validatorConfig the config that tells how to validate the file.
     *
     * @return an instance of JFileReader for the file and config specified.
     */
    public static JFileValidator newJFileValidator(JFileValidatorConfig validatorConfig) {
        return new JFileValidatorEngine(validatorConfig);
    }

    /**
     * Create a File Validator Config to configure the validator engine.
     *
     * @return a new instance of the validator config.
     */
    public static JFileValidatorConfig newValidatorConfig() {
        return new JFileValidatorConfigImpl();
    }

    /**
     * Create a File Validator Config from a Pattern Config to configure the validator engine.
     *
     * @param filePatternConfig the config that will be copy to the new validator config.
     *
     * @return a new instance of the validator config.
     */
    public static JFileValidatorConfig newValidatorConfig(JFilePatternConfig filePatternConfig) {
        return new JFileValidatorConfigImpl(filePatternConfig);
    }

}
