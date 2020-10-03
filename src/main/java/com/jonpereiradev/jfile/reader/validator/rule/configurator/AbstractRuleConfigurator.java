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

import com.jonpereiradev.jfile.reader.validator.JFileValidatorConfig;
import com.jonpereiradev.jfile.reader.validator.rule.RuleNode;
import com.jonpereiradev.jfile.reader.validator.rule.RuleNodeImpl;
import com.jonpereiradev.jfile.reader.validator.rule.column.ColumnRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.NotNullRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.OnlyNullRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.RefRule;

import java.util.function.Function;

abstract class AbstractRuleConfigurator<T extends TypedRuleConfigurator<?>> implements TypedRuleConfigurator<T> {

    private final int position;
    private final JFileValidatorConfig configuration;

    private RuleNode<ColumnRule> ruleNode;

    AbstractRuleConfigurator(int position, JFileValidatorConfig configuration, RuleNode<ColumnRule> ruleNode) {
        this.position = position;
        this.configuration = configuration;
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
    public T apply() {
        if (ruleNode.getParentNode() != null) {
            setRuleNode(ruleNode.getParentNode());
        }

        return (T) this;
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
        return new GenericTypeConfiguratorImpl(position, configuration, getParentNode());
    }

    @Override
    @SuppressWarnings("unchecked")
    public RefRuleConfigurator<T> depends(int position) {
        RuleNode<ColumnRule> parentNode = ruleNode;

        if (ruleNode.getParentNode() != null && RefRule.class.isAssignableFrom(ruleNode.getType())) {
            parentNode = ruleNode.getParentNode();
        }

        return new RefRuleConfiguratorImpl<>(position, this.position, parentNode, (T) this);
    }

    @Override
    public void setRuleNode(RuleNode<ColumnRule> ruleNode) {
        this.ruleNode = ruleNode;
    }

    private RuleNode<ColumnRule> getParentNode() {
        RuleNode<ColumnRule> node = ruleNode;

        while (node.getParentNode() != null) {
            node = node.getParentNode();
        }

        return node;
    }
}
