package com.jonpereiradev.jfile.reader.validator.rule.configurator;

import java.util.Date;

public interface DateTypeConfigurator extends TypedRuleConfigurator<DateTypeConfigurator> {

    /**
     * Defines a future value rule validation.
     * Used for validate that a date must be after the present date.
     *
     * @return the configurator with the future rule configured.
     */
    DateTypeConfigurator future();

    /**
     * Defines a future or present value rule validation.
     * Used for validate that a date must be equal or after the present date.
     *
     * @return the configurator with the future or present rule configured.
     */
    DateTypeConfigurator futureOrPresent();

    /**
     * Defines a past value rule validation.
     * Used for validate that a date must be before the present date.
     *
     * @return the configurator with the before rule configured.
     */
    DateTypeConfigurator past();

    /**
     * Defines a before or present value rule validation.
     * Used for validate that a date must be before or equal the present date.
     *
     * @return the configurator with the before or present rule configured.
     */
    DateTypeConfigurator pastOrPresent();

    /**
     * Defines a after date rule validation.
     * Used for validate that a date must be after the date parameter.
     *
     * @param date the date that is used to compare in the rule.
     *
     * @return the configurator with the after date rule configured.
     */
    DateTypeConfigurator after(Date date);

    /**
     * Defines after other column value rule validation.
     * Used for validate that a date must be after another date column in the line.
     *
     * @param position an int position reference to other date column in the line.
     *
     * @return the configurator with the after column value rule configured.
     */
    DateTypeConfigurator after(int position);

    /**
     * Defines a before date rule validation.
     * Used for validate that a date must be before the date parameter.
     *
     * @param date the date that is used to compare in the rule.
     *
     * @return the configurator with the before date rule configured.
     */
    DateTypeConfigurator before(Date date);

    /**
     * Defines before other column value rule validation.
     * Used for validate that a date must be before another date column in the line.
     *
     * @param position an int position reference to other date column in the line.
     *
     * @return the configurator with the before column value rule configured.
     */
    DateTypeConfigurator before(int position);

}
