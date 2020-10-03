package com.jonpereiradev.jfile.reader.validator.rule.configurator;

import com.jonpereiradev.jfile.reader.JFileReaderConfig;
import com.jonpereiradev.jfile.reader.validator.JFileValidatorConfig;
import com.jonpereiradev.jfile.reader.validator.rule.RuleNode;
import com.jonpereiradev.jfile.reader.validator.rule.column.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

final class LocalDateTimeTypeConfiguratorImpl
    extends AbstractRuleConfigurator<LocalDateTimeTypeConfigurator> implements LocalDateTimeTypeConfigurator {

    private final DateTimeFormatter dateTimeFormatter;

    LocalDateTimeTypeConfiguratorImpl(
        int position,
        DateTimeFormatter dateTimeFormatter,
        JFileValidatorConfig configuration,
        RuleNode<ColumnRule> ruleNode
    ) {
        super(position, configuration, ruleNode);
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public LocalDateTimeTypeConfigurator future() {
        return rule(position -> new LocalDateTimeFutureRule(position, dateTimeFormatter));
    }

    @Override
    public LocalDateTimeTypeConfigurator futureOrPresent() {
        return rule(position -> new LocalDateTimeFutureOrPresentRule(position, dateTimeFormatter));
    }

    @Override
    public LocalDateTimeTypeConfigurator past() {
        return rule(position -> new LocalDateTimePastRule(position, dateTimeFormatter));
    }

    @Override
    public LocalDateTimeTypeConfigurator pastOrPresent() {
        return rule(position -> new LocalDateTimePastOrPresentRule(position, dateTimeFormatter));
    }

    @Override
    public LocalDateTimeTypeConfigurator after(LocalDateTime min) {
        return rule(position -> new LocalDateTimeAfterRule(position, dateTimeFormatter, min));
    }

    @Override
    public LocalDateTimeTypeConfigurator after(int column) {
        return rule(position -> new LocalDateTimeAfterRule(position, dateTimeFormatter, column));
    }

    @Override
    public LocalDateTimeTypeConfigurator before(LocalDateTime max) {
        return rule(position -> new LocalDateTimeBeforeRule(position, dateTimeFormatter, max));
    }

    @Override
    public LocalDateTimeTypeConfigurator before(int column) {
        return rule(position -> new LocalDateTimeBeforeRule(position, dateTimeFormatter, column));
    }
}
