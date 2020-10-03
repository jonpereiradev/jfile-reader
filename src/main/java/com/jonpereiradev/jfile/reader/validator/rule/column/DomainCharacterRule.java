package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

import java.util.List;

public class DomainCharacterRule extends AbstractColumnRule {

    private final List<Character> domains;

    public DomainCharacterRule(int position, List<Character> domains) {
        super(position);
        this.domains = domains;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return domains.contains(fileColumn.getCharacter());
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return fileColumn.getCharacter() != null;
    }
}
