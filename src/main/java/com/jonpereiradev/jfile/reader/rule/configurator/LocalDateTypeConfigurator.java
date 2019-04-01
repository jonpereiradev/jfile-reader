package com.jonpereiradev.jfile.reader.rule.configurator;

public interface LocalDateTypeConfigurator extends TypedRuleConfigurator<LocalDateTypeConfigurator> {

    LocalDateTypeConfigurator future();

    LocalDateTypeConfigurator futureOrPresent();

    LocalDateTypeConfigurator past();

    LocalDateTypeConfigurator pastOrPresent();

}
