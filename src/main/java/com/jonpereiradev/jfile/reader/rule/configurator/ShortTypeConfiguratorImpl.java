package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;
import com.jonpereiradev.jfile.reader.rule.RuleNode;
import com.jonpereiradev.jfile.reader.rule.column.ColumnRule;
import com.jonpereiradev.jfile.reader.rule.column.DomainShortRule;
import com.jonpereiradev.jfile.reader.rule.column.MaxShortRule;
import com.jonpereiradev.jfile.reader.rule.column.MinShortRule;

import java.util.Arrays;

final class ShortTypeConfiguratorImpl extends AbstractRuleConfigurator<ShortTypeConfigurator> implements ShortTypeConfigurator {

    ShortTypeConfiguratorImpl(int position, ReaderConfiguration configuration, RuleNode<ColumnRule> ruleNode) {
        super(position, configuration, ruleNode);
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
