package com.jonpereiradev.jfile.reader.file;

import com.jonpereiradev.jfile.reader.JFileReaderContext;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

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

public class JFileColumn implements Comparable<JFileColumn> {

    private static final Pattern DEFAULT_ARRAY_SEPARATOR = Pattern.compile(",\\s*");
    private static final ConcurrentMap<Class<?>, Function<JFileColumn, ?>> MAPPER = new ConcurrentHashMap<>();

    static {
        MAPPER.putIfAbsent(char.class, JFileColumn::getCharacter);
        MAPPER.putIfAbsent(Character.class, JFileColumn::getCharacter);
        MAPPER.putIfAbsent(String.class, JFileColumn::getText);
        MAPPER.putIfAbsent(short.class, JFileColumn::getShort);
        MAPPER.putIfAbsent(Short.class, JFileColumn::getShort);
        MAPPER.putIfAbsent(int.class, JFileColumn::getInt);
        MAPPER.putIfAbsent(Integer.class, JFileColumn::getInt);
        MAPPER.putIfAbsent(long.class, JFileColumn::getLong);
        MAPPER.putIfAbsent(Long.class, JFileColumn::getLong);
        MAPPER.putIfAbsent(float.class, JFileColumn::getFloat);
        MAPPER.putIfAbsent(Float.class, JFileColumn::getFloat);
        MAPPER.putIfAbsent(double.class, JFileColumn::getDouble);
        MAPPER.putIfAbsent(Double.class, JFileColumn::getDouble);
        MAPPER.putIfAbsent(boolean.class, JFileColumn::getBoolean);
        MAPPER.putIfAbsent(Boolean.class, JFileColumn::getBoolean);
        MAPPER.putIfAbsent(BigInteger.class, JFileColumn::getBigInteger);
        MAPPER.putIfAbsent(BigDecimal.class, JFileColumn::getBigDecimal);
        MAPPER.putIfAbsent(Date.class, JFileColumn::getDate);
        MAPPER.putIfAbsent(LocalDate.class, JFileColumn::getLocalDate);
        MAPPER.putIfAbsent(LocalDateTime.class, JFileColumn::getLocalDateTime);
    }

    private final JFileReaderContext context;

    private final int position;
    private final String content;

    public JFileColumn(JFileReaderContext context, int position, String content) {
        this.context = context;
        this.position = position;
        this.content = content;
    }

    public int getPosition() {
        return position;
    }

    @SuppressWarnings("unchecked")
    public <T> T getContent(Class<T> clazz) {
        return (T) MAPPER.get(clazz).apply(this);
    }

    public String getText() {
        return content;
    }

    public String[] getTextArray() {
        return getTextArray(DEFAULT_ARRAY_SEPARATOR);
    }

    public String[] getTextArray(Pattern pattern) {
        return getArrayOf(pattern, array -> Arrays.stream(array).map(JFileColumn::getText).toArray(String[]::new));
    }

    public Character getCharacter() {
        if (StringUtils.isBlank(content) || content.length() > 1) {
            return null;
        }

        return content.charAt(0);
    }

    public Character[] getCharacterArray() {
        return getCharacterArray(DEFAULT_ARRAY_SEPARATOR);
    }

    public Character[] getCharacterArray(Pattern pattern) {
        return getArrayOf(pattern, array -> Arrays.stream(array).map(JFileColumn::getCharacter).toArray(Character[]::new));
    }

