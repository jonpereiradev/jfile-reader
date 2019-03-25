package com.jonpereiradev.jfile.reader.rules.configurator;

import com.jonpereiradev.jfile.reader.rules.RuleConfiguratorContext;
import com.jonpereiradev.jfile.reader.rules.column.ColumnRule;
import com.jonpereiradev.jfile.reader.rules.column.NotNullRule;
import com.jonpereiradev.jfile.reader.rules.column.OnlyNullRule;

abstract class TypedRuleConfiguratorImpl<T> implements TypedRuleConfigurator<T> {

    protected final int position;
    protected final RuleConfiguratorContext context;

    TypedRuleConfiguratorImpl(int position, RuleConfiguratorContext context) {
        this.position = position;
        this.context = context;
    }

    @Override
    public T notNull() {
        return rule(new NotNullRule(position));
    }

    @Override
    public T onlyNull() {
        return rule(new OnlyNullRule(position));
    }

    @Override
    @SuppressWarnings("unchecked")
    public T rule(ColumnRule rule) {
        context.getRuleConfiguration().getColumnRules().add(rule);
        return (T) this;
    }

    @Override
    public GenericTypeConfigurator column(int position) {
        return new GenericTypeConfiguratorImpl(position, context);
    }

    @Override
    public RefRuleConfigurator<T> ref(int position) {
        return null;
    }

    @Override
    public void build() {
        context.getReaderConfiguration().withRuleConfiguration(context.getRuleConfiguration());
    }

}
