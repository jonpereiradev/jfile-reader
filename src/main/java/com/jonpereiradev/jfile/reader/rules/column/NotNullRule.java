package com.jonpereiradev.jfile.reader.rules.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;
import org.apache.commons.lang3.StringUtils;

public class NotNullRule extends AbstractColumnRule {

    public NotNullRule(int position) {
        super(position);
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return StringUtils.isNotEmpty(fileColumn.getText());
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return true;
    }
}
