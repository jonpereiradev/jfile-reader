/*
 * MIT License
 *
 * Copyright (c) 2020 Jonathan de Almeida Pereira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
     * Creates the rule config for the column at columnNumber.
     *
     * @param columnNumber the columnNumber of the column in the line.
     *
     * @return the configurator to configure a column.
     */
    GenericTypeConfigurator column(int columnNumber);

    /**
     * Creates the dependency rule validation between columns.
     *
     * @param columnNumber the position of the column in the line that the current column depends on.
     *
     * @return the configurator for the Dependency rule.
     */
    RefRuleConfigurator<T> depends(int columnNumber);

    /**
     * Changes the current node rule validation.
     *
     * @param ruleNode the node for the current rule.
     */
    void setRuleNode(RuleNode<ColumnRule> ruleNode);

}
