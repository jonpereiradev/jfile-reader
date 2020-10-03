package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;
import com.jonpereiradev.jfile.reader.file.LineValue;
import com.jonpereiradev.jfile.reader.validator.rule.Rule;
import com.jonpereiradev.jfile.reader.validator.rule.RuleNode;
import com.jonpereiradev.jfile.reader.validator.rule.RuleUtils;

public interface ColumnRule extends Rule<ColumnValue> {

    @Override
    boolean isValid(ColumnValue fileColumn);

    @Override
    default boolean canValidate(ColumnValue fileColumn) {
        return RuleUtils.isNotBlank(fileColumn.getText());
    }

    int getPosition();

    LineValue getLineValue();

    void setLineValue(LineValue lineValue);

    RuleNode<ColumnRule> getRuleNode();

    void setRuleNode(RuleNode<ColumnRule> ruleNode);
}
