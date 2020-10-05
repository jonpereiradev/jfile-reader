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


/**
 * Configurate the reference column rule.
 *
 * @param <T> the type of rule configurator returned.
 *
 * @author jonpereiradev
 * @since 0.1.0
 */
public interface RefRuleConfigurator<T extends TypedRuleConfigurator<?>> {

    /**
     * Creates a rule for not empty column.
     *
     * @return the object with the rule filled configured.
     */
    T filled();

    /**
     * Creates a rule for column with specific values.
     *
     * @param values the values that the column must have to activate the ref rule.
     *
     * @return the object with the rule filled configured.
     */
    T filled(Object... values);

    /**
     * Creates a rule for column with empty value.
     *
     * @return the object with the rule empty configured.
     */
    T empty();

}
