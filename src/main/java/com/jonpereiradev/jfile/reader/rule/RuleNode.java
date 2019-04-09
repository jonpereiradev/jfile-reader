package com.jonpereiradev.jfile.reader.rule;

import java.util.List;

public interface RuleNode<T extends Rule<?>> extends Iterable<T> {

    void add(T rule);

    RuleNode<T> getParentNode();

    List<T> getChildrens();

}
