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
import com.jonpereiradev.jfile.reader.validator.rule.RuleUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.regex.Pattern;


final class ColumnValueImpl implements ColumnValue {

    private static final Pattern DEFAULT_ARRAY_SEPARATOR = Pattern.compile(",\\s*");
    private static final ConcurrentMap<Class<?>, Function<ColumnValueImpl, ?>> MAPPER = new ConcurrentHashMap<>();

    static {
        MAPPER.putIfAbsent(char.class, ColumnValueImpl::getCharacter);
        MAPPER.putIfAbsent(Character.class, ColumnValueImpl::getCharacter);
        MAPPER.putIfAbsent(String.class, ColumnValueImpl::getText);
        MAPPER.putIfAbsent(short.class, ColumnValueImpl::getShort);
        MAPPER.putIfAbsent(Short.class, ColumnValueImpl::getShort);
        MAPPER.putIfAbsent(int.class, ColumnValueImpl::getInt);
        MAPPER.putIfAbsent(Integer.class, ColumnValueImpl::getInt);
        MAPPER.putIfAbsent(long.class, ColumnValueImpl::getLong);
        MAPPER.putIfAbsent(Long.class, ColumnValueImpl::getLong);
        MAPPER.putIfAbsent(float.class, ColumnValueImpl::getFloat);
        MAPPER.putIfAbsent(Float.class, ColumnValueImpl::getFloat);
        MAPPER.putIfAbsent(double.class, ColumnValueImpl::getDouble);
        MAPPER.putIfAbsent(Double.class, ColumnValueImpl::getDouble);
        MAPPER.putIfAbsent(boolean.class, ColumnValueImpl::getBoolean);
        MAPPER.putIfAbsent(Boolean.class, ColumnValueImpl::getBoolean);
        MAPPER.putIfAbsent(BigInteger.class, ColumnValueImpl::getBigInteger);
        MAPPER.putIfAbsent(BigDecimal.class, ColumnValueImpl::getBigDecimal);
        MAPPER.putIfAbsent(Date.class, ColumnValueImpl::getDate);
        MAPPER.putIfAbsent(LocalDate.class, ColumnValueImpl::getLocalDate);
        MAPPER.putIfAbsent(LocalDateTime.class, ColumnValueImpl::getLocalDateTime);
    }

    private final JFilePatternConfig patternConfig;

    private final int columnNumber;
    private final String content;

    ColumnValueImpl(JFilePatternConfig patternConfig, int columnNumber, String content) {
        this.patternConfig = patternConfig;
        this.columnNumber = columnNumber;
        this.content = RuleUtils.trimToEmpty(content);
    }

    @Override
    public int getColumnNumber() {
        return columnNumber;
    }

    @SuppressWarnings("unchecked")
    public <T> T getContent(Class<T> clazz) {
        return (T) MAPPER.get(clazz).apply(this);
    }

    @Override
    public String getText() {
        return content;
    }

    @Override
    public String[] getTextArray() {
        return getTextArray(DEFAULT_ARRAY_SEPARATOR);
    }

    @Override
    public String[] getTextArray(Pattern splitPattern) {
        return getArrayOf(
            splitPattern,
            array -> Arrays.stream(array).map(ColumnValueImpl::getText).toArray(String[]::new)
        );
    }

    @Override
    public Character getCharacter() {
        if (RuleUtils.isBlank(content)) {
            return null;
        }

        if (content.length() > 1) {
            throw new IllegalStateException("The value '" + content + "' is not parsable to Character");
        }

        return content.charAt(0);
    }

    @Override
    public Character[] getCharacterArray() {
        return getCharacterArray(DEFAULT_ARRAY_SEPARATOR);
    }

    @Override
    public Character[] getCharacterArray(Pattern splitPattern) {
        return getArrayOf(
            splitPattern,
            array -> Arrays.stream(array).map(ColumnValueImpl::getCharacter).toArray(Character[]::new)
        );
    }

    @Override
    public Short getShort() {
        if (RuleUtils.isBlank(content)) {
            return null;
        }

        return Short.valueOf(content);
    }

    @Override
    public Short[] getShortArray() {
        return getShortArray(DEFAULT_ARRAY_SEPARATOR);
    }

    @Override
    public Short[] getShortArray(Pattern splitPattern) {
        return getArrayOf(
            splitPattern,
            array -> Arrays.stream(array).map(ColumnValueImpl::getShort).toArray(Short[]::new)
        );
    }

