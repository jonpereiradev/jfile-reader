package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

import java.util.List;

public class DomainIntegerRule extends AbstractColumnRule {

    private final List<Integer> domains;

    public DomainIntegerRule(int position, List<Integer> domains) {
        super(position);
        this.domains = domains;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return domains.contains(fileColumn.getInt());
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return fileColumn.getInt() != null;
    }
}
