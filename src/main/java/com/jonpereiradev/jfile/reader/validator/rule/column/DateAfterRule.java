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

import java.text.DateFormat;
import java.util.Date;


/**
 * @author jonpereiradev
 * @since 0.1.0
 */
public class DateAfterRule extends AbstractColumnRule {

    private final DateFormat dateFormat;
    private final Date min;
    private final int afterColumnNumber;

    public DateAfterRule(int afterColumnNumber, DateFormat dateFormat, Date min) {
        super(afterColumnNumber);
        this.dateFormat = dateFormat;
        this.min = min;
        this.afterColumnNumber = -1;
    }

    public DateAfterRule(int columnNumber, DateFormat dateFormat, int afterColumnNumber) {
        super(columnNumber);
        this.dateFormat = dateFormat;
        this.min = null;
        this.afterColumnNumber = afterColumnNumber;
    }

    @Override
    public boolean isValid(ColumnValue columnValue) {
        Date date = columnValue.getDate(dateFormat);
        return date.compareTo(getComparingDate()) > 0;
    }

    @Override
    public boolean canValidate(ColumnValue columnValue) {
        return columnValue.getDate(dateFormat) != null && getComparingDate() != null;
    }

    private Date getComparingDate() {
        if (afterColumnNumber == -1) {
            return min;
        }

        try {
            return getLineValue().getColumnValue(afterColumnNumber).getDate(dateFormat);
        } catch (IllegalStateException e) {
            return null;
        }
    }

}
