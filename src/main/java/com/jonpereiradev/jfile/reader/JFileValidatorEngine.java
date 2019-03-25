package com.jonpereiradev.jfile.reader;

import com.jonpereiradev.jfile.reader.file.JFileLine;
import com.jonpereiradev.jfile.reader.rules.RuleViolation;
import com.jonpereiradev.jfile.reader.rules.column.ColumnRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

        line.getColumns().forEach(column -> {
            List<ColumnRule> rules = context.getRuleConfiguration().getColumnRules();

            rules.stream().filter(o -> o.getPosition() == column.getPosition()).forEach(o -> {
                if (o.canValidate(column) && !o.isValid(column)) {
                    RuleViolation violation = new RuleViolation();

                    violation.setRow(line.getRow());
                    violation.setColumn(column.getPosition());
                    violation.setContent(column.getText());
                    violation.setRule(o.getClass().getSimpleName());

                    violations.add(violation);
                }
            });
        });

        return Collections.unmodifiableList(violations);
    }
}
