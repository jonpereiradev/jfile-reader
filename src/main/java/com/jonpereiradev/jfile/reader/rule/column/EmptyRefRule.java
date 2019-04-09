package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;
import com.jonpereiradev.jfile.reader.rule.RuleNode;

public class EmptyRefRule extends AbstractRefRule {

    public EmptyRefRule(int refPosition, int position, RuleNode<ColumnRule> rootNode) {
        super(refPosition, position, rootNode);
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return new OnlyNullRule(fileColumn.getPosition()).isValid(fileColumn);
    }

}