    @Override
    public Integer getInt() {
        if (RuleUtils.isBlank(content)) {
            return null;
        }

        return Integer.valueOf(content);
    }

    @Override
    public Integer[] getIntArray() {
        return getIntArray(DEFAULT_ARRAY_SEPARATOR);
    }

    @Override
    public Integer[] getIntArray(Pattern splitPattern) {
        return getArrayOf(
            splitPattern,
            array -> Arrays.stream(array).map(ColumnValueImpl::getInt).toArray(Integer[]::new)
        );
    }

    @Override
    public Long getLong() {
        if (RuleUtils.isBlank(content)) {
            return null;
        }

        return Long.valueOf(content);
    }

    @Override
    public Long[] getLongArray() {
        return getLongArray(DEFAULT_ARRAY_SEPARATOR);
    }

    @Override
    public Long[] getLongArray(Pattern splitPattern) {
        return getArrayOf(
            splitPattern,
            array -> Arrays.stream(array).map(ColumnValueImpl::getLong).toArray(Long[]::new)
        );
    }

    @Override
    public Float getFloat() {
        if (RuleUtils.isBlank(content)) {
            return null;
        }

        return Float.valueOf(content);
    }

    @Override
    public Float[] getFloatArray() {
        return getFloatArray(DEFAULT_ARRAY_SEPARATOR);
    }

    @Override
    public Float[] getFloatArray(Pattern splitPattern) {
        return getArrayOf(
            splitPattern,
            array -> Arrays.stream(array).map(ColumnValueImpl::getFloat).toArray(Float[]::new)
        );
    }

    @Override
    public Double getDouble() {
        if (RuleUtils.isBlank(content)) {
            return null;
        }

        return Double.valueOf(content);
    }

    @Override
    public Double[] getDoubleArray() {
        return getDoubleArray(DEFAULT_ARRAY_SEPARATOR);
    }

    @Override
    public Double[] getDoubleArray(Pattern splitPattern) {
        return getArrayOf(
            splitPattern,
            array -> Arrays.stream(array).map(ColumnValueImpl::getDouble).toArray(Double[]::new)
        );
    }

    @Override
    public Boolean getBoolean() {
        if (RuleUtils.isBlank(content)) {
            return null;
        }

        String booleanString = content;

        if (booleanString.equals("0")) {
            booleanString = "false";
        } else if (booleanString.equals("1")) {
            booleanString = "true";
        }

        return RuleUtils.toBooleanObject(booleanString);
    }

    @Override
    public Boolean[] getBooleanArray() {
        return getBooleanArray(DEFAULT_ARRAY_SEPARATOR);
    }

    @Override
    public Boolean[] getBooleanArray(Pattern splitPattern) {
        return getArrayOf(
            splitPattern,
            array -> Arrays.stream(array).map(ColumnValueImpl::getBoolean).toArray(Boolean[]::new)
        );
    }

    @Override
    public BigInteger getBigInteger() {
        if (RuleUtils.isBlank(content)) {
            return null;
        }

        return new BigInteger(content);
    }

    @Override
    public BigInteger[] getBigIntegerArray() {
        return getBigIntegerArray(DEFAULT_ARRAY_SEPARATOR);
    }

    @Override
    public BigInteger[] getBigIntegerArray(Pattern splitPattern) {
        return getArrayOf(
            splitPattern,
            array -> Arrays.stream(array).map(ColumnValueImpl::getBigInteger).toArray(BigInteger[]::new)
        );
    }

    @Override
    public BigDecimal getBigDecimal() {
        return getBigDecimal(patternConfig.getBigDecimalFormatter());
    }

    @Override
    public BigDecimal getBigDecimal(DecimalFormat bigDecimalFormatter) {
        if (RuleUtils.isBlank(content)) {
            return null;
        }

        try {
            return (BigDecimal) bigDecimalFormatter.parse(content);
        } catch (ParseException e) {
            throw new NumberFormatException(
                "The value '" + content + "' is not parsable to BigDecimal with current DecimalFormat"
            );
        }
    }

    @Override
    public BigDecimal[] getBigDecimalArray() {
        return getBigDecimalArray(DEFAULT_ARRAY_SEPARATOR);
    }

    @Override
    public BigDecimal[] getBigDecimalArray(Pattern splitPattern) {
        return getBigDecimalArray(splitPattern, patternConfig.getBigDecimalFormatter());
    }

