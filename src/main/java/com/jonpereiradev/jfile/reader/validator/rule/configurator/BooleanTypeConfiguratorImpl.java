package com.jonpereiradev.jfile.reader.validator.rule.configurator;

import com.jonpereiradev.jfile.reader.validator.JFileValidatorConfig;
import com.jonpereiradev.jfile.reader.validator.rule.RuleNode;
import com.jonpereiradev.jfile.reader.validator.rule.column.ColumnRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.DomainCharacterRule;

import java.util.Arrays;

final class BooleanTypeConfiguratorImpl extends AbstractRuleConfigurator<BooleanTypeConfigurator> implements BooleanTypeConfigurator {

    BooleanTypeConfiguratorImpl(
        int position,
        JFileValidatorConfig configuration,
        RuleNode<ColumnRule> ruleNode) {
        super(position, configuration, ruleNode);
    }

    @Override
    public BooleanTypeConfigurator domain(Character... domains) {
        return rule(position -> new DomainCharacterRule(position, Arrays.asList(domains)));
    }
}
