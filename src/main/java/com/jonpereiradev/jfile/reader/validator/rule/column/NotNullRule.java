package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;
import com.jonpereiradev.jfile.reader.validator.rule.RuleUtils;

public class NotNullRule extends AbstractColumnRule {

    public NotNullRule(int position) {
        super(position);
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return RuleUtils.isNotEmpty(fileColumn.getText());
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return true;
    }
}
