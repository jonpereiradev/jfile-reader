package com.jonpereiradev.jfile.reader.validator.rule.configurator;

import com.jonpereiradev.jfile.reader.validator.JFileValidatorConfig;
import com.jonpereiradev.jfile.reader.validator.rule.RuleNode;
import com.jonpereiradev.jfile.reader.validator.rule.column.ColumnRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.DomainCharacterRule;

import java.util.Arrays;

final class CharacterTypeConfiguratorImpl
    extends AbstractRuleConfigurator<CharacterTypeConfigurator> implements CharacterTypeConfigurator {

    CharacterTypeConfiguratorImpl(
        int position,
        JFileValidatorConfig configuration,
        RuleNode<ColumnRule> ruleNode) {
        super(position, configuration, ruleNode);
    }

    @Override
    public CharacterTypeConfigurator domain(Character... domains) {
        return rule(position -> new DomainCharacterRule(position, Arrays.asList(domains)));
    }
}
