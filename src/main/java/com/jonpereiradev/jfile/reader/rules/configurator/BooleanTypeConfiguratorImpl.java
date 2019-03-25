package com.jonpereiradev.jfile.reader.rules.configurator;

import com.jonpereiradev.jfile.reader.rules.RuleConfiguratorContext;

final class BooleanTypeConfiguratorImpl extends TypedRuleConfiguratorImpl<BooleanTypeConfigurator> implements BooleanTypeConfigurator {

    public BooleanTypeConfiguratorImpl(int position, RuleConfiguratorContext context) {
        super(position, context);
    }
}
