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
import com.jonpereiradev.jfile.reader.validator.rule.column.LocalDateTimeAfterRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.LocalDateTimeBeforeRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.LocalDateTimeFutureOrPresentRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.LocalDateTimeFutureRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.LocalDateTimePastOrPresentRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.LocalDateTimePastRule;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


final class LocalDateTimeTypeConfiguratorImpl
    extends AbstractRuleConfigurator<LocalDateTimeTypeConfigurator> implements LocalDateTimeTypeConfigurator {

    private final DateTimeFormatter dateTimeFormatter;

    LocalDateTimeTypeConfiguratorImpl(
        int columnNumber,
        DateTimeFormatter dateTimeFormatter,
        JFileValidatorConfig configuration,
        RuleNode<ColumnRule> ruleNode
    ) {
        super(columnNumber, configuration, ruleNode);
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public LocalDateTimeTypeConfigurator future() {
        return rule(columnNumber -> new LocalDateTimeFutureRule(columnNumber, dateTimeFormatter));
    }

    @Override
    public LocalDateTimeTypeConfigurator futureOrPresent() {
        return rule(columnNumber -> new LocalDateTimeFutureOrPresentRule(columnNumber, dateTimeFormatter));
    }

    @Override
    public LocalDateTimeTypeConfigurator past() {
        return rule(columnNumber -> new LocalDateTimePastRule(columnNumber, dateTimeFormatter));
    }

    @Override
    public LocalDateTimeTypeConfigurator pastOrPresent() {
        return rule(columnNumber -> new LocalDateTimePastOrPresentRule(columnNumber, dateTimeFormatter));
    }

    @Override
    public LocalDateTimeTypeConfigurator after(LocalDateTime min) {
        return rule(columnNumber -> new LocalDateTimeAfterRule(columnNumber, dateTimeFormatter, min));
    }

    @Override
    public LocalDateTimeTypeConfigurator after(int column) {
        return rule(columnNumber -> new LocalDateTimeAfterRule(columnNumber, dateTimeFormatter, column));
    }

    @Override
    public LocalDateTimeTypeConfigurator before(LocalDateTime max) {
        return rule(columnNumber -> new LocalDateTimeBeforeRule(columnNumber, dateTimeFormatter, max));
    }

    @Override
    public LocalDateTimeTypeConfigurator before(int column) {
        return rule(columnNumber -> new LocalDateTimeBeforeRule(columnNumber, dateTimeFormatter, column));
    }

}
