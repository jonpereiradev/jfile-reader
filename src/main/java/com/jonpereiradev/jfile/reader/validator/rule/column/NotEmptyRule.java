package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;
import com.jonpereiradev.jfile.reader.validator.rule.RuleUtils;

public class NotEmptyRule extends AbstractColumnRule {

    public NotEmptyRule(int position) {
        super(position);
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return RuleUtils.isNotBlank(fileColumn.getText());
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return true;
    }
}
