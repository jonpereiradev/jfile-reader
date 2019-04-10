package com.jonpereiradev.jfile.reader.validation;

import com.jonpereiradev.jfile.reader.JFileReaderContext;
import com.jonpereiradev.jfile.reader.file.JFileColumn;
import com.jonpereiradev.jfile.reader.file.JFileLine;
import com.jonpereiradev.jfile.reader.rule.RuleNode;
import com.jonpereiradev.jfile.reader.rule.RuleViolation;
import com.jonpereiradev.jfile.reader.rule.column.ArrayOfTypeRule;
import com.jonpereiradev.jfile.reader.rule.column.ColumnRule;
import com.jonpereiradev.jfile.reader.rule.column.RefRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class JFileValidatorEngineImpl implements JFileValidatorEngine {

    private final JFileReaderContext context;

    JFileValidatorEngineImpl(JFileReaderContext context) {
        this.context = context;
    }

    @Override
    public List<RuleViolation> validate(JFileLine line) {
        List<RuleViolation> violations = new ArrayList<>();

        checkLineRuleViolation(line, violations);

        if (violations.isEmpty()) {
            checkColumnRuleViolation(line, violations);
        }

        return Collections.unmodifiableList(violations);
    }

    private void checkLineRuleViolation(JFileLine line, List<RuleViolation> violations) {
        context.getRuleConfiguration().getLineRootNode().forEach(rule -> {
            if (rule.canValidate(line) && !rule.isValid(line)) {
                RuleViolation violation = new RuleViolation();

                violation.setRow(line.getRow());
                violation.setColumn(-1);
                violation.setContent(line.getContent());
                violation.setRule(rule.getClass().getSimpleName());

                violations.add(violation);
            }
        });
    }

    private void checkColumnRuleViolation(JFileLine line, List<RuleViolation> violations) {
        SortedSet<JFileColumn> columns = line.getColumns();

        columns.forEach(column -> {
            RuleNode<ColumnRule> rules = context.getRuleConfiguration().getColumnRootNode();
            violations.addAll(validateColumnRules(line, column, rules));
        });
    }

    private List<RuleViolation> validateColumnRules(JFileLine line, JFileColumn column, RuleNode<ColumnRule> node) {
        Stream<ColumnRule> stream = node.getChildrens().stream();
        List<ColumnRule> filtered = stream.filter(o -> o.getPosition() == column.getPosition()).collect(Collectors.toList());
        List<RuleViolation> violations = new ArrayList<>();

        for (ColumnRule rule : filtered) {
            JFileColumn fileColumn = isRefRule(rule) ? getDependsColumn(line, (RefRule) rule) : column;

            if (rule instanceof ArrayOfTypeRule) {
                ArrayOfTypeRule arrayOf = (ArrayOfTypeRule) rule;
                arrayOf.split(column).forEach(o -> violations.addAll(validateColumnRules(line, o, rule.getRuleNode())));
            } else if (rule.canValidate(fileColumn)) {
                recursivelyValidate(line, column, rule, violations);
            }

            if (!violations.isEmpty()) {
                break;
            }
        }

        return violations;
    }

    private void recursivelyValidate(JFileLine line, JFileColumn column, ColumnRule rule, List<RuleViolation> violations) {
        if (!rule.isValid(column)) {
            createViolation(line, column, rule, violations);
        } else {
            violations.addAll(validateColumnRules(line, column, rule.getRuleNode()));
        }
    }

    private void createViolation(JFileLine line, JFileColumn column, ColumnRule rule, List<RuleViolation> violations) {
        RuleViolation violation = new RuleViolation();

        violation.setRow(line.getRow());
        violation.setColumn(column.getPosition());
        violation.setContent(column.getText());
        violation.setRule(rule.getClass().getName());

        violations.add(violation);
    }

    private boolean isRefRule(ColumnRule rule) {
        return rule instanceof RefRule && ((RefRule) rule).getRefPosition() != -1;
    }

    private JFileColumn getDependsColumn(JFileLine line, RefRule refRule) {
        JFileColumn refColumn = line.getColumn(refRule.getRefPosition());

        if (refColumn == null) {
            throw new NullPointerException("Column doesn't exists at " + refRule.getRefPosition() + "position");
        }

        return refColumn;
    }

}
