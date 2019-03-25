package com.jonpereiradev.jfile.reader.rules.configurator;

public interface LocalDateTimeConfigurator extends TypedRuleConfigurator<LocalDateTimeConfigurator> {

    LocalDateTimeConfigurator future();

    LocalDateTimeConfigurator futureOrPresent();

    LocalDateTimeConfigurator past();

    LocalDateTimeConfigurator pastOrPresent();

}
