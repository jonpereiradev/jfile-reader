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
    public DateTypeConfigurator min(Date min) {
        return rule(position -> new DateMinRule(position, dateFormat, min));
    }

    @Override
    public DateTypeConfigurator max(Date max) {
        return rule(position -> new DateMaxRule(position, dateFormat, max));
    }
}
