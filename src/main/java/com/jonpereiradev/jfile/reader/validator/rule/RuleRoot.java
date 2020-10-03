package com.jonpereiradev.jfile.reader.validator.rule;

import com.jonpereiradev.jfile.reader.validator.rule.column.ColumnRule;
import com.jonpereiradev.jfile.reader.validator.rule.file.FileRule;
import com.jonpereiradev.jfile.reader.validator.rule.line.LineRule;

public interface RuleRoot {

    RuleNode<FileRule> getFileRootNode();

    RuleNode<LineRule> getLineRootNode();

    RuleNode<ColumnRule> getColumnRootNode();

}
