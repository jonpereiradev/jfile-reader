package com.jonpereiradev.jfile.reader.validator.rule.configurator;

public interface DoubleTypeConfigurator extends TypedRuleConfigurator<DoubleTypeConfigurator> {

    /**
     * defines a min value rule validation.
     */
    DoubleTypeConfigurator min(double min);

    /**
     * defines a max value rule validation.
     */
    DoubleTypeConfigurator max(double max);

}
