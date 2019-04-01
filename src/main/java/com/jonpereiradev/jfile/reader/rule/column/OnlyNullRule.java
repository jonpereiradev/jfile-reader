package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;
import org.apache.commons.lang3.StringUtils;

public class OnlyNullRule extends AbstractColumnRule {

    public OnlyNullRule(int position) {
        super(position);
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return StringUtils.isBlank(fileColumn.getText());
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return true;
    }
}
