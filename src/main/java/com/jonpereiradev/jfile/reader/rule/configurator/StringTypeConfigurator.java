package com.jonpereiradev.jfile.reader.rule.configurator;

import java.util.regex.Pattern;

public interface StringTypeConfigurator extends TypedRuleConfigurator<StringTypeConfigurator> {

    /**
     * defines a not empty rule validation.
     */
    StringTypeConfigurator notEmpty();

    /**
     * defines a min length rule validation.
     */
    StringTypeConfigurator min(int min);

    /**
     * defines a max length rule validation.
     */
    StringTypeConfigurator max(int max);

    /**
     * defines a domain rule validation with possible values options.
     */
    StringTypeConfigurator domain(String... values);

    /**
     * defines an email rule validation.
     */
    StringTypeConfigurator email();

    /**
     * defines a brazilian document CPF rule validation.
     */
    StringTypeConfigurator cpf();

    /**
     * defines a brazilian document CNPJ rule validation.
     */
    StringTypeConfigurator cnpj();

    /**
     * defines a pattern rule validation.
     */
    StringTypeConfigurator regex(Pattern pattern);

}
