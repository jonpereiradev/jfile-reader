package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;
import com.jonpereiradev.jfile.reader.rule.RuleNode;
import com.jonpereiradev.jfile.reader.rule.column.ColumnRule;
import com.jonpereiradev.jfile.reader.rule.column.DomainIntegerRule;
import com.jonpereiradev.jfile.reader.rule.column.MaxIntegerRule;
import com.jonpereiradev.jfile.reader.rule.column.MinIntegerRule;

import java.util.Arrays;

final class IntegerTypeConfiguratorImpl extends AbstractRuleConfigurator<IntegerTypeConfigurator> implements IntegerTypeConfigurator {

    IntegerTypeConfiguratorImpl(int position, ReaderConfiguration configuration, RuleNode<ColumnRule> ruleNode) {
        super(position, configuration, ruleNode);
    }

    @Override
    public IntegerTypeConfigurator min(int min) {
        return rule(position -> new MinIntegerRule(position, min));
    }

    @Override
    public IntegerTypeConfigurator max(int max) {
        return rule(position -> new MaxIntegerRule(position, max));
    }

    @Override
    public IntegerTypeConfigurator domain(Integer... values) {
        return rule(position -> new DomainIntegerRule(position, Arrays.asList(values)));
    }

}
