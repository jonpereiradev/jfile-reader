package com.jonpereiradev.jfile.reader.rules.configurator;

import java.util.regex.Pattern;

public interface StringTypeConfigurator extends TypedRuleConfigurator<StringTypeConfigurator> {

    StringTypeConfigurator notEmpty();

    StringTypeConfigurator min(int min);

    StringTypeConfigurator max(int max);

    StringTypeConfigurator domain(String... values);

    StringTypeConfigurator email();

    StringTypeConfigurator cpf();

    StringTypeConfigurator cnpj();

    StringTypeConfigurator regex(Pattern pattern);

}