    public Short getShort() {
        if (StringUtils.isBlank(content)) {
            return null;
        }

        try {
            return Short.valueOf(content);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Short[] getShortArray() {
        return getShortArray(DEFAULT_ARRAY_SEPARATOR);
    }

    public Short[] getShortArray(Pattern pattern) {
        return getArrayOf(pattern, array -> Arrays.stream(array).map(JFileColumn::getShort).toArray(Short[]::new));
    }

    public Integer getInt() {
        if (StringUtils.isBlank(content)) {
            return null;
        }

        try {
            return Integer.valueOf(content);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Integer[] getIntArray() {
        return getIntArray(DEFAULT_ARRAY_SEPARATOR);
    }

    public Integer[] getIntArray(Pattern pattern) {
        return getArrayOf(pattern, array -> Arrays.stream(array).map(JFileColumn::getInt).toArray(Integer[]::new));
    }

    public Long getLong() {
        if (StringUtils.isBlank(content)) {
            return null;
        }

        try {
            return Long.valueOf(content);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Long[] getLongArray() {
        return getLongArray(DEFAULT_ARRAY_SEPARATOR);
    }

    public Long[] getLongArray(Pattern pattern) {
        return getArrayOf(pattern, array -> Arrays.stream(array).map(JFileColumn::getLong).toArray(Long[]::new));
    }

    public Float getFloat() {
        if (StringUtils.isBlank(content)) {
            return null;
        }

        try {
            return Float.valueOf(content);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Float[] getFloatArray() {
        return getFloatArray(DEFAULT_ARRAY_SEPARATOR);
    }

    public Float[] getFloatArray(Pattern pattern) {
        return getArrayOf(pattern, array -> Arrays.stream(array).map(JFileColumn::getFloat).toArray(Float[]::new));
    }

    public Double getDouble() {
        if (StringUtils.isBlank(content)) {
            return null;
        }

        try {
            return Double.valueOf(content);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Double[] getDoubleArray() {
        return getDoubleArray(DEFAULT_ARRAY_SEPARATOR);
    }

    public Double[] getDoubleArray(Pattern pattern) {
        return getArrayOf(pattern, array -> Arrays.stream(array).map(JFileColumn::getDouble).toArray(Double[]::new));
    }

    public Boolean getBoolean() {
        if (StringUtils.isBlank(content)) {
            return null;
        }

        String booleanString = content;

        if (booleanString.equals("0")) {
            booleanString = "false";
        } else if (booleanString.equals("1")) {
            booleanString = "true";
        }

        return BooleanUtils.toBooleanObject(booleanString);
    }

    public Boolean[] getBooleanArray() {
        return getBooleanArray(DEFAULT_ARRAY_SEPARATOR);
    }

    public Boolean[] getBooleanArray(Pattern pattern) {
        return getArrayOf(pattern, array -> Arrays.stream(array).map(JFileColumn::getBoolean).toArray(Boolean[]::new));
    }

    public BigInteger getBigInteger() {
        if (StringUtils.isBlank(content)) {
            return null;
        }

        try {
            return new BigInteger(content);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public BigInteger[] getBigIntegerArray() {
        return getBigIntegerArray(DEFAULT_ARRAY_SEPARATOR);
    }

    public BigInteger[] getBigIntegerArray(Pattern pattern) {
        return getArrayOf(pattern, array -> Arrays.stream(array).map(JFileColumn::getBigInteger).toArray(BigInteger[]::new));
    }

    public BigDecimal getBigDecimal() {
        return getBigDecimal(context.getBigDecimalFormatter());
    }

    public BigDecimal getBigDecimal(DecimalFormat bigDecimalFormatter) {
        if (StringUtils.isBlank(content)) {
            return null;
        }

        try {
            return (BigDecimal) bigDecimalFormatter.parse(content);
        } catch (ParseException e) {
            return null;
        }
    }

    public BigDecimal[] getBigDecimalArray() {
        return getBigDecimalArray(DEFAULT_ARRAY_SEPARATOR);
    }

    public BigDecimal[] getBigDecimalArray(Pattern pattern) {
        return getBigDecimalArray(pattern, context.getBigDecimalFormatter());
    }

    public BigDecimal[] getBigDecimalArray(Pattern pattern, DecimalFormat bigDecimalFormatter) {
        return getArrayOf(pattern, array -> Arrays.stream(array).map(o -> o.getBigDecimal(bigDecimalFormatter)).toArray(BigDecimal[]::new));
    }

    public Date getDate() {
        return getDate(context.getDateFormat());
    }

    public Date getDate(DateFormat pattern) {
        if (StringUtils.isBlank(content)) {
            return null;
        }

        try {
            return pattern.parse(content);
        } catch (ParseException e) {
            return null;
        }
    }

    public Date[] getDateArray() {
        return getDateArray(DEFAULT_ARRAY_SEPARATOR);
    }

    public Date[] getDateArray(Pattern pattern) {
        return getDateArray(pattern, context.getDateFormat());
    }

    public Date[] getDateArray(Pattern pattern, DateFormat dateFormat) {
        return getArrayOf(pattern, array -> Arrays.stream(array).map(o -> o.getDate(dateFormat)).toArray(Date[]::new));
    }

    public LocalDate getLocalDate() {
        return getLocalDate(context.getLocalDateFormatter());
    }

    public LocalDate getLocalDate(DateTimeFormatter dateTimeFormatter) {
        if (StringUtils.isBlank(content)) {
            return null;
        }

        try {
            return LocalDate.parse(content, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public LocalDate[] getLocalDateArray() {
        return getLocalDateArray(DEFAULT_ARRAY_SEPARATOR);
    }

    public LocalDate[] getLocalDateArray(Pattern pattern) {
        return getLocalDateArray(pattern, context.getLocalDateFormatter());
    }

    public LocalDate[] getLocalDateArray(Pattern pattern, DateTimeFormatter dateTimeFormatter) {
        return getArrayOf(pattern, array -> Arrays.stream(array).map(o -> o.getLocalDate(dateTimeFormatter)).toArray(LocalDate[]::new));
    }

    public LocalDateTime getLocalDateTime() {
        return getLocalDateTime(context.getLocalDateTimeFormatter());
    }

    public LocalDateTime getLocalDateTime(DateTimeFormatter dateTimeFormatter) {
        if (StringUtils.isBlank(content)) {
            return null;
        }

        try {
            return LocalDateTime.parse(content, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public LocalDateTime[] getLocalDateTimeArray() {
        return getLocalDateTimeArray(DEFAULT_ARRAY_SEPARATOR);
    }

    public LocalDateTime[] getLocalDateTimeArray(Pattern pattern) {
        return getLocalDateTimeArray(pattern, context.getLocalDateTimeFormatter());
    }

    public LocalDateTime[] getLocalDateTimeArray(Pattern pattern, DateTimeFormatter dateTimeFormatter) {
        return getArrayOf(pattern, array -> Arrays.stream(array).map(o -> o.getLocalDateTime(dateTimeFormatter)).toArray(LocalDateTime[]::new));
    }

    private <T> T[] getArrayOf(Pattern pattern, Function<JFileColumn[], T[]> function) {
        String[] split = pattern.split(content);
        return function.apply(Arrays.stream(split).map(s -> new JFileColumn(context, position, s)).toArray(JFileColumn[]::new));
    }

    public JFileReaderContext getContext() {
        return context;
    }

    @Override
    public int compareTo(JFileColumn o) {
        return Integer.compare(position, o.position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JFileColumn that = (JFileColumn) o;
        return position == that.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
