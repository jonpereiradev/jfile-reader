package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

public class EmptyRefRule extends AbstractRefRule {

    public EmptyRefRule(int refPosition, int position) {
        super(refPosition, position);
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return new OnlyNullRule(fileColumn.getPosition()).isValid(fileColumn);
    }

}
