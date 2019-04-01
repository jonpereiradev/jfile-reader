package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.rule.RuleConfiguratorContext;
import com.jonpereiradev.jfile.reader.rule.column.DomainCharacterRule;

import java.util.Arrays;

final class CharacterTypeConfiguratorImpl
    extends AbstractRuleConfigurator<CharacterTypeConfigurator> implements CharacterTypeConfigurator {

    CharacterTypeConfiguratorImpl(int position, RuleConfiguratorContext context) {
        super(position, context);
    }

    @Override
    public CharacterTypeConfigurator domain(Character... values) {
        return rule(position -> new DomainCharacterRule(position, Arrays.asList(values)));
    }
}
