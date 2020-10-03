package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;
import com.jonpereiradev.jfile.reader.file.LineValue;
import com.jonpereiradev.jfile.reader.validator.rule.RuleNode;

public abstract class AbstractRefRule implements RefRule {

    private final int refPosition;
    private final int position;

    private LineValue lineValue;
    private RuleNode<ColumnRule> ruleNode;

    public AbstractRefRule(int refPosition, int position) {
        this.refPosition = refPosition;
        this.position = position;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return true;
    }

    @Override
    public int getRefPosition() {
        return refPosition;
    }

    @Override
    public int getPosition() {
        return position;
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
}
