package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.rule.RuleConfiguratorContext;
import com.jonpereiradev.jfile.reader.rule.column.LocalDateFutureOrPresentRule;
import com.jonpereiradev.jfile.reader.rule.column.LocalDateFutureRule;
import com.jonpereiradev.jfile.reader.rule.column.LocalDatePastOrPresentRule;
import com.jonpereiradev.jfile.reader.rule.column.LocalDatePastRule;

import java.time.format.DateTimeFormatter;

final class LocalDateTypeConfiguratorImpl
    extends AbstractRuleConfigurator<LocalDateTypeConfigurator> implements LocalDateTypeConfigurator {

    private final DateTimeFormatter dateTimeFormatter;

    LocalDateTypeConfiguratorImpl(int position, DateTimeFormatter dateTimeFormatter, RuleConfiguratorContext context) {
        super(position, context);
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public LocalDateTypeConfigurator future() {
        return rule(position -> new LocalDateFutureRule(position, dateTimeFormatter));
    }

    @Override
    public LocalDateTypeConfigurator futureOrPresent() {
        return rule(position -> new LocalDateFutureOrPresentRule(position, dateTimeFormatter));
    }

    @Override
    public LocalDateTypeConfigurator past() {
        return rule(position -> new LocalDatePastRule(position, dateTimeFormatter));
    }

    @Override
    public LocalDateTypeConfigurator pastOrPresent() {
        return rule(position -> new LocalDatePastOrPresentRule(position, dateTimeFormatter));
    }
}
