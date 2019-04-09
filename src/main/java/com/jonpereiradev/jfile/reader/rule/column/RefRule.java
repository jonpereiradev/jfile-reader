package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.rule.RuleNode;

public interface RefRule extends ColumnRule {

    int getRefPosition();

    RuleNode<ColumnRule> getRules();

}
