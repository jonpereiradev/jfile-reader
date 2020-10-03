package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

public class FilledRefRule extends AbstractRefRule {

    public FilledRefRule(int refPosition, int position) {
        super(refPosition, position);
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return new NotEmptyRule(fileColumn.getPosition()).isValid(fileColumn);
    }

}
