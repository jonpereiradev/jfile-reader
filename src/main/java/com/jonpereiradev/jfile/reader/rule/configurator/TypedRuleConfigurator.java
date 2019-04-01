package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.rule.column.ColumnRule;

import java.util.function.Function;

public interface TypedRuleConfigurator<T> {

    T notNull();

    T onlyNull();

    T rule(Function<Integer, ColumnRule> rule);

    GenericTypeConfigurator column(int position);

    RefRuleConfigurator<T> ref(int position);

    void build();

}
