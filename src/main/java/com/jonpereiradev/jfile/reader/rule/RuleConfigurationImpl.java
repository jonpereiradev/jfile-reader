package com.jonpereiradev.jfile.reader.rule;

import com.jonpereiradev.jfile.reader.rule.column.ColumnRule;
import com.jonpereiradev.jfile.reader.rule.file.FileRule;
import com.jonpereiradev.jfile.reader.rule.line.LineRule;

final class RuleConfigurationImpl implements RuleConfiguration {

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
