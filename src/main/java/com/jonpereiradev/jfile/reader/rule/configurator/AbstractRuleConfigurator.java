package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.rule.RuleConfiguratorContext;
import com.jonpereiradev.jfile.reader.rule.RuleNode;
import com.jonpereiradev.jfile.reader.rule.column.ColumnRule;
import com.jonpereiradev.jfile.reader.rule.column.NotNullRule;
import com.jonpereiradev.jfile.reader.rule.column.OnlyNullRule;

import java.util.function.Function;

abstract class AbstractRuleConfigurator<T extends TypedRuleConfigurator<?>> implements TypedRuleConfigurator<T> {

    private final int position;
    private final RuleConfiguratorContext context;

    private RuleNode<ColumnRule> ruleNode;

    AbstractRuleConfigurator(int position, RuleConfiguratorContext context, RuleNode<ColumnRule> ruleNode) {
        this.position = position;
        this.context = context;
        this.ruleNode = ruleNode;
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
        ruleNode.add(rule.apply(position));
        return (T) this;
    }

    @Override
    public GenericTypeConfigurator column(int position) {
        if (ruleNode.getParentNode() != null) {
            return new GenericTypeConfiguratorImpl(position, context, ruleNode.getParentNode());
        }

        return new GenericTypeConfiguratorImpl(position, context, ruleNode);
    }

    @Override
    @SuppressWarnings("unchecked")
    public RefRuleConfigurator<T> depends(int column) {
        if (ruleNode.getParentNode() != null) {
            return new RefRuleConfiguratorImpl<>(column, position, ruleNode.getParentNode(), (T) this);
        }

        return new RefRuleConfiguratorImpl<>(column, position, ruleNode, (T) this);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T apply() {
        if (ruleNode.getParentNode() != null) {
            setRuleNode(ruleNode.getParentNode());
        }

        return (T) this;
    }

    @Override
    public void build() {
        context.getReaderConfiguration().withRuleConfiguration(context.getRuleConfiguration());
    }

    @Override
    public void setRuleNode(RuleNode<ColumnRule> ruleNode) {
        this.ruleNode = ruleNode;
    }
}
