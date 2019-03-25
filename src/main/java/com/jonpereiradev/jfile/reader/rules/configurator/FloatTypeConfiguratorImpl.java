package com.jonpereiradev.jfile.reader.rules.configurator;

import com.jonpereiradev.jfile.reader.rules.RuleConfiguratorContext;
import com.jonpereiradev.jfile.reader.rules.column.MaxFloatRule;
import com.jonpereiradev.jfile.reader.rules.column.MinFloatRule;

final class FloatTypeConfiguratorImpl extends TypedRuleConfiguratorImpl<FloatTypeConfigurator> implements FloatTypeConfigurator {

    FloatTypeConfiguratorImpl(int position, RuleConfiguratorContext context) {
        super(position, context);
    }

    @Override
    public FloatTypeConfigurator min(float min) {
        return rule(new MinFloatRule(position, min));
    }

    @Override
    public FloatTypeConfigurator max(float max) {
        return rule(new MaxFloatRule(position, max));
    }

}
