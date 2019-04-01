package com.jonpereiradev.jfile.reader.rule.configurator;

/**
 * Configurate the reference column rule.
 *
 * @param <T> the type of rule configurator returned.
 */
public interface RefRuleConfigurator<T> {

    /**
     * creates a rule for not empty column.
     */
    T filled();

    /**
     * creates a rule for column with specific values.
     */
    T filled(Object... values);

}
