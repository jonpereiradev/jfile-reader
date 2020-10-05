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


import java.util.regex.Pattern;


/**
 * @author jonpereiradev
 * @since 0.1.0
 */
public interface StringTypeConfigurator extends TypedRuleConfigurator<StringTypeConfigurator> {

    /**
     * Defines a not empty rule validation.
     *
     * @return the object with the not empty rule configured.
     */
    StringTypeConfigurator notEmpty();

    /**
     * Defines a min length rule validation.
     *
     * @param minLength the minimum length of the string.
     *
     * @return the object with the min length rule configured.
     */
    StringTypeConfigurator minLength(int minLength);

    /**
     * Defines a max length rule validation.
     *
     * @param maxLength the maximum length of the string.
     *
     * @return the object with the max length rule configured.
     */
    StringTypeConfigurator maxLength(int maxLength);

    /**
     * Defines a exact length rule validation.
     *
     * @param length the exact length of the string.
     *
     * @return the object with the exact length rule configured.
     */
    StringTypeConfigurator exactLength(int length);

    /**
     * Defines a domain rule validation with possible values options.
     *
     * @param values the possible values to a column.
     *
     * @return the object with the domain rule configured.
     */
    StringTypeConfigurator domain(String... values);

    /**
     * Defines an email rule validation.
     *
     * @return the object with the email rule configured.
     */
    StringTypeConfigurator email();

    /**
     * Defines a brazilian document CPF rule validation.
     *
     * @return the object with the cpf rule configured.
     */
    StringTypeConfigurator cpf();

    /**
     * Defines a brazilian document CNPJ rule validation.
     *
     * @return the object with the cnpj rule configured.
     */
    StringTypeConfigurator cnpj();

    /**
     * Defines a pattern rule validation.
     *
     * @param pattern a pattern to define valid string state.
     *
     * @return the object with the pattern rule configured.
     */
    StringTypeConfigurator regex(Pattern pattern);

}
