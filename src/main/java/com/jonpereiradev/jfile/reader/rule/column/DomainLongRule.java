package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.util.List;

public class DomainLongRule extends AbstractColumnRule {

    private final List<Long> domains;

    public DomainLongRule(int position, List<Long> domains) {
        super(position);
        this.domains = domains;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return domains.contains(fileColumn.getLong());
    }
}
