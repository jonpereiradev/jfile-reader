package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.text.DecimalFormat;

public class BigDecimalTypeRule extends AbstractColumnRule {

    private final DecimalFormat decimalFormat;

    public BigDecimalTypeRule(int position, DecimalFormat decimalFormat) {
        super(position);
        this.decimalFormat = decimalFormat;
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        return fileColumn.getText().isEmpty() || fileColumn.getBigDecimal(decimalFormat) != null;
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return true;
    }
}
