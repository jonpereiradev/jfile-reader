package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;
import com.jonpereiradev.jfile.reader.rule.RuleNode;
import com.jonpereiradev.jfile.reader.rule.RuleNodeImpl;
import com.jonpereiradev.jfile.reader.rule.column.*;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

final class GenericTypeConfiguratorImpl implements GenericTypeConfigurator {

    private static final Pattern DEFAULT_ARRAY_SEPARATOR = Pattern.compile(",\\s*");

    private final int position;
    private final ReaderConfiguration configuration;
    private final RuleNode<ColumnRule> ruleNode;

    GenericTypeConfiguratorImpl(int position, ReaderConfiguration configuration, RuleNode<ColumnRule> ruleNode) {
        this.position = position;
        this.configuration = configuration;
        this.ruleNode = ruleNode;
    }

    @Override
    public ShortTypeConfigurator shortType() {
        ShortTypeRule rule = new ShortTypeRule(position);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new ShortTypeConfiguratorImpl(position, configuration, rule.getRuleNode());
    }

    @Override
    public IntegerTypeConfigurator integerType() {
        IntegerTypeRule rule = new IntegerTypeRule(position);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new IntegerTypeConfiguratorImpl(position, configuration, rule.getRuleNode());
    }

    @Override
    public LongTypeConfigurator longType() {
        LongTypeRule rule = new LongTypeRule(position);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new LongTypeConfiguratorImpl(position, configuration, rule.getRuleNode());
    }

    @Override
    public FloatTypeConfigurator floatType() {
        FloatTypeRule rule = new FloatTypeRule(position);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new FloatTypeConfiguratorImpl(position, configuration, rule.getRuleNode());
    }

    @Override
    public DoubleTypeConfigurator doubleType() {
        DoubleTypeRule rule = new DoubleTypeRule(position);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new DoubleTypeConfiguratorImpl(position, configuration, rule.getRuleNode());
    }

    @Override
    public BooleanTypeConfigurator booleanType() {
        BooleanTypeRule rule = new BooleanTypeRule(position);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new BooleanTypeConfiguratorImpl(position, configuration, rule.getRuleNode());
    }

    @Override
    public CharacterTypeConfigurator characterType() {
        CharacterTypeRule rule = new CharacterTypeRule(position);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new CharacterTypeConfiguratorImpl(position, configuration, rule.getRuleNode());
    }

    @Override
    public StringTypeConfigurator stringType() {
        StringTypeRule rule = new StringTypeRule(position);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new StringTypeConfiguratorImpl(position, configuration, rule.getRuleNode());
    }

    @Override
    public BigIntegerTypeConfigurator bigIntegerType() {
        BigIntegerTypeRule rule = new BigIntegerTypeRule(position);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new BigIntegerTypeConfiguratorImpl(position, configuration, rule.getRuleNode());
    }

    @Override
    public BigDecimalTypeConfigurator bigDecimalType() {
        return bigDecimalType(configuration.getBigDecimalFormatter());
    }

    @Override
    public BigDecimalTypeConfigurator bigDecimalType(DecimalFormat decimalFormat) {
        BigDecimalTypeRule rule = new BigDecimalTypeRule(position, decimalFormat);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new BigDecimalTypeConfiguratorImpl(position, decimalFormat, configuration, rule.getRuleNode());
    }

    @Override
    public DateTypeConfigurator dateType() {
        return dateType(configuration.getDateFormat());
    }

    @Override
    public DateTypeConfigurator dateType(DateFormat dateFormat) {
        DateTypeRule rule = new DateTypeRule(position, dateFormat);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new DateTypeConfiguratorImpl(position, dateFormat, configuration, rule.getRuleNode());
    }

    @Override
    public LocalDateTypeConfigurator localDateType() {
        return localDateType(configuration.getLocalDateFormatter());
    }

    @Override
    public LocalDateTypeConfigurator localDateType(DateTimeFormatter dateTimeFormatter) {
        LocalDateTypeRule rule = new LocalDateTypeRule(position, dateTimeFormatter);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new LocalDateTypeConfiguratorImpl(position, dateTimeFormatter, configuration, rule.getRuleNode());
    }

    @Override
    public LocalDateTimeTypeConfigurator localDateTimeType() {
        return localDateTimeType(configuration.getLocalDateTimeFormatter());
    }

    @Override
    public LocalDateTimeTypeConfigurator localDateTimeType(DateTimeFormatter dateTimeFormatter) {
        LocalDateTimeTypeRule rule = new LocalDateTimeTypeRule(position, dateTimeFormatter);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new LocalDateTimeTypeConfiguratorImpl(position, dateTimeFormatter, configuration, rule.getRuleNode());
    }

    @Override
    public ArrayTypeConfigurator arrayOf() {
        return arrayOf(DEFAULT_ARRAY_SEPARATOR);
    }

    @Override
    public ArrayTypeConfigurator arrayOf(Pattern pattern) {
        ArrayOfTypeRule rule = new ArrayOfTypeRule(position, pattern);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new ArrayTypeConfiguratorImpl(position, configuration, rule.getRuleNode());
    }

}
