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
import com.jonpereiradev.jfile.reader.validator.rule.RuleNodeImpl;
import com.jonpereiradev.jfile.reader.validator.rule.column.ColumnRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.DomainRefRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.EmptyRefRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.FilledRefRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.RefRule;

import java.util.Arrays;

final class RefRuleConfiguratorImpl<T extends TypedRuleConfigurator<?>> implements RefRuleConfigurator<T> {

    private final int refPosition;
    private final int columnNumber;
    private final T currentConfigurator;
    private final RuleNode<ColumnRule> rule;

    RefRuleConfiguratorImpl(int refPosition, int columnNumber, RuleNode<ColumnRule> ruleNode, T currentConfigurator) {
        this.refPosition = refPosition;
        this.columnNumber = columnNumber;
        this.currentConfigurator = currentConfigurator;
        this.rule = ruleNode;
    }

    @Override
    public T filled() {
        RefRule ref = new FilledRefRule(refPosition, columnNumber);
        ref.setRuleNode(new RuleNodeImpl<>(ref.getClass(), rule));
        rule.add(ref);
        currentConfigurator.setRuleNode(ref.getRuleNode());
        return currentConfigurator;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T filled(Object... values) {
        RefRule ref = new DomainRefRule(refPosition, columnNumber, Arrays.asList(values));
        ref.setRuleNode(new RuleNodeImpl<>(ref.getClass(), rule));
        rule.add(ref);
        currentConfigurator.setRuleNode(ref.getRuleNode());
        return currentConfigurator;
    }

    @Override
    public T empty() {
        RefRule ref = new EmptyRefRule(refPosition, columnNumber);
        ref.setRuleNode(new RuleNodeImpl<>(ref.getClass(), rule));
        rule.add(ref);
        currentConfigurator.setRuleNode(ref.getRuleNode());
        return currentConfigurator;
    }
}
