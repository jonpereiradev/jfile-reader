package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.rule.RuleConfiguratorContext;
import com.jonpereiradev.jfile.reader.rule.RuleNode;
import com.jonpereiradev.jfile.reader.rule.column.ColumnRule;
import com.jonpereiradev.jfile.reader.rule.column.DomainLongRule;
import com.jonpereiradev.jfile.reader.rule.column.MaxLongRule;
import com.jonpereiradev.jfile.reader.rule.column.MinLongRule;

import java.util.Arrays;

final class LongTypeConfiguratorImpl extends AbstractRuleConfigurator<LongTypeConfigurator> implements LongTypeConfigurator {

    LongTypeConfiguratorImpl(int position, RuleConfiguratorContext context, RuleNode<ColumnRule> ruleNode) {
        super(position, context, ruleNode);
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
