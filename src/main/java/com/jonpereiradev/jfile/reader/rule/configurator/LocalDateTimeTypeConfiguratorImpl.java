package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.rule.RuleConfiguratorContext;
import com.jonpereiradev.jfile.reader.rule.RuleNode;
import com.jonpereiradev.jfile.reader.rule.column.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

final class LocalDateTimeTypeConfiguratorImpl
    extends AbstractRuleConfigurator<LocalDateTimeTypeConfigurator> implements LocalDateTimeTypeConfigurator {

    private final DateTimeFormatter dateTimeFormatter;

    LocalDateTimeTypeConfiguratorImpl(
        int position,
        DateTimeFormatter dateTimeFormatter,
        RuleConfiguratorContext context,
        RuleNode<ColumnRule> ruleNode
    ) {
        super(position, context, ruleNode);
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
    public LocalDateTimeTypeConfigurator min(LocalDateTime min) {
        return rule(position -> new LocalDateTimeMinRule(position, dateTimeFormatter, min));
    }

    @Override
    public LocalDateTimeTypeConfigurator max(LocalDateTime max) {
        return rule(position -> new LocalDateTimeMaxRule(position, dateTimeFormatter, max));
    }
}