    @Override
    public BigDecimal[] getBigDecimalArray(Pattern splitPattern, DecimalFormat bigDecimalFormatter) {
        return getArrayOf(
            splitPattern,
            array -> Arrays.stream(array).map(o -> o.getBigDecimal(bigDecimalFormatter)).toArray(BigDecimal[]::new)
        );
    }

    @Override
    public Date getDate() {
        return getDate(patternConfig.getDateFormat());
    }

    @Override
    public Date getDate(DateFormat dateFormat) {
        if (RuleUtils.isBlank(content)) {
            return null;
        }

        try {
            return dateFormat.parse(content);
        } catch (ParseException e) {
            throw new IllegalStateException(
                "The value '" + content + "' is not parsable to Date with current DateFormat"
            );
        }
    }

    @Override
    public Date[] getDateArray() {
        return getDateArray(DEFAULT_ARRAY_SEPARATOR);
    }

    @Override
    public Date[] getDateArray(Pattern splitPattern) {
        return getDateArray(splitPattern, patternConfig.getDateFormat());
    }

    @Override
    public Date[] getDateArray(Pattern splitPattern, DateFormat dateFormat) {
        return getArrayOf(
            splitPattern,
            array -> Arrays.stream(array).map(o -> o.getDate(dateFormat)).toArray(Date[]::new)
        );
    }

    @Override
    public LocalDate getLocalDate() {
        return getLocalDate(patternConfig.getLocalDateFormatter());
    }

    @Override
    public LocalDate getLocalDate(DateTimeFormatter dateTimeFormatter) {
        if (RuleUtils.isBlank(content)) {
            return null;
        }

        return LocalDate.parse(content, dateTimeFormatter);
    }

    @Override
    public LocalDate[] getLocalDateArray() {
        return getLocalDateArray(DEFAULT_ARRAY_SEPARATOR);
    }

    @Override
    public LocalDate[] getLocalDateArray(Pattern splitPattern) {
        return getLocalDateArray(splitPattern, patternConfig.getLocalDateFormatter());
    }

    @Override
    public LocalDate[] getLocalDateArray(Pattern splitPattern, DateTimeFormatter dateTimeFormatter) {
        return getArrayOf(
            splitPattern,
            array -> Arrays.stream(array).map(o -> o.getLocalDate(dateTimeFormatter)).toArray(LocalDate[]::new)
        );
    }

    @Override
    public LocalDateTime getLocalDateTime() {
        return getLocalDateTime(patternConfig.getLocalDateTimeFormatter());
    }

    @Override
    public LocalDateTime getLocalDateTime(DateTimeFormatter dateTimeFormatter) {
        if (RuleUtils.isBlank(content)) {
            return null;
        }

        return LocalDateTime.parse(content, dateTimeFormatter);
    }

    @Override
    public LocalDateTime[] getLocalDateTimeArray() {
        return getLocalDateTimeArray(DEFAULT_ARRAY_SEPARATOR);
    }

    @Override
    public LocalDateTime[] getLocalDateTimeArray(Pattern splitPattern) {
        return getLocalDateTimeArray(splitPattern, patternConfig.getLocalDateTimeFormatter());
    }

    @Override
    public LocalDateTime[] getLocalDateTimeArray(Pattern splitPattern, DateTimeFormatter dateTimeFormatter) {
        return getArrayOf(
            splitPattern,
            array -> Arrays.stream(array).map(o -> o.getLocalDateTime(dateTimeFormatter)).toArray(LocalDateTime[]::new)
        );
    }

    @Override
    public JFilePatternConfig getPatternConfig() {
        return patternConfig;
    }

    private <T> T[] getArrayOf(Pattern splitPattern, Function<ColumnValueImpl[], T[]> function) {
        String[] split = splitPattern.split(content);

        ColumnValueImpl[] columnValueStream = Arrays
            .stream(split)
            .map(content -> new ColumnValueImpl(patternConfig, columnNumber, content))
            .toArray(ColumnValueImpl[]::new);

        return function.apply(columnValueStream);
    }

    @Override
    public int compareTo(ColumnValue o) {
        return Integer.compare(columnNumber, o.getColumnNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(columnNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColumnValueImpl that = (ColumnValueImpl) o;
        return columnNumber == that.columnNumber;
    }

}
