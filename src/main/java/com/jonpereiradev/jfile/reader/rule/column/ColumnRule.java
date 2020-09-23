package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;
import com.jonpereiradev.jfile.reader.file.JFileLine;
import com.jonpereiradev.jfile.reader.rule.Rule;
import com.jonpereiradev.jfile.reader.rule.RuleNode;
import com.jonpereiradev.jfile.reader.rule.RuleUtils;

public interface ColumnRule extends Rule<JFileColumn> {

    @Override
    boolean isValid(JFileColumn fileColumn);

    @Override
    default boolean canValidate(JFileColumn fileColumn) {
        return RuleUtils.isNotBlank(fileColumn.getText());
    }

    int getPosition();

    JFileLine getFileLine();

    void setFileLine(JFileLine fileLine);

    RuleNode<ColumnRule> getRuleNode();

    void setRuleNode(RuleNode<ColumnRule> ruleNode);
}
