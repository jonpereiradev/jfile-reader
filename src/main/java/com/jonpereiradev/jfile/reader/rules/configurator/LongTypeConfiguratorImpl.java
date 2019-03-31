package com.jonpereiradev.jfile.reader.rules.configurator;

import com.jonpereiradev.jfile.reader.rules.RuleConfiguratorContext;
import com.jonpereiradev.jfile.reader.rules.column.DomainLongRule;
import com.jonpereiradev.jfile.reader.rules.column.MaxLongRule;
import com.jonpereiradev.jfile.reader.rules.column.MinLongRule;

import java.util.Arrays;

final class LongTypeConfiguratorImpl extends AbstractRuleConfigurator<LongTypeConfigurator> implements LongTypeConfigurator {

    LongTypeConfiguratorImpl(int position, RuleConfiguratorContext context) {
        super(position, context);
    }

    @Override
    public LongTypeConfigurator min(long min) {
        return rule(position -> new MinLongRule(position, min));
    }

    @Override
    public LongTypeConfigurator max(long max) {
        return rule(position -> new MaxLongRule(position, max));
    }

    @Override
    public LongTypeConfigurator domain(Long... values) {
        return rule(position -> new DomainLongRule(position, Arrays.asList(values)));
    }
}
