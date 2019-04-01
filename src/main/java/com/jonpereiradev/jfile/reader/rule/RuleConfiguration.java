package com.jonpereiradev.jfile.reader.rule;

import com.jonpereiradev.jfile.reader.rule.column.ColumnRule;
import com.jonpereiradev.jfile.reader.rule.file.FileRule;
import com.jonpereiradev.jfile.reader.rule.line.LineRule;

import java.util.List;

public interface RuleConfiguration {

    List<FileRule> getFileRules();

    List<LineRule> getLineRules();

    List<ColumnRule> getColumnRules();

}
