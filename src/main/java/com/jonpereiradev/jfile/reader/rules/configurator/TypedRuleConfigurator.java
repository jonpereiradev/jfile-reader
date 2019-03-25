package com.jonpereiradev.jfile.reader.rules.configurator;

import com.jonpereiradev.jfile.reader.rules.column.ColumnRule;

public interface TypedRuleConfigurator<T> {

    T notNull();

    T onlyNull();

    T rule(ColumnRule rule);

    GenericTypeConfigurator column(int position);

    RefRuleConfigurator<T> ref(int position);

    void build();

}
