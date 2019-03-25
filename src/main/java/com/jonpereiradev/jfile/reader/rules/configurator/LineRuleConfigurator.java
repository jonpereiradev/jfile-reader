package com.jonpereiradev.jfile.reader.rules.configurator;

public interface LineRuleConfigurator {

    LineRuleConfigurator columns(int size);

    GenericTypeConfigurator column(int position);

    void build();
}
