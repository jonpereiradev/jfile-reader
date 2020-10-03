package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

public class CharacterTypeRule extends AbstractColumnRule {

    public CharacterTypeRule(int position) {
        super(position);
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return fileColumn.getText().isEmpty() || fileColumn.getCharacter() != null;
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return true;
    }
}
