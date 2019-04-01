package com.jonpereiradev.jfile.reader.validation;

import com.jonpereiradev.jfile.reader.rule.RuleViolation;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class ReportValidationImpl implements ReportValidation {

    private final Map<Integer, ReportLineValidation> violationsPerLine = new TreeMap<>();

    public void put(int row, RuleViolation violation) {
        if (!violationsPerLine.containsKey(row)) {
            violationsPerLine.put(row, new ReportLineValidation());
        }

        violationsPerLine.get(row).add(violation);
    }

    public void put(int row, List<RuleViolation> violations) {
        if (!violationsPerLine.containsKey(row)) {
            violationsPerLine.put(row, new ReportLineValidation());
        }

        violationsPerLine.get(row).addAll(violations);
    }

    @Override
    public boolean isValid() {
        return getViolations().isEmpty();
    }

    @Override
    public boolean isInvalid() {
        return !getViolations().isEmpty();
    }

    @Override
    public List<RuleViolation> getViolations(int row) {
        if (violationsPerLine.containsKey(row)) {
            return Collections.unmodifiableList(violationsPerLine.get(row).violations);
        }

        return Collections.emptyList();
    }

    @Override
    public List<RuleViolation> getViolations(int row, int position) {
        if (violationsPerLine.containsKey(row)) {
            Stream<RuleViolation> stream = violationsPerLine.get(row).violations.stream().filter(o -> o.getColumn() == position);
            return Collections.unmodifiableList(stream.collect(Collectors.toList()));
        }

        return Collections.emptyList();
    }

    @Override
    public List<RuleViolation> getViolations() {
        return violationsPerLine.entrySet().stream().flatMap(o -> o.getValue().violations.stream()).collect(Collectors.toList());
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
