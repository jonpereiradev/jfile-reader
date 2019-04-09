package com.jonpereiradev.jfile.reader.rule.configurator;

public interface CharacterTypeConfigurator extends TypedRuleConfigurator<CharacterTypeConfigurator> {

    /**
     * defines a domain rule validation with possible values options.
     */
    CharacterTypeConfigurator domain(Character... values);

}
