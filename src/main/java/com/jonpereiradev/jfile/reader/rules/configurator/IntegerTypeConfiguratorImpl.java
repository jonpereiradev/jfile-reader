package com.jonpereiradev.jfile.reader.rules.configurator;

import com.jonpereiradev.jfile.reader.rules.RuleConfiguratorContext;
import com.jonpereiradev.jfile.reader.rules.column.DomainIntegerRule;
import com.jonpereiradev.jfile.reader.rules.column.MaxIntegerRule;
import com.jonpereiradev.jfile.reader.rules.column.MinIntegerRule;

import java.util.Arrays;

final class IntegerTypeConfiguratorImpl extends TypedRuleConfiguratorImpl<IntegerTypeConfigurator> implements IntegerTypeConfigurator {

    IntegerTypeConfiguratorImpl(int position, RuleConfiguratorContext context) {
        super(position, context);
    }

    @Override
    public IntegerTypeConfigurator min(int min) {
        return rule(new MinIntegerRule(position, min));
    }

    @Override
    public IntegerTypeConfigurator max(int max) {
        return rule(new MaxIntegerRule(position, max));
    }

    @Override
    public IntegerTypeConfigurator domain(Integer... values) {
        return rule(new DomainIntegerRule(position, Arrays.asList(values)));
    }

}
