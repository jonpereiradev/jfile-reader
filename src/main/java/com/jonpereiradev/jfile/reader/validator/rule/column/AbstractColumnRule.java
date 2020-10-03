package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;
import com.jonpereiradev.jfile.reader.file.LineValue;
import com.jonpereiradev.jfile.reader.validator.rule.RuleNode;
import com.jonpereiradev.jfile.reader.validator.rule.RuleUtils;

public abstract class AbstractColumnRule implements ColumnRule {

    private final int position;

    private LineValue lineValue;
    private RuleNode<ColumnRule> ruleNode;

    public AbstractColumnRule(int position) {
        this.position = position;
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return RuleUtils.isNotBlank(fileColumn.getText());
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
