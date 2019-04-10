package com.jonpereiradev.jfile.reader.rule;

import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;
import com.jonpereiradev.jfile.reader.rule.configurator.FileRuleConfigurator;

public interface RuleConfigurator {

    static RuleConfigurator defaultConfigurator(ReaderConfiguration readerConfiguration) {
        readerConfiguration.withRuleConfiguration(new RuleConfigurationImpl());
        return new RuleConfiguratorImpl(readerConfiguration);
    }

    FileRuleConfigurator files();

}
