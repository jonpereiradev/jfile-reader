package com.jonpereiradev.jfile.reader.rules.configurator;

import com.jonpereiradev.jfile.reader.rules.RuleConfiguratorContext;
import com.jonpereiradev.jfile.reader.rules.column.MaxDoubleRule;
import com.jonpereiradev.jfile.reader.rules.column.MinDoubleRule;

final class DoubleTypeConfiguratorImpl extends TypedRuleConfiguratorImpl<DoubleTypeConfigurator> implements DoubleTypeConfigurator {

    DoubleTypeConfiguratorImpl(int position, RuleConfiguratorContext context) {
        super(position, context);
    }

    @Override
    public DoubleTypeConfigurator min(double min) {
        return rule(position -> new MinDoubleRule(position, min));
    }

    @Override
    public DoubleTypeConfigurator max(double max) {
        return rule(position -> new MaxDoubleRule(position, max));
    }
}
