package com.jonpereiradev.jfile.reader.validator.rule;

public interface RuleViolation {

    int getRow();

    int getColumn();

    String getContent();

    String getRule();

}
