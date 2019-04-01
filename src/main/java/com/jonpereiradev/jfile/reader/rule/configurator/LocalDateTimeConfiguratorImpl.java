package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.rule.RuleConfiguratorContext;
import com.jonpereiradev.jfile.reader.rule.column.LocalDateTimeFutureOrPresentRule;
import com.jonpereiradev.jfile.reader.rule.column.LocalDateTimeFutureRule;
import com.jonpereiradev.jfile.reader.rule.column.LocalDateTimePastOrPresentRule;
import com.jonpereiradev.jfile.reader.rule.column.LocalDateTimePastRule;

import java.time.format.DateTimeFormatter;

final class LocalDateTimeConfiguratorImpl
    extends AbstractRuleConfigurator<LocalDateTimeConfigurator> implements LocalDateTimeConfigurator {

    private final DateTimeFormatter dateTimeFormatter;

    LocalDateTimeConfiguratorImpl(int position, DateTimeFormatter dateTimeFormatter, RuleConfiguratorContext context) {
        super(position, context);
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public LocalDateTimeConfigurator future() {
        return rule(position -> new LocalDateTimeFutureRule(position, dateTimeFormatter));
    }

    @Override
    public LocalDateTimeConfigurator futureOrPresent() {
        return rule(position -> new LocalDateTimeFutureOrPresentRule(position, dateTimeFormatter));
    }

    @Override
    public LocalDateTimeConfigurator past() {
        return rule(position -> new LocalDateTimePastRule(position, dateTimeFormatter));
    }

    @Override
    public LocalDateTimeConfigurator pastOrPresent() {
        return rule(position -> new LocalDateTimePastOrPresentRule(position, dateTimeFormatter));
    }
}
