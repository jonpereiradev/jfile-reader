package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.rule.RuleConfiguratorContext;
import com.jonpereiradev.jfile.reader.rule.line.LineColumnSizeRule;

final class LineRuleConfiguratorImpl implements LineRuleConfigurator {

    private final RuleConfiguratorContext context;

    LineRuleConfiguratorImpl(RuleConfiguratorContext context) {
        this.context = context;
    }

    @Override
    public LineRuleConfigurator columns(int size) {
        context.getRuleConfiguration().getLineRootNode().add(new LineColumnSizeRule(size));
        return this;
    }

    @Override
    public GenericTypeConfigurator column(int position) {
        return new GenericTypeConfiguratorImpl(position, context, context.getRuleConfiguration().getColumnRootNode());
    }

    @Override
    public void build() {
        context.getReaderConfiguration().withRuleConfiguration(context.getRuleConfiguration());
    }
}
