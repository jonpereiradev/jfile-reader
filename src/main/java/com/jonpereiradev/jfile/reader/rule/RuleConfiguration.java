package com.jonpereiradev.jfile.reader.rule;

import com.jonpereiradev.jfile.reader.rule.column.ColumnRule;
import com.jonpereiradev.jfile.reader.rule.file.FileRule;
import com.jonpereiradev.jfile.reader.rule.line.LineRule;

public interface RuleConfiguration {

    RuleNode<FileRule> getFileRootNode();

    RuleNode<LineRule> getLineRootNode();

    RuleNode<ColumnRule> getColumnRootNode();

}
