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


import java.time.LocalDateTime;


/**
 * @author jonpereiradev
 * @since 0.1.0
 */
public interface LocalDateTimeTypeConfigurator extends TypedRuleConfigurator<LocalDateTimeTypeConfigurator> {

    /**
     * Defines a future rule validation.
     *
     * @return the object with the future rule configured.
     */
    LocalDateTimeTypeConfigurator future();

    /**
     * Defines a future or present rule validation.
     *
     * @return the object with the future or present rule configured.
     */
    LocalDateTimeTypeConfigurator futureOrPresent();

    /**
     * Defines a past rule validation.
     *
     * @return the object with the past rule configured.
     */
    LocalDateTimeTypeConfigurator past();

    /**
     * Defines a past or present rule validation.
     *
     * @return the object with the past or present rule configured.
     */
    LocalDateTimeTypeConfigurator pastOrPresent();

    /**
     * Defines a minimum date rule validation.
     *
     * @param min the minimum value for the date.
     *
     * @return the object with the after rule configured.
     */
    LocalDateTimeTypeConfigurator after(LocalDateTime min);

    /**
     * Defines a minimum date rule validation comparing to another column.
     *
     * @param columnNumber the column number that is compared to the current column value.
     *
     * @return the object with the after rule configured.
     */
    LocalDateTimeTypeConfigurator after(int columnNumber);

    /**
     * Defines a maximum date rule validation.
     *
     * @param max the maximum value for the date.
     *
     * @return the object with the before rule configured.
     */
    LocalDateTimeTypeConfigurator before(LocalDateTime max);

    /**
     * Defines a maximum date rule validation comparing to another column.
     *
     * @param columnNumber the column number that is compared to the current column value.
     *
     * @return the object with the before rule configured.
     */
    LocalDateTimeTypeConfigurator before(int columnNumber);

}
