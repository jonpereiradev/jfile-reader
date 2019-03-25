package com.jonpereiradev.jfile.reader.rules.configurator;

import com.jonpereiradev.jfile.reader.rules.RuleConfiguratorContext;
import com.jonpereiradev.jfile.reader.rules.column.CnpjRule;
import com.jonpereiradev.jfile.reader.rules.column.CpfRule;
import com.jonpereiradev.jfile.reader.rules.column.DomainStringRule;
import com.jonpereiradev.jfile.reader.rules.column.EmailRule;
import com.jonpereiradev.jfile.reader.rules.column.MaxStringRule;
import com.jonpereiradev.jfile.reader.rules.column.MinStringRule;
import com.jonpereiradev.jfile.reader.rules.column.NotEmptyRule;
import com.jonpereiradev.jfile.reader.rules.column.RegexRule;

import java.util.Arrays;
import java.util.regex.Pattern;

final class StringTypeConfiguratorImpl extends TypedRuleConfiguratorImpl<StringTypeConfigurator> implements StringTypeConfigurator {

    StringTypeConfiguratorImpl(int position, RuleConfiguratorContext context) {
        super(position, context);
    }

    @Override
    public StringTypeConfigurator notEmpty() {
        return rule(new NotEmptyRule(position));
    }

    @Override
    public StringTypeConfigurator min(int min) {
        return rule(new MinStringRule(position, min));
    }

    @Override
    public StringTypeConfigurator max(int max) {
        return rule(new MaxStringRule(position, max));
    }

    @Override
    public StringTypeConfigurator domain(String... values) {
        return rule(new DomainStringRule(position, Arrays.asList(values)));
    }

    @Override
    public StringTypeConfigurator email() {
        return rule(new EmailRule(position));
    }

    @Override
    public StringTypeConfigurator cpf() {
        return rule(new CpfRule(position));
    }

    @Override
    public StringTypeConfigurator cnpj() {
        return rule(new CnpjRule(position));
    }

    @Override
    public StringTypeConfigurator regex(Pattern pattern) {
        return rule(new RegexRule(position, pattern));
    }
}
