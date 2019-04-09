package com.jonpereiradev.jfile.reader.rule.configurator;

import java.time.LocalDate;

public interface LocalDateTypeConfigurator extends TypedRuleConfigurator<LocalDateTypeConfigurator> {

    /**
     * defines a future rule validation.
     */
    LocalDateTypeConfigurator future();

    /**
     * defines a future or present rule validation.
     */
    LocalDateTypeConfigurator futureOrPresent();

    /**
     * defines a past rule validation.
     */
    LocalDateTypeConfigurator past();

    /**
     * defines a past or present rule validation.
     */
    LocalDateTypeConfigurator pastOrPresent();

    /**
     * defines a min date rule validation.
     */
    LocalDateTypeConfigurator min(LocalDate min);

    /**
     * defines a max date rule validation.
     */
    LocalDateTypeConfigurator max(LocalDate max);
}
