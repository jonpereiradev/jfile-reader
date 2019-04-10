package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.rule.RuleConfiguratorContext;
import com.jonpereiradev.jfile.reader.rule.RuleNode;
import com.jonpereiradev.jfile.reader.rule.RuleNodeImpl;
import com.jonpereiradev.jfile.reader.rule.column.ColumnRule;
import com.jonpereiradev.jfile.reader.rule.column.NotNullRule;
import com.jonpereiradev.jfile.reader.rule.column.OnlyNullRule;
import com.jonpereiradev.jfile.reader.rule.column.RefRule;

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
        ColumnRule columnRule = rule.apply(position);
        columnRule.setRuleNode(new RuleNodeImpl<>(columnRule.getClass(), ruleNode));
        ruleNode.add(columnRule);
        return (T) this;
    }

    @Override
    public GenericTypeConfigurator column(int position) {
        return new GenericTypeConfiguratorImpl(position, context, getParentNode());
    }

    private RuleNode<ColumnRule> getParentNode() {
        RuleNode<ColumnRule> node = ruleNode;

        while (node.getParentNode() != null) {
            node = node.getParentNode();
        }

        return node;
    }

    @Override
    @SuppressWarnings("unchecked")
    public RefRuleConfigurator<T> depends(int column) {
        RuleNode<ColumnRule> parentNode = ruleNode;

        if (ruleNode.getParentNode() != null && RefRule.class.isAssignableFrom(ruleNode.getType())) {
            parentNode = ruleNode.getParentNode();
        }

        return new RefRuleConfiguratorImpl<>(column, position, parentNode, (T) this);
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
