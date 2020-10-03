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

    void put(int row, RuleViolation violation) {
        if (!violationsPerRow.containsKey(row)) {
            violationsPerRow.put(row, new ReportLineValidation());
        }

        violationsPerRow.get(row).add(violation);
    }

    void put(int row, List<RuleViolation> violations) {
        if (!violationsPerRow.containsKey(row)) {
            violationsPerRow.put(row, new ReportLineValidation());
        }

        violationsPerRow.get(row).addAll(violations);
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
    public List<RuleViolation> getViolations(int row) {
        if (violationsPerRow.containsKey(row)) {
            return Collections.unmodifiableList(violationsPerRow.get(row).violations);
        }

        return Collections.emptyList();
    }

    @Override
    public List<RuleViolation> getViolations(int row, int column) {
        if (violationsPerRow.containsKey(row)) {
            Stream<RuleViolation> stream = violationsPerRow.get(row).violations.stream().filter(o -> o.getColumn() == column);
            return Collections.unmodifiableList(stream.collect(Collectors.toList()));
        }

        return Collections.emptyList();
    }

    @Override
    public List<RuleViolation> getViolations() {
        return violationsPerRow.entrySet().stream().flatMap(o -> o.getValue().violations.stream()).collect(Collectors.toList());
    }

    private class ReportLineValidation {

        private final List<RuleViolation> violations;

        private ReportLineValidation() {
            this.violations = new ArrayList<>();
        }

        void add(RuleViolation violation) {
            this.violations.add(violation);
        }

        void addAll(List<RuleViolation> violations) {
            this.violations.addAll(violations);
        }
    }

}
