package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

import java.util.List;

public class DomainShortRule extends AbstractColumnRule {

    private final List<Short> domains;

    public DomainShortRule(int position, List<Short> domains) {
        super(position);
        this.domains = domains;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return domains.contains(fileColumn.getShort());
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return fileColumn.getShort() != null;
    }
}
