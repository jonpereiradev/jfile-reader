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
package com.jonpereiradev.jfile.reader.file;

import com.jonpereiradev.jfile.reader.JFilePatternConfig;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;

public interface ColumnValue extends Comparable<ColumnValue> {

    static ColumnValue newColumnValue(JFilePatternConfig patternConfig, int position, String content) {
        return new ColumnValueImpl(patternConfig, position, content);
    }

    int getColumnNumber();

    <T> T getContent(Class<T> clazz);

    String getText();

    String[] getTextArray();

    String[] getTextArray(Pattern splitPattern);

    Character getCharacter();

    Character[] getCharacterArray();

    Character[] getCharacterArray(Pattern splitPattern);

    Short getShort();

    Short[] getShortArray();

    Short[] getShortArray(Pattern splitPattern);

    Integer getInt();

    Integer[] getIntArray();

    Integer[] getIntArray(Pattern splitPattern);

    Long getLong();

    Long[] getLongArray();

    Long[] getLongArray(Pattern splitPattern);

    Float getFloat();

    Float[] getFloatArray();

    Float[] getFloatArray(Pattern splitPattern);

    Double getDouble();

    Double[] getDoubleArray();

    Double[] getDoubleArray(Pattern splitPattern);

    Boolean getBoolean();

    Boolean[] getBooleanArray();

    Boolean[] getBooleanArray(Pattern splitPattern);

    BigInteger getBigInteger();

    BigInteger[] getBigIntegerArray();

    BigInteger[] getBigIntegerArray(Pattern splitPattern);

    BigDecimal getBigDecimal();

    BigDecimal getBigDecimal(DecimalFormat bigDecimalFormatter);

    BigDecimal[] getBigDecimalArray();

    BigDecimal[] getBigDecimalArray(Pattern splitPattern);

    BigDecimal[] getBigDecimalArray(Pattern splitPattern, DecimalFormat bigDecimalFormatter);

    Date getDate();

    Date getDate(DateFormat dateFormat);

    Date[] getDateArray();

    Date[] getDateArray(Pattern splitPattern);

    Date[] getDateArray(Pattern splitPattern, DateFormat dateFormat);

    LocalDate getLocalDate();

    LocalDate getLocalDate(DateTimeFormatter dateTimeFormatter);

    LocalDate[] getLocalDateArray();

    LocalDate[] getLocalDateArray(Pattern splitPattern);

    LocalDate[] getLocalDateArray(Pattern splitPattern, DateTimeFormatter dateTimeFormatter);

    LocalDateTime getLocalDateTime();

    LocalDateTime getLocalDateTime(DateTimeFormatter dateTimeFormatter);

    LocalDateTime[] getLocalDateTimeArray();

    LocalDateTime[] getLocalDateTimeArray(Pattern splitPattern);

    LocalDateTime[] getLocalDateTimeArray(Pattern splitPattern, DateTimeFormatter dateTimeFormatter);

    JFilePatternConfig getPatternConfig();

}
