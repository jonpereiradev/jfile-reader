package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.rule.RuleConfiguratorContext;
import com.jonpereiradev.jfile.reader.rule.RuleNode;
import com.jonpereiradev.jfile.reader.rule.column.*;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

final class GenericTypeConfiguratorImpl implements GenericTypeConfigurator {

    private final int position;
    private final RuleConfiguratorContext context;
    private final RuleNode<ColumnRule> ruleNode;

    GenericTypeConfiguratorImpl(int position, RuleConfiguratorContext context, RuleNode<ColumnRule> ruleNode) {
        this.position = position;
        this.context = context;
        this.ruleNode = ruleNode;
    }

    @Override
    public ShortTypeConfigurator shortType() {
        ruleNode.add(new ShortTypeRule(position));
        return new ShortTypeConfiguratorImpl(position, context, ruleNode);
    }

    @Override
    public IntegerTypeConfigurator integerType() {
        ruleNode.add(new IntegerTypeRule(position));
        return new IntegerTypeConfiguratorImpl(position, context, ruleNode);
    }

    @Override
    public LongTypeConfigurator longType() {
        ruleNode.add(new LongTypeRule(position));
        return new LongTypeConfiguratorImpl(position, context, ruleNode);
    }

    @Override
    public FloatTypeConfigurator floatType() {
        ruleNode.add(new FloatTypeRule(position));
        return new FloatTypeConfiguratorImpl(position, context, ruleNode);
    }

    @Override
    public DoubleTypeConfigurator doubleType() {
        ruleNode.add(new DoubleTypeRule(position));
        return new DoubleTypeConfiguratorImpl(position, context, ruleNode);
    }

    @Override
    public BooleanTypeConfigurator booleanType() {
        ruleNode.add(new BooleanTypeRule(position));
        return new BooleanTypeConfiguratorImpl(position, context, ruleNode);
    }

    @Override
    public CharacterTypeConfigurator characterType() {
        ruleNode.add(new CharacterTypeRule(position));
        return new CharacterTypeConfiguratorImpl(position, context, ruleNode);
    }

    @Override
    public StringTypeConfigurator stringType() {
        return new StringTypeConfiguratorImpl(position, context, ruleNode);
    }

    @Override
    public BigIntegerTypeConfigurator bigIntegerType() {
        ruleNode.add(new BigIntegerTypeRule(position));
        return new BigIntegerTypeConfiguratorImpl(position, context, ruleNode);
    }

    @Override
    public BigDecimalTypeConfigurator bigDecimalType() {
        return bigDecimalType(context.getReaderConfiguration().getBigDecimalFormatter());
    }

    @Override
    public BigDecimalTypeConfigurator bigDecimalType(DecimalFormat decimalFormat) {
        ruleNode.add(new BigDecimalTypeRule(position, decimalFormat));
        return new BigDecimalTypeConfiguratorImpl(position, decimalFormat, context, ruleNode);
    }

    @Override
    public DateTypeConfigurator dateType() {
        return dateType(context.getReaderConfiguration().getDateFormat());
    }

    @Override
    public DateTypeConfigurator dateType(DateFormat dateFormat) {
        ruleNode.add(new DateTypeRule(position, dateFormat));
        return new DateTypeConfiguratorImpl(position, dateFormat, context, ruleNode);
    }

    @Override
    public LocalDateTypeConfigurator localDateType() {
        return localDateType(context.getReaderConfiguration().getLocalDateFormatter());
    }

    @Override
    public LocalDateTypeConfigurator localDateType(DateTimeFormatter dateTimeFormatter) {
        ruleNode.add(new LocalDateTypeRule(position, dateTimeFormatter));
        return new LocalDateTypeConfiguratorImpl(position, dateTimeFormatter, context, ruleNode);
    }

    @Override
    public LocalDateTimeTypeConfigurator localDateTimeType() {
        return localDateTimeType(context.getReaderConfiguration().getLocalDateTimeFormatter());
    }

    @Override
    public LocalDateTimeTypeConfigurator localDateTimeType(DateTimeFormatter dateTimeFormatter) {
        ruleNode.add(new LocalDateTimeTypeRule(position, dateTimeFormatter));
        return new LocalDateTimeTypeConfiguratorImpl(position, dateTimeFormatter, context, ruleNode);
    }

    @Override
    public void build() {
        context.getReaderConfiguration().withRuleConfiguration(context.getRuleConfiguration());
    }
}
