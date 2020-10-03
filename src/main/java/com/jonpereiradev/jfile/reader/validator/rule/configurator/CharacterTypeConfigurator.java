package com.jonpereiradev.jfile.reader.validator.rule.configurator;

public interface CharacterTypeConfigurator extends TypedRuleConfigurator<CharacterTypeConfigurator> {

    /**
     * Defines a domain rule validation with possible options.
     *
     * @param domains the valid domains for a char type.
     *
     * @return the configurator with the domain rule configured.
     */
    CharacterTypeConfigurator domain(Character... domains);

}
