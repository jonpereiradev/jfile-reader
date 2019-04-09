package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.rule.RuleConfiguratorContext;
import com.jonpereiradev.jfile.reader.rule.RuleNode;
import com.jonpereiradev.jfile.reader.rule.column.ColumnRule;
import com.jonpereiradev.jfile.reader.rule.column.DomainCharacterRule;

import java.util.Arrays;

final class BooleanTypeConfiguratorImpl extends AbstractRuleConfigurator<BooleanTypeConfigurator> implements BooleanTypeConfigurator {

    BooleanTypeConfiguratorImpl(int position, RuleConfiguratorContext context, RuleNode<ColumnRule> ruleNode) {
        super(position, context, ruleNode);
    }

    @Override
    public BooleanTypeConfigurator domain(Character... values) {
        return rule(position -> new DomainCharacterRule(position, Arrays.asList(values)));
    }
}
