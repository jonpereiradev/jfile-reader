package com.jonpereiradev.jfile.reader.validator.rule.configurator;

public interface BooleanTypeConfigurator extends TypedRuleConfigurator<BooleanTypeConfigurator> {

    /**
     * Defines a domain rule validation with possible options.
     *
     * @param domains the valid values for a boolean type, like 'Y' or 'N', '0', or '1', etc.
     *
     * @return the configurator with the domain rule configured.
     */
    BooleanTypeConfigurator domain(Character... domains);

}
