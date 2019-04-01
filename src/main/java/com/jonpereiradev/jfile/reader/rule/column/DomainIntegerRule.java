package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.util.List;

public class DomainIntegerRule extends AbstractColumnRule {

    private final List<Integer> domains;

    public DomainIntegerRule(int position, List<Integer> domains) {
        super(position);
        this.domains = domains;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return domains.contains(fileColumn.getInt());
    }
}
