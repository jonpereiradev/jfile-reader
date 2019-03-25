package com.jonpereiradev.jfile.reader.rules.configurator;

import java.math.BigInteger;

public interface BigIntegerTypeConfigurator extends TypedRuleConfigurator<BigIntegerTypeConfigurator> {

    BigIntegerTypeConfigurator min(BigInteger min);

    BigIntegerTypeConfigurator max(BigInteger max);

}
