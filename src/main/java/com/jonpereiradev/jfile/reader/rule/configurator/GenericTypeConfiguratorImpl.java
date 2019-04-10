package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.rule.RuleConfiguratorContext;
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
    private final RuleConfiguratorContext context;
    private final RuleNode<ColumnRule> ruleNode;

    GenericTypeConfiguratorImpl(int position, RuleConfiguratorContext context, RuleNode<ColumnRule> ruleNode) {
        this.position = position;
        this.context = context;
        this.ruleNode = ruleNode;
    }

    @Override
    public ShortTypeConfigurator shortType() {
        ShortTypeRule rule = new ShortTypeRule(position);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new ShortTypeConfiguratorImpl(position, context, rule.getRuleNode());
    }

    @Override
    public IntegerTypeConfigurator integerType() {
        IntegerTypeRule rule = new IntegerTypeRule(position);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new IntegerTypeConfiguratorImpl(position, context, rule.getRuleNode());
    }

    @Override
    public LongTypeConfigurator longType() {
        LongTypeRule rule = new LongTypeRule(position);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new LongTypeConfiguratorImpl(position, context, rule.getRuleNode());
    }

    @Override
    public FloatTypeConfigurator floatType() {
        FloatTypeRule rule = new FloatTypeRule(position);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new FloatTypeConfiguratorImpl(position, context, rule.getRuleNode());
    }

    @Override
    public DoubleTypeConfigurator doubleType() {
        DoubleTypeRule rule = new DoubleTypeRule(position);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new DoubleTypeConfiguratorImpl(position, context, rule.getRuleNode());
    }

    @Override
    public BooleanTypeConfigurator booleanType() {
        BooleanTypeRule rule = new BooleanTypeRule(position);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new BooleanTypeConfiguratorImpl(position, context, rule.getRuleNode());
    }

    @Override
    public CharacterTypeConfigurator characterType() {
        CharacterTypeRule rule = new CharacterTypeRule(position);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new CharacterTypeConfiguratorImpl(position, context, rule.getRuleNode());
    }

    @Override
    public StringTypeConfigurator stringType() {
        return new StringTypeConfiguratorImpl(position, context, ruleNode);
    }

    @Override
    public BigIntegerTypeConfigurator bigIntegerType() {
        BigIntegerTypeRule rule = new BigIntegerTypeRule(position);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new BigIntegerTypeConfiguratorImpl(position, context, rule.getRuleNode());
    }

    @Override
    public BigDecimalTypeConfigurator bigDecimalType() {
        return bigDecimalType(context.getReaderConfiguration().getBigDecimalFormatter());
    }

    @Override
    public BigDecimalTypeConfigurator bigDecimalType(DecimalFormat decimalFormat) {
        BigDecimalTypeRule rule = new BigDecimalTypeRule(position, decimalFormat);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new BigDecimalTypeConfiguratorImpl(position, decimalFormat, context, rule.getRuleNode());
    }

    @Override
    public DateTypeConfigurator dateType() {
        return dateType(context.getReaderConfiguration().getDateFormat());
    }

    @Override
    public DateTypeConfigurator dateType(DateFormat dateFormat) {
        DateTypeRule rule = new DateTypeRule(position, dateFormat);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new DateTypeConfiguratorImpl(position, dateFormat, context, rule.getRuleNode());
    }

    @Override
    public LocalDateTypeConfigurator localDateType() {
        return localDateType(context.getReaderConfiguration().getLocalDateFormatter());
    }

    @Override
    public LocalDateTypeConfigurator localDateType(DateTimeFormatter dateTimeFormatter) {
        LocalDateTypeRule rule = new LocalDateTypeRule(position, dateTimeFormatter);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new LocalDateTypeConfiguratorImpl(position, dateTimeFormatter, context, rule.getRuleNode());
    }

    @Override
    public LocalDateTimeTypeConfigurator localDateTimeType() {
        return localDateTimeType(context.getReaderConfiguration().getLocalDateTimeFormatter());
    }

    @Override
    public LocalDateTimeTypeConfigurator localDateTimeType(DateTimeFormatter dateTimeFormatter) {
        LocalDateTimeTypeRule rule = new LocalDateTimeTypeRule(position, dateTimeFormatter);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new LocalDateTimeTypeConfiguratorImpl(position, dateTimeFormatter, context, rule.getRuleNode());
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
        return new ArrayTypeConfiguratorImpl(position, context, rule.getRuleNode());
    }

    @Override
    public void build() {
        context.getReaderConfiguration().withRuleConfiguration(context.getRuleConfiguration());
    }
}
