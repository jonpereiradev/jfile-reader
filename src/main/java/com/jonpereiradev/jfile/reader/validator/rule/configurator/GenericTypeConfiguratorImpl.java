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
package com.jonpereiradev.jfile.reader.validator.rule.configurator;


import com.jonpereiradev.jfile.reader.validator.JFileValidatorConfig;
import com.jonpereiradev.jfile.reader.validator.rule.RuleNode;
import com.jonpereiradev.jfile.reader.validator.rule.RuleNodeImpl;
import com.jonpereiradev.jfile.reader.validator.rule.column.ArrayOfTypeRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.BigDecimalTypeRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.BigIntegerTypeRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.BooleanTypeRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.CharacterTypeRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.ColumnRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.DateTypeRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.DoubleTypeRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.FloatTypeRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.IntegerTypeRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.LocalDateTimeTypeRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.LocalDateTypeRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.LongTypeRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.ShortTypeRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.StringTypeRule;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;


final class GenericTypeConfiguratorImpl implements GenericTypeConfigurator {

    private static final Pattern DEFAULT_ARRAY_SEPARATOR = Pattern.compile(",\\s*");

    private final int columnNumber;
    private final JFileValidatorConfig configuration;
    private final RuleNode<ColumnRule> ruleNode;

    GenericTypeConfiguratorImpl(
        int columnNumber,
        JFileValidatorConfig configuration,
        RuleNode<ColumnRule> ruleNode) {
        this.columnNumber = columnNumber;
        this.configuration = configuration;
        this.ruleNode = ruleNode;
    }

    @Override
    public ShortTypeConfigurator shortType() {
        ShortTypeRule rule = new ShortTypeRule(columnNumber);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new ShortTypeConfiguratorImpl(columnNumber, configuration, rule.getRuleNode());
    }

    @Override
    public IntegerTypeConfigurator integerType() {
        IntegerTypeRule rule = new IntegerTypeRule(columnNumber);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new IntegerTypeConfiguratorImpl(columnNumber, configuration, rule.getRuleNode());
    }

    @Override
    public LongTypeConfigurator longType() {
        LongTypeRule rule = new LongTypeRule(columnNumber);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new LongTypeConfiguratorImpl(columnNumber, configuration, rule.getRuleNode());
    }

    @Override
    public FloatTypeConfigurator floatType() {
        FloatTypeRule rule = new FloatTypeRule(columnNumber);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new FloatTypeConfiguratorImpl(columnNumber, configuration, rule.getRuleNode());
    }

    @Override
    public DoubleTypeConfigurator doubleType() {
        DoubleTypeRule rule = new DoubleTypeRule(columnNumber);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new DoubleTypeConfiguratorImpl(columnNumber, configuration, rule.getRuleNode());
    }

    @Override
    public BooleanTypeConfigurator booleanType() {
        BooleanTypeRule rule = new BooleanTypeRule(columnNumber);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new BooleanTypeConfiguratorImpl(columnNumber, configuration, rule.getRuleNode());
    }

    @Override
    public CharacterTypeConfigurator characterType() {
        CharacterTypeRule rule = new CharacterTypeRule(columnNumber);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new CharacterTypeConfiguratorImpl(columnNumber, configuration, rule.getRuleNode());
    }

    @Override
    public StringTypeConfigurator stringType() {
        StringTypeRule rule = new StringTypeRule(columnNumber);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new StringTypeConfiguratorImpl(columnNumber, configuration, rule.getRuleNode());
    }

    @Override
    public BigIntegerTypeConfigurator bigIntegerType() {
        BigIntegerTypeRule rule = new BigIntegerTypeRule(columnNumber);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new BigIntegerTypeConfiguratorImpl(columnNumber, configuration, rule.getRuleNode());
    }

    @Override
    public BigDecimalTypeConfigurator bigDecimalType() {
        return bigDecimalType(configuration.getBigDecimalFormatter());
    }

    @Override
    public BigDecimalTypeConfigurator bigDecimalType(DecimalFormat decimalFormat) {
        BigDecimalTypeRule rule = new BigDecimalTypeRule(columnNumber, decimalFormat);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new BigDecimalTypeConfiguratorImpl(columnNumber, decimalFormat, configuration, rule.getRuleNode());
    }

    @Override
    public DateTypeConfigurator dateType() {
        return dateType(configuration.getDateFormat());
    }

    @Override
    public DateTypeConfigurator dateType(DateFormat dateFormat) {
        DateTypeRule rule = new DateTypeRule(columnNumber, dateFormat);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new DateTypeConfiguratorImpl(columnNumber, dateFormat, configuration, rule.getRuleNode());
    }

    @Override
    public LocalDateTypeConfigurator localDateType() {
        return localDateType(configuration.getLocalDateFormatter());
    }

    @Override
    public LocalDateTypeConfigurator localDateType(DateTimeFormatter dateTimeFormatter) {
        LocalDateTypeRule rule = new LocalDateTypeRule(columnNumber, dateTimeFormatter);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new LocalDateTypeConfiguratorImpl(columnNumber, dateTimeFormatter, configuration, rule.getRuleNode());
    }

    @Override
    public LocalDateTimeTypeConfigurator localDateTimeType() {
        return localDateTimeType(configuration.getLocalDateTimeFormatter());
    }

    @Override
    public LocalDateTimeTypeConfigurator localDateTimeType(DateTimeFormatter dateTimeFormatter) {
        LocalDateTimeTypeRule rule = new LocalDateTimeTypeRule(columnNumber, dateTimeFormatter);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new LocalDateTimeTypeConfiguratorImpl(
            columnNumber,
            dateTimeFormatter,
            configuration,
            rule.getRuleNode()
        );
    }

    @Override
    public ArrayTypeConfigurator arrayOf() {
        return arrayOf(DEFAULT_ARRAY_SEPARATOR);
    }

    @Override
    public ArrayTypeConfigurator arrayOf(Pattern pattern) {
        ArrayOfTypeRule rule = new ArrayOfTypeRule(columnNumber, pattern);
        rule.setRuleNode(new RuleNodeImpl<>(rule.getClass(), ruleNode));
        ruleNode.add(rule);
        return new ArrayTypeConfiguratorImpl(columnNumber, configuration, rule.getRuleNode());
    }

}
