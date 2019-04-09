package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;
import com.jonpereiradev.jfile.reader.rule.RuleNode;

import java.util.List;

public class DomainRefRule<T> extends AbstractRefRule {

    private final List<T> domains;
    private final Class<T> clazz;

    @SuppressWarnings("unchecked")
    public DomainRefRule(int refPosition, int position, List<T> domains, RuleNode<ColumnRule> rootNode) {
        super(refPosition, position, rootNode);
        this.domains = domains;
        this.clazz = (Class<T>) domains.get(0).getClass();
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return domains.contains(fileColumn.getContent(clazz));
    }

}
