package com.jonpereiradev.jfile.reader.rule.configurator;

import java.math.BigDecimal;

public interface BigDecimalTypeConfigurator extends TypedRuleConfigurator<BigDecimalTypeConfigurator> {

    /**
     * defines a min value rule validation.
     */
    BigDecimalTypeConfigurator min(BigDecimal min);

    /**
     * defines a max value rule validation.
     */
    BigDecimalTypeConfigurator max(BigDecimal max);

}
