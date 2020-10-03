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
import java.time.format.DateTimeParseException;
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

    private final int position;
    private final String content;

    ColumnValueImpl(JFilePatternConfig patternConfig, int position, String content) {
        this.patternConfig = patternConfig;
        this.position = position;
        this.content = RuleUtils.trimToEmpty(content);
    }

    @Override
    public int getPosition() {
        return position;
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
    public String[] getTextArray(Pattern pattern) {
        return getArrayOf(pattern, array -> Arrays.stream(array).map(ColumnValueImpl::getText).toArray(String[]::new));
    }

    @Override
    public Character getCharacter() {
        if (RuleUtils.isBlank(content) || content.length() > 1) {
            return null;
        }

        return content.charAt(0);
    }

    @Override
    public Character[] getCharacterArray() {
        return getCharacterArray(DEFAULT_ARRAY_SEPARATOR);
    }

    @Override
    public Character[] getCharacterArray(Pattern pattern) {
        return getArrayOf(
            pattern,
            array -> Arrays.stream(array).map(ColumnValueImpl::getCharacter).toArray(Character[]::new)
        );
    }

    @Override
    public Short getShort() {
        if (RuleUtils.isBlank(content)) {
            return null;
        }

        try {
            return Short.valueOf(content);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public Short[] getShortArray() {
        return getShortArray(DEFAULT_ARRAY_SEPARATOR);
    }

    @Override
    public Short[] getShortArray(Pattern pattern) {
        return getArrayOf(pattern, array -> Arrays.stream(array).map(ColumnValueImpl::getShort).toArray(Short[]::new));
    }

    @Override
    public Integer getInt() {
        if (RuleUtils.isBlank(content)) {
            return null;
        }

        try {
            return Integer.valueOf(content);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public Integer[] getIntArray() {
        return getIntArray(DEFAULT_ARRAY_SEPARATOR);
    }

    @Override
    public Integer[] getIntArray(Pattern pattern) {
        return getArrayOf(pattern, array -> Arrays.stream(array).map(ColumnValueImpl::getInt).toArray(Integer[]::new));
    }

    @Override
    public Long getLong() {
        if (RuleUtils.isBlank(content)) {
            return null;
        }

        try {
            return Long.valueOf(content);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public Long[] getLongArray() {
        return getLongArray(DEFAULT_ARRAY_SEPARATOR);
    }

    @Override
    public Long[] getLongArray(Pattern pattern) {
        return getArrayOf(pattern, array -> Arrays.stream(array).map(ColumnValueImpl::getLong).toArray(Long[]::new));
    }

    @Override
    public Float getFloat() {
        if (RuleUtils.isBlank(content)) {
            return null;
        }

        try {
            return Float.valueOf(content);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public Float[] getFloatArray() {
        return getFloatArray(DEFAULT_ARRAY_SEPARATOR);
    }

    @Override
    public Float[] getFloatArray(Pattern pattern) {
        return getArrayOf(pattern, array -> Arrays.stream(array).map(ColumnValueImpl::getFloat).toArray(Float[]::new));
    }

    @Override
    public Double getDouble() {
        if (RuleUtils.isBlank(content)) {
            return null;
        }

        try {
            return Double.valueOf(content);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public Double[] getDoubleArray() {
        return getDoubleArray(DEFAULT_ARRAY_SEPARATOR);
    }

    @Override
    public Double[] getDoubleArray(Pattern pattern) {
        return getArrayOf(
            pattern,
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
    public Boolean[] getBooleanArray(Pattern pattern) {
        return getArrayOf(
            pattern,
            array -> Arrays.stream(array).map(ColumnValueImpl::getBoolean).toArray(Boolean[]::new)
        );
    }

    @Override
    public BigInteger getBigInteger() {
        if (RuleUtils.isBlank(content)) {
            return null;
        }

        try {
            return new BigInteger(content);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public BigInteger[] getBigIntegerArray() {
        return getBigIntegerArray(DEFAULT_ARRAY_SEPARATOR);
    }

    @Override
    public BigInteger[] getBigIntegerArray(Pattern pattern) {
        return getArrayOf(
            pattern,
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
            return null;
        }
    }

    @Override
    public BigDecimal[] getBigDecimalArray() {
        return getBigDecimalArray(DEFAULT_ARRAY_SEPARATOR);
    }

    @Override
    public BigDecimal[] getBigDecimalArray(Pattern pattern) {
        return getBigDecimalArray(pattern, patternConfig.getBigDecimalFormatter());
    }

    @Override
    public BigDecimal[] getBigDecimalArray(Pattern pattern, DecimalFormat bigDecimalFormatter) {
        return getArrayOf(
            pattern,
            array -> Arrays.stream(array).map(o -> o.getBigDecimal(bigDecimalFormatter)).toArray(BigDecimal[]::new)
        );
    }

    @Override
    public Date getDate() {
        return getDate(patternConfig.getDateFormat());
    }

    @Override
    public Date getDate(DateFormat pattern) {
        if (RuleUtils.isBlank(content)) {
            return null;
        }

        try {
            return pattern.parse(content);
        } catch (ParseException e) {
            return null;
        }
    }

    @Override
    public Date[] getDateArray() {
        return getDateArray(DEFAULT_ARRAY_SEPARATOR);
    }

    @Override
    public Date[] getDateArray(Pattern pattern) {
        return getDateArray(pattern, patternConfig.getDateFormat());
    }

    @Override
    public Date[] getDateArray(Pattern pattern, DateFormat dateFormat) {
        return getArrayOf(pattern, array -> Arrays.stream(array).map(o -> o.getDate(dateFormat)).toArray(Date[]::new));
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

        try {
            return LocalDate.parse(content, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    @Override
    public LocalDate[] getLocalDateArray() {
        return getLocalDateArray(DEFAULT_ARRAY_SEPARATOR);
    }

    @Override
    public LocalDate[] getLocalDateArray(Pattern pattern) {
        return getLocalDateArray(pattern, patternConfig.getLocalDateFormatter());
    }

    @Override
    public LocalDate[] getLocalDateArray(Pattern pattern, DateTimeFormatter dateTimeFormatter) {
        return getArrayOf(
            pattern,
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

        try {
            return LocalDateTime.parse(content, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    @Override
    public LocalDateTime[] getLocalDateTimeArray() {
        return getLocalDateTimeArray(DEFAULT_ARRAY_SEPARATOR);
    }

    @Override
    public LocalDateTime[] getLocalDateTimeArray(Pattern pattern) {
        return getLocalDateTimeArray(pattern, patternConfig.getLocalDateTimeFormatter());
    }

    @Override
    public LocalDateTime[] getLocalDateTimeArray(Pattern pattern, DateTimeFormatter dateTimeFormatter) {
        return getArrayOf(
            pattern,
            array -> Arrays.stream(array).map(o -> o.getLocalDateTime(dateTimeFormatter)).toArray(LocalDateTime[]::new)
        );
    }

    @Override
    public JFilePatternConfig getPatternConfig() {
        return patternConfig;
    }

    private <T> T[] getArrayOf(Pattern pattern, Function<ColumnValueImpl[], T[]> function) {
        String[] split = pattern.split(content);
        return function.apply(Arrays.stream(split).map(s -> new ColumnValueImpl(patternConfig, position, s)).toArray(
            ColumnValueImpl[]::new));
    }

    @Override
    public int compareTo(ColumnValue o) {
        return Integer.compare(position, o.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColumnValueImpl that = (ColumnValueImpl) o;
        return position == that.position;
    }

}
