package com.jonpereiradev.jfile.reader.validator;

import com.jonpereiradev.jfile.reader.validator.rule.RuleViolation;

import java.util.List;

public interface ValidationReport {

    boolean isValid();

    boolean isNotValid();

    List<RuleViolation> getViolations(int row);

    List<RuleViolation> getViolations(int row, int column);

    List<RuleViolation> getViolations();

}
