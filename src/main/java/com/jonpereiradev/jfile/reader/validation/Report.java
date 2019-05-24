package com.jonpereiradev.jfile.reader.validation;

import com.jonpereiradev.jfile.reader.rule.RuleViolation;

import java.util.List;

public interface Report {

    static Report defaultReport() {
        return new ReportImpl();
    }

    void put(int row, RuleViolation violation);

    void put(int row, List<RuleViolation> violations);

    boolean isValid();

    boolean isInvalid();

    List<RuleViolation> getViolations(int row);

    List<RuleViolation> getViolations(int row, int position);

    List<RuleViolation> getViolations();

}
