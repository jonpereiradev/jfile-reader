package com.jonpereiradev.jfile.reader.rule.configurator;

public interface ShortTypeConfigurator extends TypedRuleConfigurator<ShortTypeConfigurator> {

    ShortTypeConfigurator min(short min);

    ShortTypeConfigurator max(short max);

    ShortTypeConfigurator domain(Short... values);

}
