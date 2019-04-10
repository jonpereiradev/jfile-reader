package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.rule.RuleNode;
import com.jonpereiradev.jfile.reader.rule.RuleNodeImpl;
import com.jonpereiradev.jfile.reader.rule.column.*;

import java.util.Arrays;

final class RefRuleConfiguratorImpl<T extends TypedRuleConfigurator<?>> implements RefRuleConfigurator<T> {

    private final int refPosition;
    private final int position;
    private final T currentConfigurator;
    private final RuleNode<ColumnRule> rule;

    RefRuleConfiguratorImpl(int refPosition, int position, RuleNode<ColumnRule> ruleNode, T currentConfigurator) {
        this.refPosition = refPosition;
        this.position = position;
        this.currentConfigurator = currentConfigurator;
        this.rule = ruleNode;
    }

    @Override
    public T filled() {
        RefRule ref = new FilledRefRule(refPosition, position);
        ref.setRuleNode(new RuleNodeImpl<>(ref.getClass(), rule));
        rule.add(ref);
        currentConfigurator.setRuleNode(ref.getRuleNode());
        return currentConfigurator;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T filled(Object... values) {
        RefRule ref = new DomainRefRule(refPosition, position, Arrays.asList(values));
        ref.setRuleNode(new RuleNodeImpl<>(ref.getClass(), rule));
        rule.add(ref);
        currentConfigurator.setRuleNode(ref.getRuleNode());
        return currentConfigurator;
    }

    @Override
    public T empty() {
        RefRule ref = new EmptyRefRule(refPosition, position);
        ref.setRuleNode(new RuleNodeImpl<>(ref.getClass(), rule));
        rule.add(ref);
        currentConfigurator.setRuleNode(ref.getRuleNode());
        return currentConfigurator;
    }
}
