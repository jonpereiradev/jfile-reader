package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;
import com.jonpereiradev.jfile.reader.rule.RuleNode;
import com.jonpereiradev.jfile.reader.rule.column.ColumnRule;
import com.jonpereiradev.jfile.reader.rule.column.MaxBigDecimalRule;
import com.jonpereiradev.jfile.reader.rule.column.MinBigDecimalRule;

import java.math.BigDecimal;
import java.text.DecimalFormat;

final class BigDecimalTypeConfiguratorImpl
    extends AbstractRuleConfigurator<BigDecimalTypeConfigurator> implements BigDecimalTypeConfigurator {

    private final DecimalFormat decimalFormat;

    BigDecimalTypeConfiguratorImpl(
        int position,
        DecimalFormat decimalFormat,
        ReaderConfiguration configuration,
        RuleNode<ColumnRule> ruleNode
    ) {
        super(position, configuration, ruleNode);
        this.decimalFormat = decimalFormat;
    }

    @Override
    public BigDecimalTypeConfigurator min(BigDecimal min) {
        return rule(position -> new MinBigDecimalRule(position, min, decimalFormat));
    }

    @Override
    public BigDecimalTypeConfigurator max(BigDecimal max) {
        return rule(position -> new MaxBigDecimalRule(position, max, decimalFormat));
    }

}
