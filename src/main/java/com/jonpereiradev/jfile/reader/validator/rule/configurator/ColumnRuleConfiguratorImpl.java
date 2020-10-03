package com.jonpereiradev.jfile.reader.validator.rule.configurator;

import com.jonpereiradev.jfile.reader.validator.JFileValidatorConfig;

final class ColumnRuleConfiguratorImpl implements ColumnRuleConfigurator {

    private final JFileValidatorConfig configuration;

    ColumnRuleConfiguratorImpl(JFileValidatorConfig configuration) {
        this.configuration = configuration;
    }

    @Override
    public GenericTypeConfigurator column(int position) {
        return new GenericTypeConfiguratorImpl(
            position,
            configuration,
            configuration.getRuleRoot().getColumnRootNode()
        );
    }

}
