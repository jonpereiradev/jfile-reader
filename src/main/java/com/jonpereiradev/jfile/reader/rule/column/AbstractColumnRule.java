package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;
import com.jonpereiradev.jfile.reader.file.JFileLine;
import com.jonpereiradev.jfile.reader.rule.RuleNode;
import org.apache.commons.lang3.StringUtils;

public abstract class AbstractColumnRule implements ColumnRule {

    private final int position;

    private JFileLine fileLine;
    private RuleNode<ColumnRule> ruleNode;

    public AbstractColumnRule(int position) {
        this.position = position;
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return StringUtils.isNotBlank(fileColumn.getText());
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
