package com.jonpereiradev.jfile.reader.rules.configurator;

import com.jonpereiradev.jfile.reader.rules.column.ColumnRule;

import java.util.function.Function;

public interface TypedRuleConfigurator<T> {

    T notNull();

    T onlyNull();

    T rule(Function<Integer, ColumnRule> rule);

    GenericTypeConfigurator column(int position);

    RefRuleConfigurator<T> ref(int position);

    void build();

}
