package com.jonpereiradev.jfile.reader.validation;

import com.jonpereiradev.jfile.reader.JFileReaderContext;
import com.jonpereiradev.jfile.reader.file.JFileColumn;
import com.jonpereiradev.jfile.reader.file.JFileLine;
import com.jonpereiradev.jfile.reader.rule.RuleNode;
import com.jonpereiradev.jfile.reader.rule.RuleViolation;
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
            if (isRefRule(rule)) {
                RefRule refRule = (RefRule) rule;

                if (canValidateRefRules(line, refRule)) {
                    violations.addAll(validateColumnRules(line, column, refRule.getRules()));
                }
            } else if (rule.canValidate(column) && !rule.isValid(column)) {
                RuleViolation violation = new RuleViolation();

                violation.setRow(line.getRow());
                violation.setColumn(column.getPosition());
                violation.setContent(column.getText());
                violation.setRule(rule.getClass().getName());

                violations.add(violation);
            }

            if (!violations.isEmpty()) {
                break;
            }
        }

        return violations;
    }

    private boolean isRefRule(ColumnRule rule) {
        return rule instanceof RefRule && ((RefRule) rule).getRefPosition() != -1;
    }

    private boolean canValidateRefRules(JFileLine line, RefRule refRule) {
        JFileColumn refColumn = line.getColumn(refRule.getRefPosition());

        if (refColumn == null) {
            throw new NullPointerException("RefColumn doesn't exists at " + refRule.getRefPosition() + "position");
        }

        return refRule.canValidate(refColumn) && refRule.isValid(refColumn);
    }

}
