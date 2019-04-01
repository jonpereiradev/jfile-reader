package com.jonpereiradev.jfile.reader.rule.configurator;

public interface DoubleTypeConfigurator extends TypedRuleConfigurator<DoubleTypeConfigurator> {

    DoubleTypeConfigurator min(double min);

    DoubleTypeConfigurator max(double max);

}
