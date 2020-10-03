package com.jonpereiradev.jfile.reader.validator.rule.configurator;

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
    LocalDateTypeConfigurator after(LocalDate min);

    /**
     * defines a min date rule validation comparing to another column.
     */
    LocalDateTypeConfigurator after(int columnPosition);

    /**
     * defines a max date rule validation.
     */
    LocalDateTypeConfigurator before(LocalDate max);

    /**
     * defines a max date rule validation comparing to another column.
     */
    LocalDateTypeConfigurator before(int columnPosition);

}
