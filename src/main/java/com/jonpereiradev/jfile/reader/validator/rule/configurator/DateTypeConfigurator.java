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

import java.util.Date;

public interface DateTypeConfigurator extends TypedRuleConfigurator<DateTypeConfigurator> {

    /**
     * Defines a future value rule validation.
     * Used for validate that a date must be after the present date.
     *
     * @return the configurator with the future rule configured.
     */
    DateTypeConfigurator future();

    /**
     * Defines a future or present value rule validation.
     * Used for validate that a date must be equal or after the present date.
     *
     * @return the configurator with the future or present rule configured.
     */
    DateTypeConfigurator futureOrPresent();

    /**
     * Defines a past value rule validation.
     * Used for validate that a date must be before the present date.
     *
     * @return the configurator with the before rule configured.
     */
    DateTypeConfigurator past();

    /**
     * Defines a before or present value rule validation.
     * Used for validate that a date must be before or equal the present date.
     *
     * @return the configurator with the before or present rule configured.
     */
    DateTypeConfigurator pastOrPresent();

    /**
     * Defines a after date rule validation.
     * Used for validate that a date must be after the date parameter.
     *
     * @param date the date that is used to compare in the rule.
     *
     * @return the configurator with the after date rule configured.
     */
    DateTypeConfigurator after(Date date);

    /**
     * Defines after other column value rule validation.
     * Used for validate that a date must be after another date column in the line.
     *
     * @param columnNumber an int columnNumber reference to other date column in the line.
     *
     * @return the configurator with the after column value rule configured.
     */
    DateTypeConfigurator after(int columnNumber);

    /**
     * Defines a before date rule validation.
     * Used for validate that a date must be before the date parameter.
     *
     * @param date the date that is used to compare in the rule.
     *
     * @return the configurator with the before date rule configured.
     */
    DateTypeConfigurator before(Date date);

    /**
     * Defines before other column value rule validation.
     * Used for validate that a date must be before another date column in the line.
     *
     * @param columnNumber an int position reference to other date column in the line.
     *
     * @return the configurator with the before column value rule configured.
     */
    DateTypeConfigurator before(int columnNumber);

}
