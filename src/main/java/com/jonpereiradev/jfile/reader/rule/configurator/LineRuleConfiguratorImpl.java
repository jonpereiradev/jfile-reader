package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;
import com.jonpereiradev.jfile.reader.rule.line.LineColumnSizeRule;

final class LineRuleConfiguratorImpl implements LineRuleConfigurator {

    private final ReaderConfiguration configuration;

    LineRuleConfiguratorImpl(ReaderConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public LineRuleConfigurator columns(int size) {
        configuration.getRuleConfiguration().getLineRootNode().add(new LineColumnSizeRule(size));
        return this;
    }

    @Override
    public GenericTypeConfigurator column(int position) {
        return new GenericTypeConfiguratorImpl(position, configuration, configuration.getRuleConfiguration().getColumnRootNode());
    }

}
