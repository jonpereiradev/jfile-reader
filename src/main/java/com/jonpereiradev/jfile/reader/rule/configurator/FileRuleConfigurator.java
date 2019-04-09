package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.rule.RuleConfiguratorContext;

public interface FileRuleConfigurator {

    static FileRuleConfigurator defaultConfigurator(RuleConfiguratorContext context) {
        return new FileRuleConfiguratorImpl(context);
    }

    /**
     * creates the rule configurator for lines.
     */
    LineRuleConfigurator lines();
}
