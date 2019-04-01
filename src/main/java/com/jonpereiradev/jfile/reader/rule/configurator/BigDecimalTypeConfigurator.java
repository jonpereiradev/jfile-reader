package com.jonpereiradev.jfile.reader.rule.configurator;

import java.math.BigDecimal;

public interface BigDecimalTypeConfigurator extends TypedRuleConfigurator<BigDecimalTypeConfigurator> {

    BigDecimalTypeConfigurator min(BigDecimal min);

    BigDecimalTypeConfigurator max(BigDecimal max);

}
