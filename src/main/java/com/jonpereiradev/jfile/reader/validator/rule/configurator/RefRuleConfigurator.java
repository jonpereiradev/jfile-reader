package com.jonpereiradev.jfile.reader.validator.rule.configurator;

/**
 * Configurate the reference column rule.
 *
 * @param <T> the type of rule configurator returned.
 */
public interface RefRuleConfigurator<T extends TypedRuleConfigurator<?>> {

    /**
     * creates a rule for not empty column.
     */
    T filled();

    /**
     * creates a rule for column with specific values.
     */
    T filled(Object... values);

    /**
     * creates a rule for column with empty value.
     */
    T empty();

}
