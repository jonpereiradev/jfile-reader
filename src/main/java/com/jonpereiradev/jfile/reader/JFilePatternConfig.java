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
