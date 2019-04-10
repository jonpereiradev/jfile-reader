package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;
import com.jonpereiradev.jfile.reader.rule.RuleNode;
import com.jonpereiradev.jfile.reader.rule.column.ColumnRule;
import com.jonpereiradev.jfile.reader.rule.column.MaxDoubleRule;
import com.jonpereiradev.jfile.reader.rule.column.MinDoubleRule;

final class DoubleTypeConfiguratorImpl extends AbstractRuleConfigurator<DoubleTypeConfigurator> implements DoubleTypeConfigurator {

    DoubleTypeConfiguratorImpl(int position, ReaderConfiguration configuration, RuleNode<ColumnRule> ruleNode) {
        super(position, configuration, ruleNode);
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
