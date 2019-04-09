package com.jonpereiradev.jfile.reader.rule.configurator;

import java.math.BigInteger;

public interface BigIntegerTypeConfigurator extends TypedRuleConfigurator<BigIntegerTypeConfigurator> {

    /**
     * defines a min value rule validation.
     */
    BigIntegerTypeConfigurator min(BigInteger min);

    /**
     * defines a max value rule validation.
     */
    BigIntegerTypeConfigurator max(BigInteger max);

}
