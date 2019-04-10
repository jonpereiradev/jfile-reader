package com.jonpereiradev.jfile.reader.rule;

import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;
import com.jonpereiradev.jfile.reader.rule.configurator.FileRuleConfigurator;

final class RuleConfiguratorImpl implements RuleConfigurator {

    private final ReaderConfiguration configuration;

    RuleConfiguratorImpl(ReaderConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public FileRuleConfigurator files() {
        return FileRuleConfigurator.defaultConfigurator(configuration);
    }
}
