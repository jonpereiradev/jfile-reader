package com.jonpereiradev.jfile.reader.rule.configurator;

import java.time.LocalDateTime;

public interface LocalDateTimeTypeConfigurator extends TypedRuleConfigurator<LocalDateTimeTypeConfigurator> {

    /**
     * defines a future rule validation.
     */
    LocalDateTimeTypeConfigurator future();

    /**
     * defines a future or present rule validation.
     */
    LocalDateTimeTypeConfigurator futureOrPresent();

    /**
     * defines a past rule validation.
     */
    LocalDateTimeTypeConfigurator past();

    /**
     * defines a past or present rule validation.
     */
    LocalDateTimeTypeConfigurator pastOrPresent();

    /**
     * defines a min date rule validation.
     */
    LocalDateTimeTypeConfigurator after(LocalDateTime min);

    /**
     * defines a min date rule validation comparing to another column.
     */
    LocalDateTimeTypeConfigurator after(int columnPosition);

    /**
     * defines a max date rule validation.
     */
    LocalDateTimeTypeConfigurator before(LocalDateTime max);

    /**
     * defines a max date rule validation comparing to another column.
     */
    LocalDateTimeTypeConfigurator before(int columnPosition);

}
