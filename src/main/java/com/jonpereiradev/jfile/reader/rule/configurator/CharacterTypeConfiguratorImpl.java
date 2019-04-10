package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;
import com.jonpereiradev.jfile.reader.rule.RuleNode;
import com.jonpereiradev.jfile.reader.rule.column.ColumnRule;
import com.jonpereiradev.jfile.reader.rule.column.DomainCharacterRule;

import java.util.Arrays;

final class CharacterTypeConfiguratorImpl
    extends AbstractRuleConfigurator<CharacterTypeConfigurator> implements CharacterTypeConfigurator {

    CharacterTypeConfiguratorImpl(int position, ReaderConfiguration configuration, RuleNode<ColumnRule> ruleNode) {
        super(position, configuration, ruleNode);
    }

    @Override
    public CharacterTypeConfigurator domain(Character... values) {
        return rule(position -> new DomainCharacterRule(position, Arrays.asList(values)));
    }
}
