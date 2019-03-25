package com.jonpereiradev.jfile.reader.rules.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;
import org.apache.commons.lang3.StringUtils;

public class NotEmptyRule extends AbstractColumnRule {

    public NotEmptyRule(int position) {
        super(position);
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return StringUtils.isNotBlank(fileColumn.getText());
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return true;
    }
}
