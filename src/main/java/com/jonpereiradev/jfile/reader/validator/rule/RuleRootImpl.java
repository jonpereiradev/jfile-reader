package com.jonpereiradev.jfile.reader.validator.rule;

import com.jonpereiradev.jfile.reader.validator.rule.column.ColumnRule;
import com.jonpereiradev.jfile.reader.validator.rule.file.FileRule;
import com.jonpereiradev.jfile.reader.validator.rule.line.LineRule;

public final class RuleRootImpl implements RuleRoot {

    private final RuleNode<FileRule> fileRuleNode = new RuleNodeImpl<>(null, null);
    private final RuleNode<LineRule> lineRuleNode = new RuleNodeImpl<>(null, null);
    private final RuleNode<ColumnRule> columnRuleNode = new RuleNodeImpl<>(null, null);

    @Override
    public RuleNode<FileRule> getFileRootNode() {
        return fileRuleNode;
    }

    @Override
    public RuleNode<LineRule> getLineRootNode() {
        return lineRuleNode;
    }

    @Override
    public RuleNode<ColumnRule> getColumnRootNode() {
        return columnRuleNode;
    }
}
