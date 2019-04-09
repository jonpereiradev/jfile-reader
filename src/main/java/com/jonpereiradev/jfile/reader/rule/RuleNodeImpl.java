package com.jonpereiradev.jfile.reader.rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RuleNodeImpl<T extends Rule<?>> implements RuleNode<T> {

    private final RuleNode<T> parentRoot;
    private final List<T> rules = new ArrayList<>();

    public RuleNodeImpl() {
        this.parentRoot = null;
    }

    public RuleNodeImpl(RuleNode<T> parentRoot) {
        this.parentRoot = parentRoot;
    }

    @Override
    public void add(T rule) {
        this.rules.add(rule);
    }

    @Override
    public Iterator<T> iterator() {
        return rules.iterator();
    }

    @Override
    public RuleNode<T> getParentNode() {
        return parentRoot;
    }

    @Override
    public List<T> getChildrens() {
        return Collections.unmodifiableList(rules);
    }
}
