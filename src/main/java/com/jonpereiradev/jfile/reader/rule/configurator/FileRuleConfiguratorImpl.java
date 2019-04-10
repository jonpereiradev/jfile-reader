package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;

final class FileRuleConfiguratorImpl implements FileRuleConfigurator {

    private final ReaderConfiguration configuration;

    FileRuleConfiguratorImpl(ReaderConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public LineRuleConfigurator lines() {
        return new LineRuleConfiguratorImpl(configuration);
    }

}
