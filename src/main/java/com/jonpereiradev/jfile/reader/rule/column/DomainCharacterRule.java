package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.util.List;

public class DomainCharacterRule extends AbstractColumnRule {

    private final List<Character> domains;

    public DomainCharacterRule(int position, List<Character> domains) {
        super(position);
        this.domains = domains;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return domains.contains(fileColumn.getCharacter());
    }
}
