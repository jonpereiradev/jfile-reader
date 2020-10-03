package com.jonpereiradev.jfile.reader.validator.rule.configurator;

import java.math.BigInteger;

public interface BigIntegerTypeConfigurator extends TypedRuleConfigurator<BigIntegerTypeConfigurator> {

    /**
     * Defines a minimum value rule validation.
     *
     * @param min a {@link BigInteger} minimum number that the column accepts.
     *
     * @return the configurator with the min rule configured.
     */
    BigIntegerTypeConfigurator min(BigInteger min);

    /**
     * Defines a maximum value rule validation.
     *
     * @param max a {@link BigInteger} maximum number that the column accepts.
     *
     * @return the configurator with the max rule configured.
     */
    BigIntegerTypeConfigurator max(BigInteger max);

}
