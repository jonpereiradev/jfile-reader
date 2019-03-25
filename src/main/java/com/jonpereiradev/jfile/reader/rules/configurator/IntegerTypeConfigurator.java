package com.jonpereiradev.jfile.reader.rules.configurator;

public interface IntegerTypeConfigurator extends TypedRuleConfigurator<IntegerTypeConfigurator> {

    IntegerTypeConfigurator min(int min);

    IntegerTypeConfigurator max(int max);

    IntegerTypeConfigurator domain(Integer... values);

}
