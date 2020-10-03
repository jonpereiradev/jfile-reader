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
