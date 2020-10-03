package com.jonpereiradev.jfile.reader.validator.rule.configurator;

import java.math.BigDecimal;

public interface BigDecimalTypeConfigurator extends TypedRuleConfigurator<BigDecimalTypeConfigurator> {

    /**
     * Defines a minimum value rule validation.
     *
     * @param min a {@link BigDecimal} minimum number that the column accepts.
     *
     * @return the configurator with the min rule configured.
     */
    BigDecimalTypeConfigurator min(BigDecimal min);

    /**
     * Defines a maximum value rule validation.
     *
     * @param max a {@link BigDecimal} maximum number that the column accepts.
     *
     * @return the configurator with the max rule configured.
     */
    BigDecimalTypeConfigurator max(BigDecimal max);

}
