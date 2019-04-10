package com.jonpereiradev.jfile.reader.rule.configurator;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public interface ArrayTypeConfigurator {

    /**
     * defines a rule for a column of short type.
     */
    ShortTypeConfigurator shortType();

    /**
     * defines a rule for a column of integer type.
     */
    IntegerTypeConfigurator integerType();

    /**
     * defines a rule for a column of long type.
     */
    LongTypeConfigurator longType();

    /**
     * defines a rule for a column of float type.
     */
    FloatTypeConfigurator floatType();

    /**
     * defines a rule for a column of double type.
     */
    DoubleTypeConfigurator doubleType();

    /**
     * defines a rule for a column of boolean type.
     */
    BooleanTypeConfigurator booleanType();

    /**
     * defines a rule for a column of character type.
     */
    CharacterTypeConfigurator characterType();

    /**
     * defines a rule for a column of string type.
     */
    StringTypeConfigurator stringType();

    /**
     * defines a rule for a column of big integer type.
     */
    BigIntegerTypeConfigurator bigIntegerType();

    /**
     * defines a rule for a column of big decimal type.
     */
    BigDecimalTypeConfigurator bigDecimalType();

    /**
     * defines a rule for a column of big decimal type.
     */
    BigDecimalTypeConfigurator bigDecimalType(DecimalFormat decimalFormat);

    /**
     * defines a rule for a column of date type.
     */
    DateTypeConfigurator dateType();

    /**
     * defines a rule for a column of date type.
     */
    DateTypeConfigurator dateType(DateFormat pattern);

    /**
     * defines a rule for a column of local date type.
     */
    LocalDateTypeConfigurator localDateType();

    /**
     * defines a rule for a column of local date type.
     */
    LocalDateTypeConfigurator localDateType(DateTimeFormatter dateTimeFormatter);

    /**
     * defines a rule for a column of local date time type.
     */
    LocalDateTimeTypeConfigurator localDateTimeType();

    /**
     * defines a rule for a column of local date time type.
     */
    LocalDateTimeTypeConfigurator localDateTimeType(DateTimeFormatter dateTimeFormatter);

}
