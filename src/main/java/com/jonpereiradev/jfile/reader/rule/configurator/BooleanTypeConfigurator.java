package com.jonpereiradev.jfile.reader.rule.configurator;

public interface BooleanTypeConfigurator extends TypedRuleConfigurator<BooleanTypeConfigurator> {

    /**
     * defines a domain rule validation with possible values options.
     */
    BooleanTypeConfigurator domain(Character... values);

}
