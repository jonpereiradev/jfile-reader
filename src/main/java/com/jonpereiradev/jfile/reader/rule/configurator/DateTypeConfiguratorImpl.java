package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.rule.RuleConfiguratorContext;
import com.jonpereiradev.jfile.reader.rule.column.DateFutureOrPresentRule;
import com.jonpereiradev.jfile.reader.rule.column.DateFutureRule;
import com.jonpereiradev.jfile.reader.rule.column.DatePastOrPresentRule;
import com.jonpereiradev.jfile.reader.rule.column.DatePastRule;

import java.text.DateFormat;

final class DateTypeConfiguratorImpl extends AbstractRuleConfigurator<DateTypeConfigurator> implements DateTypeConfigurator {

    private final DateFormat dateFormat;

    DateTypeConfiguratorImpl(int position, DateFormat dateFormat, RuleConfiguratorContext context) {
        super(position, context);
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
}
