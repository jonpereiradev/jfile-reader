package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.rule.RuleNode;
import com.jonpereiradev.jfile.reader.rule.column.ColumnRule;

import java.util.function.Function;

public interface TypedRuleConfigurator<T extends TypedRuleConfigurator<?>> {

    /**
     * apply the rule of not null validation.
     */
    T notNull();

    /**
     * apply the rule of only null validation.
     */
    T onlyNull();

    /**
     * apply the current depends configuration and return to the root configuration node.
     */
    T apply();

    /**
     * define a custom rule validation.
     */
    T rule(Function<Integer, ColumnRule> rule);

    /**
     * creates the rule configuration for the column at position.
     */
    GenericTypeConfigurator column(int position);

    /**
     * creates the dependency rule validation between columns.
     */
    RefRuleConfigurator<T> depends(int column);

    /**
     * changes the current node rule validation.
     */
    void setRuleNode(RuleNode<ColumnRule> ruleNode);

    /**
     * finish the configuration and apply to the reader.
     */
    void build();

}
