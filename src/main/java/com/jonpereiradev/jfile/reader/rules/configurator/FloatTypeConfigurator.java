package com.jonpereiradev.jfile.reader.rules.configurator;

public interface FloatTypeConfigurator extends TypedRuleConfigurator<FloatTypeConfigurator> {

    FloatTypeConfigurator min(float min);

    FloatTypeConfigurator max(float max);

}
