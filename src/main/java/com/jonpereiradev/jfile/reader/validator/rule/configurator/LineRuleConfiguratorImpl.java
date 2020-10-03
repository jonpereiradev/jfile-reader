package com.jonpereiradev.jfile.reader.validator.rule.configurator;

import com.jonpereiradev.jfile.reader.validator.JFileValidatorConfig;
import com.jonpereiradev.jfile.reader.validator.rule.line.LineColumnSizeRule;

final class LineRuleConfiguratorImpl implements LineRuleConfigurator {

    private final JFileValidatorConfig configuration;

    LineRuleConfiguratorImpl(JFileValidatorConfig configuration) {
        this.configuration = configuration;
    }

    @Override
    public LineRuleConfigurator columnsSize(int size) {
        configuration.getRuleRoot().getLineRootNode().add(new LineColumnSizeRule(size));
        return this;
    }

    @Override
    public ColumnRuleConfigurator columns() {
        return new ColumnRuleConfiguratorImpl(configuration);
    }

}
