package com.jonpereiradev.jfile.reader.rules.configurator;

import com.jonpereiradev.jfile.reader.rules.RuleConfiguratorContext;
import com.jonpereiradev.jfile.reader.rules.column.LocalDateFutureOrPresentRule;
import com.jonpereiradev.jfile.reader.rules.column.LocalDateFutureRule;
import com.jonpereiradev.jfile.reader.rules.column.LocalDatePastOrPresentRule;
import com.jonpereiradev.jfile.reader.rules.column.LocalDatePastRule;

import java.time.format.DateTimeFormatter;

final class LocalDateTypeConfiguratorImpl
    extends TypedRuleConfiguratorImpl<LocalDateTypeConfigurator> implements LocalDateTypeConfigurator {

    private final DateTimeFormatter dateTimeFormatter;

    LocalDateTypeConfiguratorImpl(int position, DateTimeFormatter dateTimeFormatter, RuleConfiguratorContext context) {
        super(position, context);
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public LocalDateTypeConfigurator future() {
        return rule(new LocalDateFutureRule(position, dateTimeFormatter));
    }

    @Override
    public LocalDateTypeConfigurator futureOrPresent() {
        return rule(new LocalDateFutureOrPresentRule(position, dateTimeFormatter));
    }

    @Override
    public LocalDateTypeConfigurator past() {
        return rule(new LocalDatePastRule(position, dateTimeFormatter));
    }

    @Override
    public LocalDateTypeConfigurator pastOrPresent() {
        return rule(new LocalDatePastOrPresentRule(position, dateTimeFormatter));
    }
}
