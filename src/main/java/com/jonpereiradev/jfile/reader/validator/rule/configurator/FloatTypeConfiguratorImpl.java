package com.jonpereiradev.jfile.reader.validator.rule.configurator;

import com.jonpereiradev.jfile.reader.validator.JFileValidatorConfig;
import com.jonpereiradev.jfile.reader.validator.rule.RuleNode;
import com.jonpereiradev.jfile.reader.validator.rule.column.ColumnRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.MaxFloatRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.MinFloatRule;

final class FloatTypeConfiguratorImpl extends AbstractRuleConfigurator<FloatTypeConfigurator> implements FloatTypeConfigurator {

    FloatTypeConfiguratorImpl(
        int position,
        JFileValidatorConfig configuration,
        RuleNode<ColumnRule> ruleNode) {
        super(position, configuration, ruleNode);
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
