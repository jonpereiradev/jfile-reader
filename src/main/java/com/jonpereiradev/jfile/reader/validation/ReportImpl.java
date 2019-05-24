package com.jonpereiradev.jfile.reader.validation;

import com.jonpereiradev.jfile.reader.rule.RuleViolation;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class ReportImpl implements Report {

    private final Map<Integer, ReportLineValidation> violationsPerRow = new TreeMap<>();

    public void put(int row, RuleViolation violation) {
        if (!violationsPerRow.containsKey(row)) {
            violationsPerRow.put(row, new ReportLineValidation());
        }

        violationsPerRow.get(row).add(violation);
    }

    public void put(int row, List<RuleViolation> violations) {
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
    public boolean isInvalid() {
        return !getViolations().isEmpty();
    }

    @Override
    public List<RuleViolation> getViolations(int row) {
        if (violationsPerRow.containsKey(row)) {
            return Collections.unmodifiableList(violationsPerRow.get(row).violations);
        }

        return Collections.emptyList();
    }

    @Override
    public List<RuleViolation> getViolations(int row, int position) {
        if (violationsPerRow.containsKey(row)) {
            Stream<RuleViolation> stream = violationsPerRow.get(row).violations.stream().filter(o -> o.getColumn() == position);
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
