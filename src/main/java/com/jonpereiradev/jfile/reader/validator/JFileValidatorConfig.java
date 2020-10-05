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
import com.jonpereiradev.jfile.reader.validator.rule.RuleRoot;


/**
 * @author jonpereiradev
 * @see JFileValidator
 * @see JFileValidatorFactory
 * @since 0.1.0
 */
public interface JFileValidatorConfig extends JFilePatternConfig<JFileValidatorConfig>, JFileRuleConfig {

    /**
     * Configure the max violation size to limit the number of errors that a file can have before stops the validation.
     * The default is to not have a limit.
     *
     * @param maxViolationSize an int number for the max violation size.
     *
     * @return the object with the maxViolationSize configured.
     */
    JFileValidatorConfig maxViolationSize(int maxViolationSize);

    /**
     * @return the max violation size configured.
     */
    int getMaxViolationSize();

    /**
     * @return the root of all rules configured for the validator.
     */
    RuleRoot getRuleRoot();

}
