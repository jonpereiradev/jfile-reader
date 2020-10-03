package com.jonpereiradev.jfile.reader.validator.rule.configurator;

public interface ShortTypeConfigurator extends TypedRuleConfigurator<ShortTypeConfigurator> {

    /**
     * defines a min value rule validation.
     */
    ShortTypeConfigurator min(short min);

    /**
     * defines a max value rule validation.
     */
    ShortTypeConfigurator max(short max);

    /**
     * defines a domain rule validation with possible values options.
     */
    ShortTypeConfigurator domain(Short... values);

}
