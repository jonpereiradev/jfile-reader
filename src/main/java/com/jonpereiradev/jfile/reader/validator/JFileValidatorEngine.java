/*
 * MIT License
 *
 * Copyright (c) 2020 Jonathan de Almeida Pereira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.jonpereiradev.jfile.reader.validator;

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
import java.util.List;
import java.util.SortedSet;
import java.util.stream.Collectors;

final class JFileValidatorEngine implements JFileValidator {

    private final JFileValidatorConfig validatorConfig;

    JFileValidatorEngine(JFileValidatorConfig validatorConfig) {
        this.validatorConfig = validatorConfig;
    }

    @Override
    public ValidationReport validate(LineValue lineValue) {
        List<RuleViolation> ruleViolations = validateLine(lineValue);
        ValidationReportImpl validationReport = new ValidationReportImpl();

        validationReport.put(lineValue.getLineNumber(), ruleViolations);

        return validationReport;
    }

    private List<RuleViolation> validateLine(LineValue lineValue) {
        List<RuleViolation> violations = new ArrayList<>();

        checkLineRuleViolation(lineValue, violations);

        if (violations.isEmpty()) {
            checkColumnRuleViolation(lineValue, violations);
        }

        return Collections.unmodifiableList(violations);
    }

    private void checkLineRuleViolation(LineValue lineValue, List<RuleViolation> violations) {
        validatorConfig.getRuleRoot().getLineRootNode().forEach(rule -> {
            if (rule.canValidate(lineValue) && !rule.isValid(lineValue)) {
                RuleViolationImpl violation = new RuleViolationImpl();

                violation.setLineNumber(lineValue.getLineNumber());
                violation.setColumnNumber(-1);
                violation.setContent(lineValue.getContent());
                violation.setRule(rule.getClass().getSimpleName());

                violations.add(violation);
            }
        });
    }

    private void checkColumnRuleViolation(LineValue lineValue, List<RuleViolation> violations) {
        SortedSet<ColumnValue> columnValues = lineValue.getColumnValues();

        columnValues.forEach(columnValue -> {
            RuleNode<ColumnRule> columnRootNode = validatorConfig.getRuleRoot().getColumnRootNode();
            violations.addAll(validateColumnRules(lineValue, columnValue, columnRootNode));
        });
    }

    private List<RuleViolation> validateColumnRules(
        LineValue lineValue,
        ColumnValue columnValue,
        RuleNode<ColumnRule> ruleNode) {
        List<ColumnRule> filtered = ruleNode
            .getChildren()
            .stream()
            .filter(o -> o.getColumnNumber() == columnValue.getColumnNumber())
            .collect(Collectors.toList());

        List<RuleViolation> violations = new ArrayList<>();

        for (ColumnRule columnRule : filtered) {
            ColumnValue fileColumn = columnValue;

            if (isRefRule(columnRule)) {
                fileColumn = getDependsColumn(lineValue, (RefRule) columnRule);
            }

            columnRule.setLineValue(lineValue);

            if (columnRule instanceof ArrayOfTypeRule) {
                ArrayOfTypeRule arrayOf = (ArrayOfTypeRule) columnRule;
                arrayOf.split(columnValue).forEach(o -> violations.addAll(validateColumnRules(
                    lineValue,
                    o,
                    columnRule.getRuleNode()
                )));
            } else if (columnRule.canValidate(fileColumn)) {
                recursivelyValidate(lineValue, columnValue, columnRule, violations);
            }

            if (!violations.isEmpty()) {
                break;
            }
        }

        return violations;
    }

    private void recursivelyValidate(
        LineValue lineValue,
        ColumnValue columnValue,
        ColumnRule columnRule,
        List<RuleViolation> ruleViolations) {
        if (!columnRule.isValid(columnValue)) {
            createViolation(lineValue, columnValue, columnRule, ruleViolations);
        } else {
            ruleViolations.addAll(validateColumnRules(lineValue, columnValue, columnRule.getRuleNode()));
        }
    }

    private void createViolation(
        LineValue lineValue,
        ColumnValue columnValue,
        ColumnRule columnRule,
        List<RuleViolation> ruleViolations) {
        RuleViolationImpl violation = new RuleViolationImpl();

        violation.setLineNumber(lineValue.getLineNumber());
        violation.setColumnNumber(columnValue.getColumnNumber());
        violation.setContent(columnValue.getText());
        violation.setRule(columnRule.getClass().getName());

        ruleViolations.add(violation);
    }

    private boolean isRefRule(ColumnRule columnRule) {
        return columnRule instanceof RefRule && ((RefRule) columnRule).getRefColumnNumber() != -1;
    }

    private ColumnValue getDependsColumn(LineValue lineValue, RefRule refRule) {
        ColumnValue refColumn = lineValue.getColumnValue(refRule.getRefColumnNumber());

        if (refColumn == null) {
            throw new NullPointerException("Column doesn't exists at " + refRule.getRefColumnNumber() + "position");
        }

        return refColumn;
    }

}
