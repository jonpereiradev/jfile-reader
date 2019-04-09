package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;
import org.apache.commons.lang3.StringUtils;

final class RefRuleImpl extends AbstractRefRule {

    RefRuleImpl(int refPosition, int position) {
        super(refPosition, position, null);
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return true;
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return StringUtils.isNotBlank(fileColumn.getText());
    }
}
