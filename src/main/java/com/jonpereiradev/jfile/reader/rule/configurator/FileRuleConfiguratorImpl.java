package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.rule.RuleConfiguratorContext;

final class FileRuleConfiguratorImpl implements FileRuleConfigurator {

    private final RuleConfiguratorContext context;

    FileRuleConfiguratorImpl(RuleConfiguratorContext context) {
        this.context = context;
    }

    @Override
    public LineRuleConfigurator lines() {
        return new LineRuleConfiguratorImpl(context);
    }

}
