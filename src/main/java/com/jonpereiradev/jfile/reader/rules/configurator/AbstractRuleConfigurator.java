package com.jonpereiradev.jfile.reader.rules.configurator;

import com.jonpereiradev.jfile.reader.rules.RuleConfiguratorContext;
import com.jonpereiradev.jfile.reader.rules.column.ColumnRule;
import com.jonpereiradev.jfile.reader.rules.column.NotNullRule;
import com.jonpereiradev.jfile.reader.rules.column.OnlyNullRule;

import java.util.function.Function;

abstract class AbstractRuleConfigurator<T> implements TypedRuleConfigurator<T> {

    private final int position;
    private final RuleConfiguratorContext context;

    AbstractRuleConfigurator(int position, RuleConfiguratorContext context) {
        this.position = position;
        this.context = context;
    }

    @Override
    public T notNull() {
        return rule(NotNullRule::new);
    }

    @Override
    public T onlyNull() {
        return rule(OnlyNullRule::new);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T rule(Function<Integer, ColumnRule> rule) {
        context.getRuleConfiguration().getColumnRules().add(rule.apply(position));
        return (T) this;
    }

    @Override
    public GenericTypeConfigurator column(int position) {
        return new GenericTypeConfiguratorImpl(position, context);
    }

    @Override
    @SuppressWarnings("unchecked")
    public RefRuleConfigurator<T> ref(int refPosition) {
        return new RefRuleConfiguratorImpl<>(refPosition, position, context, (T) this);
    }

    @Override
    public void build() {
        context.getReaderConfiguration().withRuleConfiguration(context.getRuleConfiguration());
    }

}
