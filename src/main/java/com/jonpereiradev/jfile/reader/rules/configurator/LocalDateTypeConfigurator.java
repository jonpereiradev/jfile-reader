package com.jonpereiradev.jfile.reader.rules.configurator;

public interface LocalDateTypeConfigurator extends TypedRuleConfigurator<LocalDateTypeConfigurator> {

    LocalDateTypeConfigurator future();

    LocalDateTypeConfigurator futureOrPresent();

    LocalDateTypeConfigurator past();

    LocalDateTypeConfigurator pastOrPresent();

}
