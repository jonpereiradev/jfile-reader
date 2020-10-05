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
package com.jonpereiradev.jfile.reader.validator.rule.configurator;


import com.jonpereiradev.jfile.reader.validator.JFileValidatorConfig;


/**
 * @author jonpereiradev
 * @since 0.1.0
 */
public interface LineRuleConfigurator {

    static LineRuleConfigurator defaultConfigurator(JFileValidatorConfig configuration) {
        return new LineRuleConfiguratorImpl(configuration);
    }

    /**
     * Define the number of columns for one line.
     *
     * @param size the number of columns that is valid to the line.
     *
     * @return the object with the column size rule configured.
     */
    LineRuleConfigurator columnsSize(int size);

    /**
     * Creates the rule config for the column at position.
     *
     * @return the configurator to configure column rules.
     */
    ColumnRuleConfigurator columns();

}
