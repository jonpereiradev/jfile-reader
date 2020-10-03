package com.jonpereiradev.jfile.reader.validator.rule.configurator;

import com.jonpereiradev.jfile.reader.validator.JFileValidatorConfig;

final class FileRuleConfiguratorImpl implements FileRuleConfigurator {

    private final JFileValidatorConfig configuration;

    FileRuleConfiguratorImpl(JFileValidatorConfig configuration) {
        this.configuration = configuration;
    }

    @Override
    public LineRuleConfigurator lines() {
        return new LineRuleConfiguratorImpl(configuration);
    }

}
