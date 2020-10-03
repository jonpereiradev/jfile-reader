package com.jonpereiradev.jfile.reader.validator.rule.configurator;

public interface IntegerTypeConfigurator extends TypedRuleConfigurator<IntegerTypeConfigurator> {

    /**
     * defines a min value rule validation.
     */
    IntegerTypeConfigurator min(int min);

    /**
     * defines a max value rule validation.
     */
    IntegerTypeConfigurator max(int max);

    /**
     * defines a domain rule validation with possible values options.
     */
    IntegerTypeConfigurator domain(Integer... values);

}
