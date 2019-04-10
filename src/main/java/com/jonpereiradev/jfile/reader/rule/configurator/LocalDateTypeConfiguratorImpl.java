package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;
import com.jonpereiradev.jfile.reader.rule.RuleNode;
import com.jonpereiradev.jfile.reader.rule.column.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

final class LocalDateTypeConfiguratorImpl
    extends AbstractRuleConfigurator<LocalDateTypeConfigurator> implements LocalDateTypeConfigurator {

    private final DateTimeFormatter dateTimeFormatter;

    LocalDateTypeConfiguratorImpl(
        int position,
        DateTimeFormatter dateTimeFormatter,
        ReaderConfiguration configuration,
        RuleNode<ColumnRule> ruleNode
    ) {
        super(position, configuration, ruleNode);
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

    @Override
    public LocalDateTypeConfigurator min(LocalDate min) {
        return rule(position -> new LocalDateMinRule(position, dateTimeFormatter, min));
    }

    @Override
    public LocalDateTypeConfigurator max(LocalDate max) {
        return rule(position -> new LocalDateMaxRule(position, dateTimeFormatter, max));
    }
}
