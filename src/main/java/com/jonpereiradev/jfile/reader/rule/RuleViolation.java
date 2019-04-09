package com.jonpereiradev.jfile.reader.rule;

import java.util.Objects;

public final class RuleViolation {

    private int row;
    private int column;
    private String content;
    private String rule;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RuleViolation that = (RuleViolation) o;
        return row == that.row &&
            column == that.column &&
            Objects.equals(rule, that.rule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, rule);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

}
