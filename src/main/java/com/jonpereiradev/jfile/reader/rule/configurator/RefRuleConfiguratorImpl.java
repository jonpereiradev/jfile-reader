package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.rule.RuleNode;
import com.jonpereiradev.jfile.reader.rule.column.ColumnRule;
import com.jonpereiradev.jfile.reader.rule.column.DomainRefRule;
import com.jonpereiradev.jfile.reader.rule.column.EmptyRefRule;
import com.jonpereiradev.jfile.reader.rule.column.FilledRefRule;

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
        FilledRefRule ref = new FilledRefRule(refPosition, position, rule);
        rule.add(ref);
        currentConfigurator.setRuleNode(ref.getRules());
        return currentConfigurator;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T filled(Object... values) {
        DomainRefRule ref = new DomainRefRule(refPosition, position, Arrays.asList(values), rule);
        rule.add(ref);
        currentConfigurator.setRuleNode(ref.getRules());
        return currentConfigurator;
    }

    @Override
    public T empty() {
        EmptyRefRule ref = new EmptyRefRule(refPosition, position, rule);
        rule.add(ref);
        currentConfigurator.setRuleNode(ref.getRules());
        return currentConfigurator;
    }
}
