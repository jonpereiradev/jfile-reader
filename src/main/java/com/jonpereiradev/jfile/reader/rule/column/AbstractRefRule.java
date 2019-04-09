package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;
import com.jonpereiradev.jfile.reader.rule.RuleNode;
import com.jonpereiradev.jfile.reader.rule.RuleNodeImpl;

public abstract class AbstractRefRule implements RefRule {

    private final int refPosition;
    private final int position;
    private final RuleNode<ColumnRule> refRules;

    public AbstractRefRule(int refPosition, int position, RuleNode<ColumnRule> rootNode) {
        this.refPosition = refPosition;
        this.position = position;
        this.refRules = new RuleNodeImpl<>(rootNode);
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return true;
    }

    @Override
    public int getRefPosition() {
        return refPosition;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public RuleNode<ColumnRule> getRules() {
        return refRules;
    }

}
