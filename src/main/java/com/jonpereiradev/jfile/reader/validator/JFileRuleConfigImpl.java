package com.jonpereiradev.jfile.reader.validator;

import com.jonpereiradev.jfile.reader.validator.rule.configurator.ColumnRuleConfigurator;
import com.jonpereiradev.jfile.reader.validator.rule.configurator.FileRuleConfigurator;
import com.jonpereiradev.jfile.reader.validator.rule.configurator.LineRuleConfigurator;

final class JFileRuleConfigImpl implements JFileRuleConfig {

    private final JFileValidatorConfig configuration;

    JFileRuleConfigImpl(JFileValidatorConfig configuration) {
        this.configuration = configuration;
    }

    @Override
    public FileRuleConfigurator files() {
        return FileRuleConfigurator.defaultConfigurator(configuration);
    }

    @Override
    public LineRuleConfigurator lines() {
        return LineRuleConfigurator.defaultConfigurator(configuration);
    }

    @Override
    public ColumnRuleConfigurator columns() {
        return ColumnRuleConfigurator.defaultConfigurator(configuration);
    }

}
