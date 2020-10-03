package com.jonpereiradev.jfile.reader.validator.rule.configurator;

import com.jonpereiradev.jfile.reader.validator.JFileValidatorConfig;
import com.jonpereiradev.jfile.reader.validator.rule.RuleNode;
import com.jonpereiradev.jfile.reader.validator.rule.column.ColumnRule;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

final class ArrayTypeConfiguratorImpl implements ArrayTypeConfigurator {

    private final GenericTypeConfiguratorImpl genericTypeConfigurator;

    ArrayTypeConfiguratorImpl(
        int position,
        JFileValidatorConfig configuration,
        RuleNode<ColumnRule> ruleNode) {
        this.genericTypeConfigurator = new GenericTypeConfiguratorImpl(position, configuration, ruleNode);
    }

    @Override
    public ShortTypeConfigurator shortType() {
        return genericTypeConfigurator.shortType();
    }

    @Override
    public IntegerTypeConfigurator integerType() {
        return genericTypeConfigurator.integerType();
    }

    @Override
    public LongTypeConfigurator longType() {
        return genericTypeConfigurator.longType();
    }

    @Override
    public FloatTypeConfigurator floatType() {
        return genericTypeConfigurator.floatType();
    }

    @Override
    public DoubleTypeConfigurator doubleType() {
        return genericTypeConfigurator.doubleType();
    }

    @Override
    public BooleanTypeConfigurator booleanType() {
        return genericTypeConfigurator.booleanType();
    }

    @Override
    public CharacterTypeConfigurator characterType() {
        return genericTypeConfigurator.characterType();
    }

    @Override
    public StringTypeConfigurator stringType() {
        return genericTypeConfigurator.stringType();
    }

    @Override
    public BigIntegerTypeConfigurator bigIntegerType() {
        return genericTypeConfigurator.bigIntegerType();
    }

    @Override
    public BigDecimalTypeConfigurator bigDecimalType() {
        return genericTypeConfigurator.bigDecimalType();
    }

    @Override
    public BigDecimalTypeConfigurator bigDecimalType(DecimalFormat decimalFormat) {
        return genericTypeConfigurator.bigDecimalType(decimalFormat);
    }

    @Override
    public DateTypeConfigurator dateType() {
        return genericTypeConfigurator.dateType();
    }

    @Override
    public DateTypeConfigurator dateType(DateFormat dateFormat) {
        return genericTypeConfigurator.dateType(dateFormat);
    }

    @Override
    public LocalDateTypeConfigurator localDateType() {
        return genericTypeConfigurator.localDateType();
    }

    @Override
    public LocalDateTypeConfigurator localDateType(DateTimeFormatter dateTimeFormatter) {
        return genericTypeConfigurator.localDateType(dateTimeFormatter);
    }

    @Override
    public LocalDateTimeTypeConfigurator localDateTimeType() {
        return genericTypeConfigurator.localDateTimeType();
    }

    @Override
    public LocalDateTimeTypeConfigurator localDateTimeType(DateTimeFormatter dateTimeFormatter) {
        return genericTypeConfigurator.localDateTimeType(dateTimeFormatter);
    }

}
