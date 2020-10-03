package com.jonpereiradev.jfile.reader.validator.rule.configurator;

import com.jonpereiradev.jfile.reader.JFileReaderConfig;
import com.jonpereiradev.jfile.reader.validator.JFileValidatorConfig;
import com.jonpereiradev.jfile.reader.validator.rule.RuleNode;
import com.jonpereiradev.jfile.reader.validator.rule.column.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

final class LocalDateTypeConfiguratorImpl
    extends AbstractRuleConfigurator<LocalDateTypeConfigurator> implements LocalDateTypeConfigurator {

    private final DateTimeFormatter dateTimeFormatter;

    LocalDateTypeConfiguratorImpl(
        int position,
        DateTimeFormatter dateTimeFormatter,
        JFileValidatorConfig configuration,
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
    public LocalDateTypeConfigurator after(LocalDate min) {
        return rule(position -> new LocalDateAfterRule(position, dateTimeFormatter, min));
    }

    @Override
    public LocalDateTypeConfigurator after(int columnPosition) {
        return rule(position -> new LocalDateAfterRule(position, dateTimeFormatter, columnPosition));
    }

    @Override
    public LocalDateTypeConfigurator before(LocalDate max) {
        return rule(position -> new LocalDateBeforeRule(position, dateTimeFormatter, max));
    }

    @Override
    public LocalDateTypeConfigurator before(int columnPosition) {
        return rule(position -> new LocalDateBeforeRule(position, dateTimeFormatter, columnPosition));
    }


}
