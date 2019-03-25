package com.jonpereiradev.jfile.reader.rules.configurator;

import com.jonpereiradev.jfile.reader.rules.RuleConfiguratorContext;
import com.jonpereiradev.jfile.reader.rules.column.MaxBigDecimalRule;
import com.jonpereiradev.jfile.reader.rules.column.MinBigDecimalRule;

import java.math.BigDecimal;
import java.text.DecimalFormat;

final class BigDecimalTypeConfiguratorImpl
    extends TypedRuleConfiguratorImpl<BigDecimalTypeConfigurator> implements BigDecimalTypeConfigurator {

    private final DecimalFormat decimalFormat;

    BigDecimalTypeConfiguratorImpl(int position, DecimalFormat decimalFormat, RuleConfiguratorContext context) {
        super(position, context);
        this.decimalFormat = decimalFormat;
    }

    @Override
    public BigDecimalTypeConfigurator min(BigDecimal min) {
        return rule(new MinBigDecimalRule(position, min, decimalFormat));
    }

    @Override
    public BigDecimalTypeConfigurator max(BigDecimal max) {
        return rule(new MaxBigDecimalRule(position, max, decimalFormat));
    }

}
