package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;
import com.jonpereiradev.jfile.reader.rule.RuleUtils;

public class NotNullRule extends AbstractColumnRule {

    public NotNullRule(int position) {
        super(position);
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return RuleUtils.isNotEmpty(fileColumn.getText());
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return true;
    }
}
