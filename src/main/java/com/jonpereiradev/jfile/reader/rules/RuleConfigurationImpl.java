package com.jonpereiradev.jfile.reader.rules;

import com.jonpereiradev.jfile.reader.rules.column.ColumnRule;
import com.jonpereiradev.jfile.reader.rules.file.FileRule;
import com.jonpereiradev.jfile.reader.rules.line.LineRule;

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
