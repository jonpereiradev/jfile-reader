package com.jonpereiradev.jfile.reader.validator.rule.configurator;

public interface FloatTypeConfigurator extends TypedRuleConfigurator<FloatTypeConfigurator> {

    /**
     * defines a min value rule validation.
     */
    FloatTypeConfigurator min(float min);

    /**
     * defines a max value rule validation.
     */
    FloatTypeConfigurator max(float max);

}
