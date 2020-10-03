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
import com.jonpereiradev.jfile.reader.validator.rule.column.DateAfterRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.DateBeforeRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.DateFutureOrPresentRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.DateFutureRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.DatePastOrPresentRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.DatePastRule;

import java.text.DateFormat;
import java.util.Date;

final class DateTypeConfiguratorImpl extends AbstractRuleConfigurator<DateTypeConfigurator> implements DateTypeConfigurator {

    private final DateFormat dateFormat;

    DateTypeConfiguratorImpl(
        int position,
        DateFormat dateFormat,
        JFileValidatorConfig configuration, RuleNode<ColumnRule> ruleNode) {
        super(position, configuration, ruleNode);
        this.dateFormat = dateFormat;
    }

    @Override
    public DateTypeConfigurator future() {
        return rule(position -> new DateFutureRule(position, dateFormat));
    }

    @Override
    public DateTypeConfigurator futureOrPresent() {
        return rule(position -> new DateFutureOrPresentRule(position, dateFormat));
    }

    @Override
    public DateTypeConfigurator past() {
        return rule(position -> new DatePastRule(position, dateFormat));
    }

    @Override
    public DateTypeConfigurator pastOrPresent() {
        return rule(position -> new DatePastOrPresentRule(position, dateFormat));
    }

    @Override
    public DateTypeConfigurator after(Date date) {
        return rule(position -> new DateAfterRule(position, dateFormat, date));
    }

    @Override
    public DateTypeConfigurator after(int columnPosition) {
        return rule(position -> new DateAfterRule(position, dateFormat, columnPosition));
    }

    @Override
    public DateTypeConfigurator before(Date date) {
        return rule(position -> new DateBeforeRule(position, dateFormat, date));
    }

    @Override
    public DateTypeConfigurator before(int columnPosition) {
        return rule(position -> new DateBeforeRule(position, dateFormat, columnPosition));
    }
}
