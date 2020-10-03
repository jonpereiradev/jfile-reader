package com.jonpereiradev.jfile.reader.validator.rule.configurator;

import com.jonpereiradev.jfile.reader.validator.JFileValidatorConfig;

public interface FileRuleConfigurator {

    static FileRuleConfigurator defaultConfigurator(JFileValidatorConfig configuration) {
        return new FileRuleConfiguratorImpl(configuration);
    }

    /**
     * creates the rule configurator for lines.
     */
    LineRuleConfigurator lines();

}
