package com.jonpereiradev.jfile.reader.rule.configurator;

import java.util.Date;

public interface DateTypeConfigurator extends TypedRuleConfigurator<DateTypeConfigurator> {

    /**
     * defines a future rule validation.
     */
    DateTypeConfigurator future();

    /**
     * defines a future or present rule validation.
     */
    DateTypeConfigurator futureOrPresent();

    /**
     * defines a past rule validation.
     */
    DateTypeConfigurator past();

    /**
     * defines a past or present rule validation.
     */
    DateTypeConfigurator pastOrPresent();

    /**
     * defines a min date rule validation.
     */
    DateTypeConfigurator min(Date min);

    /**
     * defines a max date rule validation.
     */
    DateTypeConfigurator max(Date max);

}
