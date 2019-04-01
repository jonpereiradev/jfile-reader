package com.jonpereiradev.jfile.reader.rule.configurator;

public interface LocalDateTimeConfigurator extends TypedRuleConfigurator<LocalDateTimeConfigurator> {

    LocalDateTimeConfigurator future();

    LocalDateTimeConfigurator futureOrPresent();

    LocalDateTimeConfigurator past();

    LocalDateTimeConfigurator pastOrPresent();

}
