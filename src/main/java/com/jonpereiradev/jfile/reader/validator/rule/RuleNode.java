package com.jonpereiradev.jfile.reader.validator.rule;

import java.util.List;

public interface RuleNode<T extends Rule<?>> extends Iterable<T> {

    Class<?> getType();

    void add(T rule);

    RuleNode<T> getParentNode();

    List<T> getChildren();

}
