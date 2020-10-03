package com.jonpereiradev.jfile.reader.validator.rule.configurator;

import com.jonpereiradev.jfile.reader.validator.JFileValidatorConfig;

public interface ColumnRuleConfigurator {

    /**
     * Creates the ColumnRuleConfigurator.
     *
     * @param configuration the validator configuration.
     *
     * @return a configurator that configure rules for columns.
     */
    static ColumnRuleConfigurator defaultConfigurator(JFileValidatorConfig configuration) {
        return new ColumnRuleConfiguratorImpl(configuration);
    }

    /**
     * Creates the rule config for the column at the given position.
     *
     * @param position the int number from the position of the column in a line.
     *
     * @return a configurator that configure rules for the selected column.
     */
    GenericTypeConfigurator column(int position);

}
