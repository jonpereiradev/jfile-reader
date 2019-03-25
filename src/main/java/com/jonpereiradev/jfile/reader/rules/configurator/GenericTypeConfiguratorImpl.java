package com.jonpereiradev.jfile.reader.rules.configurator;

import com.jonpereiradev.jfile.reader.rules.RuleConfiguratorContext;
import com.jonpereiradev.jfile.reader.rules.column.BigDecimalTypeRule;
import com.jonpereiradev.jfile.reader.rules.column.BigIntegerTypeRule;
import com.jonpereiradev.jfile.reader.rules.column.BooleanTypeRule;
import com.jonpereiradev.jfile.reader.rules.column.DateTypeRule;
import com.jonpereiradev.jfile.reader.rules.column.DoubleTypeRule;
import com.jonpereiradev.jfile.reader.rules.column.FloatTypeRule;
import com.jonpereiradev.jfile.reader.rules.column.IntegerTypeRule;
import com.jonpereiradev.jfile.reader.rules.column.LocalDateTimeTypeRule;
import com.jonpereiradev.jfile.reader.rules.column.LocalDateTypeRule;
import com.jonpereiradev.jfile.reader.rules.column.LongTypeRule;
import com.jonpereiradev.jfile.reader.rules.column.ShortTypeRule;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

final class GenericTypeConfiguratorImpl implements GenericTypeConfigurator {

    private final int position;
    private final RuleConfiguratorContext context;

    GenericTypeConfiguratorImpl(int position, RuleConfiguratorContext context) {
        this.position = position;
        this.context = context;
    }

    @Override
    public ShortTypeConfigurator shortType() {
        context.getRuleConfiguration().getColumnRules().add(new ShortTypeRule(position));
        return new ShortTypeConfiguratorImpl(position, context);
    }

    @Override
    public IntegerTypeConfigurator integerType() {
        context.getRuleConfiguration().getColumnRules().add(new IntegerTypeRule(position));
        return new IntegerTypeConfiguratorImpl(position, context);
    }

    @Override
    public LongTypeConfigurator longType() {
        context.getRuleConfiguration().getColumnRules().add(new LongTypeRule(position));
        return new LongTypeConfiguratorImpl(position, context);
    }

    @Override
    public FloatTypeConfigurator floatType() {
        context.getRuleConfiguration().getColumnRules().add(new FloatTypeRule(position));
        return new FloatTypeConfiguratorImpl(position, context);
    }

    @Override
    public DoubleTypeConfigurator doubleType() {
        context.getRuleConfiguration().getColumnRules().add(new DoubleTypeRule(position));
        return new DoubleTypeConfiguratorImpl(position, context);
    }

    @Override
    public BooleanTypeConfigurator booleanType() {
        context.getRuleConfiguration().getColumnRules().add(new BooleanTypeRule(position));
        return new BooleanTypeConfiguratorImpl(position, context);
    }

    @Override
    public StringTypeConfigurator stringType() {
        return new StringTypeConfiguratorImpl(position, context);
    }

    @Override
    public BigIntegerTypeConfigurator bigIntegerType() {
        context.getRuleConfiguration().getColumnRules().add(new BigIntegerTypeRule(position));
        return new BigIntegerTypeConfiguratorImpl(position, context);
    }

    @Override
    public BigDecimalTypeConfigurator bigDecimalType() {
        return bigDecimalType(context.getReaderConfiguration().getBigDecimalFormatter());
    }

    @Override
    public BigDecimalTypeConfigurator bigDecimalType(DecimalFormat decimalFormat) {
        context.getRuleConfiguration().getColumnRules().add(new BigDecimalTypeRule(position, decimalFormat));
        return new BigDecimalTypeConfiguratorImpl(position, decimalFormat, context);
    }

    @Override
    public DateTypeConfigurator dateType() {
        return dateType(context.getReaderConfiguration().getDateFormat());
    }

    @Override
    public DateTypeConfigurator dateType(DateFormat dateFormat) {
        context.getRuleConfiguration().getColumnRules().add(new DateTypeRule(position, dateFormat));
        return new DateTypeConfiguratorImpl(position, dateFormat, context);
    }

    @Override
    public LocalDateTypeConfigurator localDateType() {
        return localDateType(context.getReaderConfiguration().getLocalDateFormatter());
    }

    @Override
    public LocalDateTypeConfigurator localDateType(DateTimeFormatter dateTimeFormatter) {
        context.getRuleConfiguration().getColumnRules().add(new LocalDateTypeRule(position, dateTimeFormatter));
        return new LocalDateTypeConfiguratorImpl(position, dateTimeFormatter, context);
    }

    @Override
    public LocalDateTimeConfigurator localDateTimeType() {
        return localDateTimeType(context.getReaderConfiguration().getLocalDateTimeFormatter());
    }

    @Override
    public LocalDateTimeConfigurator localDateTimeType(DateTimeFormatter dateTimeFormatter) {
        context.getRuleConfiguration().getColumnRules().add(new LocalDateTimeTypeRule(position, dateTimeFormatter));
        return new LocalDateTimeConfiguratorImpl(position, dateTimeFormatter, context);
    }

    @Override
    public void build() {
        context.getReaderConfiguration().withRuleConfiguration(context.getRuleConfiguration());
    }
}
