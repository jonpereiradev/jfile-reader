package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

import java.text.DecimalFormat;

public class BigDecimalTypeRule extends AbstractColumnRule {

    private final DecimalFormat decimalFormat;

    public BigDecimalTypeRule(int position, DecimalFormat decimalFormat) {
        super(position);
        this.decimalFormat = decimalFormat;
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        return fileColumn.getText().isEmpty() || fileColumn.getBigDecimal(decimalFormat) != null;
    }

    @Override
    public boolean canValidate(ColumnValue fileColumn) {
        return true;
    }
}
