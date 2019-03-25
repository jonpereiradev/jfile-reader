package com.jonpereiradev.jfile.reader.rules.configurator;

import java.math.BigDecimal;

public interface BigDecimalTypeConfigurator extends TypedRuleConfigurator<BigDecimalTypeConfigurator> {

    BigDecimalTypeConfigurator min(BigDecimal min);

    BigDecimalTypeConfigurator max(BigDecimal max);

}
