package com.jonpereiradev.jfile.reader.validator.rule.configurator;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public interface GenericTypeConfigurator {

    /**
     * Defines a rule for a column of {@link Short} type.
     *
     * @return the {@link Short} type configurator.
     */
    ShortTypeConfigurator shortType();

    /**
     * Defines a rule for a column of {@link Integer} type.
     *
     * @return the {@link Integer} type configurator.
     */
    IntegerTypeConfigurator integerType();

    /**
     * Defines a rule for a column of {@link Long} type.
     *
     * @return the {@link Long} type configurator.
     */
    LongTypeConfigurator longType();

    /**
     * Defines a rule for a column of {@link Float} type.
     *
     * @return the {@link Float} type configurator.
     */
    FloatTypeConfigurator floatType();

    /**
     * Defines a rule for a column of {@link Double} type.
     *
     * @return the {@link Double} type configurator.
     */
    DoubleTypeConfigurator doubleType();

    /**
     * Defines a rule for a column of {@link Boolean} type.
     *
     * @return the {@link Boolean} type configurator.
     */
    BooleanTypeConfigurator booleanType();

    /**
     * Defines a rule for a column of {@link Character} type.
     *
     * @return the {@link Character} type configurator.
     */
    CharacterTypeConfigurator characterType();

    /**
     * Defines a rule for a column of {@link String} type.
     *
     * @return the {@link String} type configurator.
     */
    StringTypeConfigurator stringType();

    /**
     * Defines a rule for a column of {@link java.math.BigInteger} type.
     *
     * @return the {@link java.math.BigInteger} type configurator.
     */
    BigIntegerTypeConfigurator bigIntegerType();

    /**
     * Defines a rule for a column of {@link java.math.BigDecimal} type.
     *
     * @return the {@link java.math.BigDecimal} type configurator.
     */
    BigDecimalTypeConfigurator bigDecimalType();

    /**
     * Defines a rule for a column of {@link java.math.BigDecimal} type.
     *
     * @param decimalFormat the formatter that matches the column type.
     *
     * @return the {@link java.math.BigDecimal} type configurator.
     */
    BigDecimalTypeConfigurator bigDecimalType(DecimalFormat decimalFormat);

    /**
     * Defines a rule for a column of {@link java.util.Date}.
     *
     * @return the {@link java.util.Date} type configurator.
     */
    DateTypeConfigurator dateType();

    /**
     * Defines a rule for a column of {@link java.util.Date} type.
     *
     * @param dateFormat the formatter that matches the column type.
     *
     * @return the {@link java.util.Date} type configurator.
     */
    DateTypeConfigurator dateType(DateFormat dateFormat);

    /**
     * Defines a rule for a column of {@link java.time.LocalDate} type.
     *
     * @return the {@link java.time.LocalDate} type configurator.
     */
    LocalDateTypeConfigurator localDateType();

    /**
     * Defines a rule for a column of {@link java.time.LocalDate} type.
     *
     * @param dateTimeFormatter the formatter that matches the column type.
     *
     * @return the {@link java.time.LocalDate} type configurator.
     */
    LocalDateTypeConfigurator localDateType(DateTimeFormatter dateTimeFormatter);

    /**
     * Defines a rule for a column of {@link java.time.LocalDateTime} type.
     *
     * @return the {@link java.time.LocalDateTime} type configurator.
     */
    LocalDateTimeTypeConfigurator localDateTimeType();

    /**
     * Defines a rule for a column of {@link java.time.LocalDateTime} type.
     *
     * @param dateTimeFormatter the formatter that matches the column type.
     *
     * @return the {@link java.time.LocalDateTime} type configurator.
     */
    LocalDateTimeTypeConfigurator localDateTimeType(DateTimeFormatter dateTimeFormatter);

    /**
     * Defines a rule for a column of array type.
     *
     * @return the {@link ArrayTypeConfigurator} type configurator.
     */
    ArrayTypeConfigurator arrayOf();

    /**
     * Defines a rule for a column of array type.
     *
     * @param pattern the pattern that is used to split a column string into an array.
     *
     * @return the {@link ArrayTypeConfigurator} type configurator.
     */
    ArrayTypeConfigurator arrayOf(Pattern pattern);

}
