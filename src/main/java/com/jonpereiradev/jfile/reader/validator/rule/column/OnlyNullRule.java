package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;
import com.jonpereiradev.jfile.reader.validator.rule.RuleUtils;

public class OnlyNullRule extends AbstractColumnRule {

    public OnlyNullRule(int position) {
        super(position);
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return RuleUtils.isBlank(fileColumn.getText());
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return true;
    }
}
