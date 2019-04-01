package com.jonpereiradev.jfile.reader.rule;

import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;
import com.jonpereiradev.jfile.reader.rule.configurator.FileRuleConfigurator;

public interface RuleConfigurator {

    static RuleConfigurator defaultConfigurator(ReaderConfiguration readerConfiguration) {
        return new RuleConfiguratorImpl(new RuleConfiguratorContext(readerConfiguration, new RuleConfigurationImpl()));
    }

    FileRuleConfigurator files();

}
