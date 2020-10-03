package com.jonpereiradev.jfile.reader.validator.rule.configurator;

import com.jonpereiradev.jfile.reader.validator.rule.RuleNode;
import com.jonpereiradev.jfile.reader.validator.rule.column.ColumnRule;

import java.util.function.Function;

public interface TypedRuleConfigurator<T extends TypedRuleConfigurator<?>> {

    /**
     * Apply the rule of not null validation.
     *
     * @return the configurator with the Not Null rule configured.
     */
    T notNull();

    /**
     * Apply the rule of only null validation.
     *
     * @return the configurator with the Only Null rule configured.
     */
    T onlyNull();

    /**
     * Applies the current depends config and return to the root config node.
     *
     * @return the configurator with the Dependency rule configured.
     */
    T apply();

    /**
     * Define a custom rule validation.
     *
     * @param rule a custom rule to validate the column.
     *
     * @return the configurator with the Custom rule configured.
     */
    T rule(Function<Integer, ColumnRule> rule);

    /**
     * Creates the rule config for the column at position.
     *
     * @param position the position of the column in the line.
     *
     * @return the configurator to configure a column.
     */
    GenericTypeConfigurator column(int position);

    /**
     * Creates the dependency rule validation between columns.
     *
     * @param position the position of the column in the line that the current column depends on.
     *
     * @return the configurator for the Dependency rule.
     */
    RefRuleConfigurator<T> depends(int position);

    /**
     * Changes the current node rule validation.
     *
     * @param ruleNode the node for the current rule.
     */
    void setRuleNode(RuleNode<ColumnRule> ruleNode);

}
