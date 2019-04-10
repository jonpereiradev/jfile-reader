package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;
import com.jonpereiradev.jfile.reader.rule.RuleNode;
import com.jonpereiradev.jfile.reader.rule.column.*;

import java.text.DateFormat;
import java.util.Date;

final class DateTypeConfiguratorImpl extends AbstractRuleConfigurator<DateTypeConfigurator> implements DateTypeConfigurator {

    private final DateFormat dateFormat;

    DateTypeConfiguratorImpl(int position, DateFormat dateFormat, ReaderConfiguration configuration, RuleNode<ColumnRule> ruleNode) {
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
    public DateTypeConfigurator after(Date min) {
        return rule(position -> new DateAfterRule(position, dateFormat, min));
    }

    @Override
    public DateTypeConfigurator after(int columnPosition) {
        return rule(position -> new DateAfterRule(position, dateFormat, columnPosition));
    }

    @Override
    public DateTypeConfigurator before(Date max) {
        return rule(position -> new DateBeforeRule(position, dateFormat, max));
    }

    @Override
    public DateTypeConfigurator before(int columnPosition) {
        return rule(position -> new DateBeforeRule(position, dateFormat, columnPosition));
    }
}
