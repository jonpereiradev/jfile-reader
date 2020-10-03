package com.jonpereiradev.jfile.reader.validator.rule.configurator;

import com.jonpereiradev.jfile.reader.JFileReaderConfig;
import com.jonpereiradev.jfile.reader.validator.JFileValidatorConfig;
import com.jonpereiradev.jfile.reader.validator.rule.RuleNode;
import com.jonpereiradev.jfile.reader.validator.rule.column.ColumnRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.DomainIntegerRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.MaxIntegerRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.MinIntegerRule;

import java.util.Arrays;

final class IntegerTypeConfiguratorImpl extends AbstractRuleConfigurator<IntegerTypeConfigurator> implements IntegerTypeConfigurator {

    IntegerTypeConfiguratorImpl(int position, JFileValidatorConfig configuration, RuleNode<ColumnRule> ruleNode) {
        super(position, configuration, ruleNode);
    }

    @Override
    public IntegerTypeConfigurator min(int min) {
        return rule(position -> new MinIntegerRule(position, min));
    }

    @Override
    public IntegerTypeConfigurator max(int max) {
        return rule(position -> new MaxIntegerRule(position, max));
    }

    @Override
    public IntegerTypeConfigurator domain(Integer... values) {
        return rule(position -> new DomainIntegerRule(position, Arrays.asList(values)));
    }

}
