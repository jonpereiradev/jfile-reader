package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MinBigDecimalRule extends AbstractColumnRule {

    private final BigDecimal min;
    private final DecimalFormat decimalFormat;

    public MinBigDecimalRule(int position, BigDecimal min, DecimalFormat decimalFormat) {
        super(position);
        this.min = min;
        this.decimalFormat = decimalFormat;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return fileColumn.getBigDecimal(decimalFormat).compareTo(min) >= 0;
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return fileColumn.getBigDecimal(decimalFormat) != null;
    }
}
