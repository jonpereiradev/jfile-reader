package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;

public interface FileRuleConfigurator {

    static FileRuleConfigurator defaultConfigurator(ReaderConfiguration configuration) {
        return new FileRuleConfiguratorImpl(configuration);
    }

    /**
     * creates the rule configurator for lines.
     */
    LineRuleConfigurator lines();
}
