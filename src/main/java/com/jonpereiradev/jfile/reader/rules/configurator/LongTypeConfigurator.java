package com.jonpereiradev.jfile.reader.rules.configurator;

public interface LongTypeConfigurator extends TypedRuleConfigurator<LongTypeConfigurator> {

    LongTypeConfigurator min(long min);

    LongTypeConfigurator max(long max);

    LongTypeConfigurator domain(Long... values);

}
