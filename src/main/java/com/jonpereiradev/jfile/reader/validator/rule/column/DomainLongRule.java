package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

import java.util.List;

public class DomainLongRule extends AbstractColumnRule {

    private final List<Long> domains;

    public DomainLongRule(int position, List<Long> domains) {
        super(position);
        this.domains = domains;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return domains.contains(fileColumn.getLong());
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return fileColumn.getLong() != null;
    }
}
