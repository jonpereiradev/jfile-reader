package com.jonpereiradev.jfile.reader.validator;

import com.jonpereiradev.jfile.reader.JFileReader;
import com.jonpereiradev.jfile.reader.file.ColumnValue;
import com.jonpereiradev.jfile.reader.file.LineValue;
import com.jonpereiradev.jfile.reader.validator.rule.RuleNode;
import com.jonpereiradev.jfile.reader.validator.rule.RuleViolation;
import com.jonpereiradev.jfile.reader.validator.rule.RuleViolationImpl;
import com.jonpereiradev.jfile.reader.validator.rule.column.ArrayOfTypeRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.ColumnRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.RefRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class JFileValidatorEngine implements JFileValidator {

    private final JFileValidatorConfig validatorConfig;

    JFileValidatorEngine(JFileValidatorConfig validatorConfig) {
        this.validatorConfig = validatorConfig;
    }

    @Override
    public ValidationReport validate(JFileReader fileReader) {
        Objects.requireNonNull(validatorConfig.getRuleRoot(), "No rule config provided.");
        Iterator<LineValue> iterator = fileReader.iterator();
        ValidationReportImpl validationReport = new ValidationReportImpl();
        int maxViolationSize = validatorConfig.getMaxViolationSize();

        while (iterator.hasNext()) {
            LineValue line = iterator.next();
            List<RuleViolation> violations = validateLine(line);

            validationReport.put(line.getLineNumber(), violations);

            if (maxViolationSize > 0 && validationReport.getViolations().size() > maxViolationSize) {
                break;
            }
        }

        return validationReport;
    }

    @Override
    public ValidationReport validate(LineValue line) {
        List<RuleViolation> ruleViolations = validateLine(line);
        ValidationReportImpl validationReport = new ValidationReportImpl();

        validationReport.put(line.getLineNumber(), ruleViolations);

        return validationReport;
    }

    private List<RuleViolation> validateLine(LineValue line) {
        List<RuleViolation> violations = new ArrayList<>();

        checkLineRuleViolation(line, violations);

        if (violations.isEmpty()) {
            checkColumnRuleViolation(line, violations);
        }

        return Collections.unmodifiableList(violations);
    }

    private void checkLineRuleViolation(LineValue line, List<RuleViolation> violations) {
        validatorConfig.getRuleRoot().getLineRootNode().forEach(rule -> {
            if (rule.canValidate(line) && !rule.isValid(line)) {
                RuleViolationImpl violation = new RuleViolationImpl();

                violation.setRow(line.getLineNumber());
                violation.setColumn(-1);
                violation.setContent(line.getContent());
                violation.setRule(rule.getClass().getSimpleName());

                violations.add(violation);
            }
        });
    }

    private void checkColumnRuleViolation(LineValue line, List<RuleViolation> violations) {
        SortedSet<ColumnValue> columns = line.getColumns();

        columns.forEach(column -> {
            RuleNode<ColumnRule> rules = validatorConfig.getRuleRoot().getColumnRootNode();
            violations.addAll(validateColumnRules(line, column, rules));
        });
    }

    private List<RuleViolation> validateColumnRules(
        LineValue lineValue,
        ColumnValue column,
        RuleNode<ColumnRule> node) {
        Stream<ColumnRule> stream = node.getChildren().stream();
        List<ColumnRule> filtered = stream.filter(o -> o.getPosition() == column.getPosition()).collect(Collectors.toList());
        List<RuleViolation> violations = new ArrayList<>();

        for (ColumnRule rule : filtered) {
            ColumnValue fileColumn = isRefRule(rule) ? getDependsColumn(lineValue, (RefRule) rule) : column;

            rule.setLineValue(lineValue);

            if (rule instanceof ArrayOfTypeRule) {
                ArrayOfTypeRule arrayOf = (ArrayOfTypeRule) rule;
                arrayOf.split(column).forEach(o -> violations.addAll(validateColumnRules(
                    lineValue,
                    o,
                    rule.getRuleNode()
                )));
            } else if (rule.canValidate(fileColumn)) {
                recursivelyValidate(lineValue, column, rule, violations);
            }

            if (!violations.isEmpty()) {
                break;
            }
        }

        return violations;
    }

    private void recursivelyValidate(
        LineValue line,
        ColumnValue column,
        ColumnRule rule,
        List<RuleViolation> violations) {
        if (!rule.isValid(column)) {
            createViolation(line, column, rule, violations);
        } else {
            violations.addAll(validateColumnRules(line, column, rule.getRuleNode()));
        }
    }

    private void createViolation(LineValue line, ColumnValue column, ColumnRule rule, List<RuleViolation> violations) {
        RuleViolationImpl violation = new RuleViolationImpl();

        violation.setRow(line.getLineNumber());
        violation.setColumn(column.getPosition());
        violation.setContent(column.getText());
        violation.setRule(rule.getClass().getName());

        violations.add(violation);
    }

    private boolean isRefRule(ColumnRule rule) {
        return rule instanceof RefRule && ((RefRule) rule).getRefPosition() != -1;
    }

    private ColumnValue getDependsColumn(LineValue line, RefRule refRule) {
        ColumnValue refColumn = line.getColumnValue(refRule.getRefPosition());

        if (refColumn == null) {
            throw new NullPointerException("Column doesn't exists at " + refRule.getRefPosition() + "position");
        }

        return refColumn;
    }

}
