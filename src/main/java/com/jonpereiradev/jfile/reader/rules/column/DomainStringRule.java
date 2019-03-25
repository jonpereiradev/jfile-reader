package com.jonpereiradev.jfile.reader.rules.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.util.List;

public class DomainStringRule extends AbstractColumnRule {

    private final List<String> domains;

    public DomainStringRule(int position, List<String> domains) {
        super(position);
        this.domains = domains;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return domains.contains(fileColumn.getText());
    }
}
