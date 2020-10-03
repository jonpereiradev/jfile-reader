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
package com.jonpereiradev.jfile.reader;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

/**
 * Config class to configure everything related to data patterns.
 *
 * @param <T> the type of class returned by the method to allow fluent builder.
 */
public interface JFilePatternConfig<T extends JFilePatternConfig<T>> {

    /**
     * Configure the default {@link java.util.Date} string formatter.
     *
     * @param dateFormat the formatter that applies to all {@link java.util.Date} type columns.
     *
     * @return the type of class returned by the method to allow fluent builder.
     */
    T dateFormatter(DateFormat dateFormat);

    /**
     * @return the date formatter for {@link java.util.Date}
     */
    DateFormat getDateFormat();

    /**
     * Configure the default {@link java.time.LocalDate} string formatter.
     *
     * @param localDateFormatter the formatter that applies to all {@link java.time.LocalDate} type columns.
     *
     * @return the type of class returned by the method to allow fluent builder.
     */
    T localDateFormatter(DateTimeFormatter localDateFormatter);

    /**
     * @return the local date formatter for {@link java.time.LocalDate}
     */
    DateTimeFormatter getLocalDateFormatter();

    /**
     * Configure the default {@link java.time.LocalDateTime} string formatter.
     *
     * @param localDateTimeFormatter the formatter that applies to all {@link java.time.LocalDateTime} type columns.
     *
     * @return the type of class returned by the method to allow fluent builder.
     */
    T localDateTimeFormatter(DateTimeFormatter localDateTimeFormatter);

    /**
     * @return the local date time formatter for {@link java.time.LocalDateTime}
     */
    DateTimeFormatter getLocalDateTimeFormatter();

    /**
     * Configure the default {@link java.math.BigDecimal} string formatter.
     *
     * @param decimalFormat the formatter that applies to all {@link java.math.BigDecimal} type columns.
     *
     * @return the type of class returned by the method to allow fluent builder.
     */
    T bigDecimalFormat(DecimalFormat decimalFormat);

    /**
     * @return the big decimal formatter for {@link java.math.BigDecimal}
     */
    DecimalFormat getBigDecimalFormatter();

}
