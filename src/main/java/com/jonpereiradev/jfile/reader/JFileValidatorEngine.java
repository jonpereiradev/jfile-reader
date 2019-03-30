package com.jonpereiradev.jfile.reader;

import com.jonpereiradev.jfile.reader.file.JFileLine;
import com.jonpereiradev.jfile.reader.rules.RuleViolation;
import com.jonpereiradev.jfile.reader.rules.column.ColumnRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

final class JFileValidatorEngine {

    private final JFileReaderContext context;

    JFileValidatorEngine(JFileReaderContext context) {
        this.context = context;
    }

    List<RuleViolation> validate(JFileLine line) {
        List<RuleViolation> violations = new ArrayList<>();

        context.getRuleConfiguration().getLineRules().forEach(lineRule -> {
            if (lineRule.canValidate(line) && !lineRule.isValid(line)) {
                RuleViolation violation = new RuleViolation();

                violation.setRow(line.getRow());
                violation.setColumn(-1);
                violation.setContent(line.getContent());
                violation.setRule(lineRule.getClass().getSimpleName());

                violations.add(violation);
            }
        });

        if (violations.isEmpty()) {
            line.getColumns().forEach(column -> {
                List<ColumnRule> rules = context.getRuleConfiguration().getColumnRules();
                List<ColumnRule> filtered = rules.stream().filter(o -> o.getPosition() == column.getPosition()).collect(Collectors.toList());

                for (ColumnRule columnRule : filtered) {
                    if (columnRule.canValidate(column) && !columnRule.isValid(column)) {
                        RuleViolation violation = new RuleViolation();

                        violation.setRow(line.getRow());
                        violation.setColumn(column.getPosition());
                        violation.setContent(column.getText());
                        violation.setRule(columnRule.getClass().getName());

                        violations.add(violation);

                        break;
                    }
                }
            });
        }

        return Collections.unmodifiableList(violations);
    }
}
