package com.jonpereiradev.jfile.reader.validator.rule.configurator;

import com.jonpereiradev.jfile.reader.validator.JFileValidatorConfig;

public interface LineRuleConfigurator {

    static LineRuleConfigurator defaultConfigurator(JFileValidatorConfig configuration) {
        return new LineRuleConfiguratorImpl(configuration);
    }

    /**
     * define the number of columns for one line.
     */
    LineRuleConfigurator columnsSize(int size);

    /**
     * creates the rule config for the column at position.
     */
    ColumnRuleConfigurator columns();

}
