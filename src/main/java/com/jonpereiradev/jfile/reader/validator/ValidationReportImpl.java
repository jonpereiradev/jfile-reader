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


import com.jonpereiradev.jfile.reader.validator.rule.RuleViolation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;


final class ValidationReportImpl implements ValidationReport {

    private final Map<Integer, ReportLineValidation> violationsPerRow = new TreeMap<>();

    void put(int lineNumber, RuleViolation ruleViolation) {
        if (!violationsPerRow.containsKey(lineNumber)) {
            violationsPerRow.put(lineNumber, new ReportLineValidation());
        }

        violationsPerRow.get(lineNumber).add(ruleViolation);
    }

    void put(int lineNumber, List<RuleViolation> ruleViolations) {
        if (!violationsPerRow.containsKey(lineNumber)) {
            violationsPerRow.put(lineNumber, new ReportLineValidation());
        }

        violationsPerRow.get(lineNumber).addAll(ruleViolations);
    }

    @Override
    public boolean isValid() {
        return getViolations().isEmpty();
    }

    @Override
    public boolean isNotValid() {
        return !isValid();
    }

    @Override
    public List<RuleViolation> getViolations(int lineNumber) {
        if (violationsPerRow.containsKey(lineNumber)) {
            return Collections.unmodifiableList(violationsPerRow.get(lineNumber).ruleViolations);
        }

        return Collections.emptyList();
    }

    @Override
    public List<RuleViolation> getViolations(int lineNumber, int columnNumber) {
        if (violationsPerRow.containsKey(lineNumber)) {
            Stream<RuleViolation> stream = violationsPerRow
                .get(lineNumber)
                .ruleViolations
                .stream()
                .filter(o -> o.getColumnNumber() == columnNumber);

            return Collections.unmodifiableList(stream.collect(Collectors.toList()));
        }

        return Collections.emptyList();
    }

    @Override
    public List<RuleViolation> getViolations() {
        return violationsPerRow
            .entrySet()
            .stream()
            .flatMap(o -> o.getValue().ruleViolations.stream())
            .collect(Collectors.toList());
    }

    private class ReportLineValidation {

        private final List<RuleViolation> ruleViolations;

        private ReportLineValidation() {
            this.ruleViolations = new ArrayList<>();
        }

        void add(RuleViolation ruleViolation) {
            this.ruleViolations.add(ruleViolation);
        }

        void addAll(List<RuleViolation> ruleViolations) {
            this.ruleViolations.addAll(ruleViolations);
        }

    }

}
