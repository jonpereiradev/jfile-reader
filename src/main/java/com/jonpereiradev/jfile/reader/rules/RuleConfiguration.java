package com.jonpereiradev.jfile.reader.rules;

import com.jonpereiradev.jfile.reader.rules.column.ColumnRule;
import com.jonpereiradev.jfile.reader.rules.file.FileRule;
import com.jonpereiradev.jfile.reader.rules.line.LineRule;

import java.util.List;

public interface RuleConfiguration {

    List<FileRule> getFileRules();

    List<LineRule> getLineRules();

    List<ColumnRule> getColumnRules();

}
