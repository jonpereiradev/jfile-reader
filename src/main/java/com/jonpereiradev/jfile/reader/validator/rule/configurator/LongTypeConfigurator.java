package com.jonpereiradev.jfile.reader.validator.rule.configurator;

public interface LongTypeConfigurator extends TypedRuleConfigurator<LongTypeConfigurator> {

    /**
     * defines a min value rule validation.
     */
    LongTypeConfigurator min(long min);

    /**
     * defines a max value rule validation.
     */
    LongTypeConfigurator max(long max);

    /**
     * defines a domain rule validation with possible values options.
     */
    LongTypeConfigurator domain(Long... values);

}
