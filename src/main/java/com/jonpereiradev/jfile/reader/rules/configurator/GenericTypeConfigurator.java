package com.jonpereiradev.jfile.reader.rules.configurator;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public interface GenericTypeConfigurator {

    ShortTypeConfigurator shortType();

    IntegerTypeConfigurator integerType();

    LongTypeConfigurator longType();

    FloatTypeConfigurator floatType();

    DoubleTypeConfigurator doubleType();

    BooleanTypeConfigurator booleanType();

    CharacterTypeConfigurator characterType();

    StringTypeConfigurator stringType();

    BigIntegerTypeConfigurator bigIntegerType();

    BigDecimalTypeConfigurator bigDecimalType();

    BigDecimalTypeConfigurator bigDecimalType(DecimalFormat decimalFormat);

    DateTypeConfigurator dateType();

    DateTypeConfigurator dateType(DateFormat pattern);

    LocalDateTypeConfigurator localDateType();

    LocalDateTypeConfigurator localDateType(DateTimeFormatter dateTimeFormatter);

    LocalDateTimeConfigurator localDateTimeType();

    LocalDateTimeConfigurator localDateTimeType(DateTimeFormatter dateTimeFormatter);

    void build();
}
