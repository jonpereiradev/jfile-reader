package com.jonpereiradev.jfile.reader.rules.configurator;

import com.jonpereiradev.jfile.reader.rules.RuleConfiguratorContext;
import com.jonpereiradev.jfile.reader.rules.column.DomainCharacterRule;

import java.util.Arrays;

final class BooleanTypeConfiguratorImpl extends AbstractRuleConfigurator<BooleanTypeConfigurator> implements BooleanTypeConfigurator {

    BooleanTypeConfiguratorImpl(int position, RuleConfiguratorContext context) {
        super(position, context);
    }

    @Override
    public BooleanTypeConfigurator domain(Character... values) {
        return rule(position -> new DomainCharacterRule(position, Arrays.asList(values)));
    }
}
