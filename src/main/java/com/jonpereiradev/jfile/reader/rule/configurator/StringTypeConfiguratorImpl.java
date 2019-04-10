package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;
import com.jonpereiradev.jfile.reader.rule.RuleNode;
import com.jonpereiradev.jfile.reader.rule.column.*;

import java.util.Arrays;
import java.util.regex.Pattern;

final class StringTypeConfiguratorImpl extends AbstractRuleConfigurator<StringTypeConfigurator> implements StringTypeConfigurator {

    StringTypeConfiguratorImpl(int position, ReaderConfiguration configuration, RuleNode<ColumnRule> ruleNode) {
        super(position, configuration, ruleNode);
    }

    @Override
    public StringTypeConfigurator notEmpty() {
        return rule(NotEmptyRule::new);
    }

    @Override
    public StringTypeConfigurator min(int min) {
        return rule(position -> new MinStringRule(position, min));
    }

    @Override
    public StringTypeConfigurator max(int max) {
        return rule(position -> new MaxStringRule(position, max));
    }

    @Override
    public StringTypeConfigurator domain(String... values) {
        return rule(position -> new DomainStringRule(position, Arrays.asList(values)));
    }

    @Override
    public StringTypeConfigurator email() {
        return rule(EmailRule::new);
    }

    @Override
    public StringTypeConfigurator cpf() {
        return rule(CpfRule::new);
    }

    @Override
    public StringTypeConfigurator cnpj() {
        return rule(CnpjRule::new);
    }

    @Override
    public StringTypeConfigurator regex(Pattern pattern) {
        return rule(position -> new RegexRule(position, pattern));
    }
}
