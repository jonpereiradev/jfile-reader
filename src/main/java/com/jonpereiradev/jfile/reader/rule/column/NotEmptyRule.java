package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;
import com.jonpereiradev.jfile.reader.rule.RuleUtils;

public class NotEmptyRule extends AbstractColumnRule {

    public NotEmptyRule(int position) {
        super(position);
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return RuleUtils.isNotBlank(fileColumn.getText());
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return true;
    }
}
