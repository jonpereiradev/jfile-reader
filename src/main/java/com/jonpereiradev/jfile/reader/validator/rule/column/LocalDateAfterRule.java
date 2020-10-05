/*
 * MIT License
 *
 * Copyright (c) 2020 Jonathan de Almeida Pereira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.jonpereiradev.jfile.reader.validator.rule.column;


import com.jonpereiradev.jfile.reader.file.ColumnValue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * @author jonpereiradev
 * @since 0.1.0
 */
public class LocalDateAfterRule extends AbstractColumnRule {

    private final DateTimeFormatter dateTimeFormatter;
    private final LocalDate min;
    private final int refColumnNumber;

    public LocalDateAfterRule(int columnNumber, DateTimeFormatter dateTimeFormatter, LocalDate min) {
        super(columnNumber);
        this.dateTimeFormatter = dateTimeFormatter;
        this.min = min;
        this.refColumnNumber = -1;
    }

    public LocalDateAfterRule(int columnNumber, DateTimeFormatter dateTimeFormatter, int refColumnNumber) {
        super(columnNumber);
        this.dateTimeFormatter = dateTimeFormatter;
        this.min = null;
        this.refColumnNumber = refColumnNumber;
    }

    @Override
    public boolean isValid(ColumnValue columnValue) {
        LocalDate date = columnValue.getLocalDate(dateTimeFormatter);
        return date.compareTo(getComparingDate()) > 0;
    }

    @Override
    public boolean canValidate(ColumnValue columnValue) {
        return columnValue.getLocalDate(dateTimeFormatter) != null && getComparingDate() != null;
    }

    private LocalDate getComparingDate() {
        if (refColumnNumber == -1) {
            return min;
        }

        try {
            return getLineValue().getColumnValue(refColumnNumber).getLocalDate(dateTimeFormatter);
        } catch (DateTimeParseException e) {
            return null;
        }

    }

}
