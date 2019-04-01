package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.rule.RuleConfiguratorContext;
import com.jonpereiradev.jfile.reader.rule.column.MaxFloatRule;
import com.jonpereiradev.jfile.reader.rule.column.MinFloatRule;

final class FloatTypeConfiguratorImpl extends AbstractRuleConfigurator<FloatTypeConfigurator> implements FloatTypeConfigurator {

    FloatTypeConfiguratorImpl(int position, RuleConfiguratorContext context) {
        super(position, context);
    }

    @Override
    public FloatTypeConfigurator min(float min) {
        return rule(position -> new MinFloatRule(position, min));
    }

    @Override
    public FloatTypeConfigurator max(float max) {
        return rule(position -> new MaxFloatRule(position, max));
    }

}
