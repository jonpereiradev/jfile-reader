package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;
import com.jonpereiradev.jfile.reader.file.JFileLine;
import com.jonpereiradev.jfile.reader.rule.RuleNode;

public abstract class AbstractRefRule implements RefRule {

    private final int refPosition;
    private final int position;

    private JFileLine fileLine;
    private RuleNode<ColumnRule> ruleNode;

    public AbstractRefRule(int refPosition, int position) {
        this.refPosition = refPosition;
        this.position = position;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
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
    public JFileLine getFileLine() {
        return fileLine;
    }

    @Override
    public void setFileLine(JFileLine fileLine) {
        this.fileLine = fileLine;
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
