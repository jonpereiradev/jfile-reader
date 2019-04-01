package com.jonpereiradev.jfile.reader.rule.configurator;

public interface LineRuleConfigurator {

    LineRuleConfigurator columns(int size);

    GenericTypeConfigurator column(int position);

    void build();
}
