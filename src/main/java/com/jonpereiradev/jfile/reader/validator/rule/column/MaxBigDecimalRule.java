package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MaxBigDecimalRule extends AbstractColumnRule {

    private final BigDecimal max;
    private final DecimalFormat decimalFormat;

    public MaxBigDecimalRule(int position, BigDecimal max, DecimalFormat decimalFormat) {
        super(position);
        this.max = max;
        this.decimalFormat = decimalFormat;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return fileColumn.getBigDecimal(decimalFormat).compareTo(max) <= 0;
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return fileColumn.getBigDecimal(decimalFormat) != null;
    }
}
