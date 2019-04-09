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
    LocalDateTimeTypeConfigurator min(LocalDateTime min);

    /**
     * defines a max date rule validation.
     */
    LocalDateTimeTypeConfigurator max(LocalDateTime max);

}
