package com.jonpereiradev.jfile.reader.rules;

import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;
import com.jonpereiradev.jfile.reader.rules.configurator.FileRuleConfigurator;

public interface RuleConfigurator {

    static RuleConfigurator defaultConfigurator(ReaderConfiguration readerConfiguration) {
        return new RuleConfiguratorImpl(new RuleConfiguratorContext(readerConfiguration, new RuleConfigurationImpl()));
    }

    FileRuleConfigurator files();

}
