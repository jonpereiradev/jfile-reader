package com.jonpereiradev.jfile.reader.rule.configurator;

public interface LineRuleConfigurator {

    /**
     * define the number of columns for one line.
     */
    LineRuleConfigurator columns(int size);

    /**
     * creates the rule configuration for the column at position.
     */
    GenericTypeConfigurator column(int position);

    /**
     * finish the configuration and apply to the reader.
     */
    void build();
}
