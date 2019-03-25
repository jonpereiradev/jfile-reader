package com.jonpereiradev.jfile.reader.rules.configurator;

public interface ShortTypeConfigurator extends TypedRuleConfigurator<ShortTypeConfigurator> {

    ShortTypeConfigurator min(short min);

    ShortTypeConfigurator max(short max);

    ShortTypeConfigurator domain(Short... values);

}
