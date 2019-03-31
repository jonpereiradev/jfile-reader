package com.jonpereiradev.jfile.reader.rules.configurator;

import com.jonpereiradev.jfile.reader.rules.RuleConfiguratorContext;
import com.jonpereiradev.jfile.reader.rules.column.ColumnRule;
import com.jonpereiradev.jfile.reader.rules.column.DomainRefRule;
import com.jonpereiradev.jfile.reader.rules.column.FilledRefRule;

import java.util.Arrays;
import java.util.List;

final class RefRuleConfiguratorImpl<T> implements RefRuleConfigurator<T> {

    private final int refPosition;
    private final int position;
    private final RuleConfiguratorContext context;
    private final T currentConfigurator;

    RefRuleConfiguratorImpl(int refPosition, int position, RuleConfiguratorContext context, T currentConfigurator) {
        this.refPosition = refPosition;
        this.position = position;
        this.context = context;
        this.currentConfigurator = currentConfigurator;
    }

    @Override
    public T filled() {
        context.getRuleConfiguration().getColumnRules().add(new FilledRefRule(refPosition, position));
        return currentConfigurator;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T domain(Object... values) {
        List<ColumnRule> rules = context.getRuleConfiguration().getColumnRules();
        rules.add(new DomainRefRule(refPosition, position, Arrays.asList(values)));
        return currentConfigurator;
    }
}
