package com.jonpereiradev.jfile.reader.rules.configurator;

import com.jonpereiradev.jfile.reader.rules.RuleConfiguratorContext;
import com.jonpereiradev.jfile.reader.rules.line.LineColumnSizeRule;

final class LineRuleConfiguratorImpl implements LineRuleConfigurator {

    private final RuleConfiguratorContext context;

    LineRuleConfiguratorImpl(RuleConfiguratorContext context) {
        this.context = context;
    }

    @Override
    public LineRuleConfigurator columns(int size) {
        context.getRuleConfiguration().getLineRules().add(new LineColumnSizeRule(size));
        return this;
    }

    @Override
    public GenericTypeConfigurator column(int position) {
        return new GenericTypeConfiguratorImpl(position, context);
    }

    @Override
    public void build() {
        context.getReaderConfiguration().withRuleConfiguration(context.getRuleConfiguration());
    }
}
