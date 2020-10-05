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
package com.jonpereiradev.jfile.reader.validator.rule.column;


import com.jonpereiradev.jfile.reader.file.ColumnValue;
import com.jonpereiradev.jfile.reader.file.LineValue;
import com.jonpereiradev.jfile.reader.validator.rule.RuleNode;


/**
 * @author jonpereiradev
 * @since 0.1.0
 */
public abstract class AbstractRefRule implements RefRule {

    private final int columnNumber;
    private final int refColumnNumber;

    private LineValue lineValue;
    private RuleNode<ColumnRule> ruleNode;

    public AbstractRefRule(int refColumnNumber, int columnNumber) {
        this.columnNumber = columnNumber;
        this.refColumnNumber = refColumnNumber;
    }

    @Override
    public boolean isValid(ColumnValue columnValue) {
        return true;
    }

    @Override
    public int getColumnNumber() {
        return columnNumber;
    }

    @Override
    public LineValue getLineValue() {
        return lineValue;
    }

    @Override
    public void setLineValue(LineValue lineValue) {
        this.lineValue = lineValue;
    }

    @Override
    public RuleNode<ColumnRule> getRuleNode() {
        return ruleNode;
    }

    @Override
    public void setRuleNode(RuleNode<ColumnRule> ruleNode) {
        this.ruleNode = ruleNode;
    }

    @Override
    public int getRefColumnNumber() {
        return refColumnNumber;
    }

}
