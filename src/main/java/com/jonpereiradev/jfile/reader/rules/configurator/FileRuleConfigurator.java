package com.jonpereiradev.jfile.reader.rules.configurator;

import com.jonpereiradev.jfile.reader.rules.RuleConfiguratorContext;

public interface FileRuleConfigurator {

    static FileRuleConfigurator defaultConfigurator(RuleConfiguratorContext context) {
        return new FileRuleConfiguratorImpl(context);
    }

    LineRuleConfigurator lines();
}
