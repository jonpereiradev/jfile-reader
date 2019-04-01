package com.jonpereiradev.jfile.reader.rule;

import com.jonpereiradev.jfile.reader.rule.column.ColumnRule;
import com.jonpereiradev.jfile.reader.rule.file.FileRule;
import com.jonpereiradev.jfile.reader.rule.line.LineRule;

import java.util.ArrayList;
import java.util.List;

final class RuleConfigurationImpl implements RuleConfiguration {

    private final List<FileRule> fileRules = new ArrayList<>();
    private final List<LineRule> lineRules = new ArrayList<>();
    private final List<ColumnRule> columnRules = new ArrayList<>();

    @Override
    public List<FileRule> getFileRules() {
        return fileRules;
    }

    @Override
    public List<LineRule> getLineRules() {
        return lineRules;
    }

    @Override
    public List<ColumnRule> getColumnRules() {
        return columnRules;
    }
}
