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
import com.jonpereiradev.jfile.reader.validator.rule.column.ColumnRule;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

final class ArrayTypeConfiguratorImpl implements ArrayTypeConfigurator {

    private final GenericTypeConfiguratorImpl genericTypeConfigurator;

    ArrayTypeConfiguratorImpl(
        int columnNumber,
        JFileValidatorConfig configuration,
        RuleNode<ColumnRule> ruleNode) {
        this.genericTypeConfigurator = new GenericTypeConfiguratorImpl(columnNumber, configuration, ruleNode);
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
