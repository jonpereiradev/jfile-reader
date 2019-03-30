package com.jonpereiradev.jfile.reader.rules.configurator;

import com.jonpereiradev.jfile.reader.rules.RuleConfiguratorContext;
import com.jonpereiradev.jfile.reader.rules.column.DomainShortRule;
import com.jonpereiradev.jfile.reader.rules.column.MaxShortRule;
import com.jonpereiradev.jfile.reader.rules.column.MinShortRule;

import java.util.Arrays;

final class ShortTypeConfiguratorImpl extends TypedRuleConfiguratorImpl<ShortTypeConfigurator> implements ShortTypeConfigurator {

    ShortTypeConfiguratorImpl(int position, RuleConfiguratorContext context) {
        super(position, context);
    }

    @Override
    public ShortTypeConfigurator min(short min) {
        return rule(position -> new MinShortRule(position, min));
    }

    @Override
    public ShortTypeConfigurator max(short max) {
        return rule(position -> new MaxShortRule(position, max));
    }

    @Override
    public ShortTypeConfigurator domain(Short... values) {
        return rule(position -> new DomainShortRule(position, Arrays.asList(values)));
    }
}
