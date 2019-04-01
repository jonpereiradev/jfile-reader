package com.jonpereiradev.jfile.reader.rule.configurator;

public interface DateTypeConfigurator extends TypedRuleConfigurator<DateTypeConfigurator> {

    DateTypeConfigurator future();

    DateTypeConfigurator futureOrPresent();

    DateTypeConfigurator past();

    DateTypeConfigurator pastOrPresent();

}
