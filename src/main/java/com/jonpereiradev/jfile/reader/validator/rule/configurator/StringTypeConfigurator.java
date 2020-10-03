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

public interface StringTypeConfigurator extends TypedRuleConfigurator<StringTypeConfigurator> {

    /**
     * defines a not empty rule validation.
     */
    StringTypeConfigurator notEmpty();

    /**
     * defines a min length rule validation.
     */
    StringTypeConfigurator min(int min);

    /**
     * defines a max length rule validation.
     */
    StringTypeConfigurator max(int max);

    /**
     * defines a domain rule validation with possible values options.
     */
    StringTypeConfigurator domain(String... values);

    /**
     * defines an email rule validation.
     */
    StringTypeConfigurator email();

    /**
     * defines a brazilian document CPF rule validation.
     */
    StringTypeConfigurator cpf();

    /**
     * defines a brazilian document CNPJ rule validation.
     */
    StringTypeConfigurator cnpj();

    /**
     * defines a pattern rule validation.
     */
    StringTypeConfigurator regex(Pattern pattern);

}
