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
package com.jonpereiradev.jfile.reader.validator.rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RuleNodeImpl<T extends Rule<?>> implements RuleNode<T> {

    private final Class<?> type;
    private final RuleNode<T> parentNode;
    private final List<T> rules = new ArrayList<>();

    public RuleNodeImpl(Class<?> type, RuleNode<T> parentNode) {
        this.type = type;
        this.parentNode = parentNode;
    }

    @Override
    public Class<?> getType() {
        return type;
    }

    @Override
    public void add(T rule) {
        this.rules.add(rule);
    }

    @Override
    public RuleNode<T> getParentNode() {
        return parentNode;
    }

    @Override
    public List<T> getChildren() {
        return Collections.unmodifiableList(rules);
    }

    @Override
    public Iterator<T> iterator() {
        return rules.iterator();
    }

}
