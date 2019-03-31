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
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

public class JFileColumn implements Comparable<JFileColumn> {

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

    public Character getCharacter() {
        if (StringUtils.isBlank(content) || content.length() > 1) {
            return null;
        }

        return content.charAt(0);
    }

    public Short getShort() {
        if (StringUtils.isBlank(content)) {
            return null;
        }

        return Short.valueOf(content);
    }

    public Integer getInt() {
        if (StringUtils.isBlank(content)) {
            return null;
        }

        return Integer.valueOf(content);
    }

    public Long getLong() {
        if (StringUtils.isBlank(content)) {
            return null;
        }

        return Long.valueOf(content);
    }

    public Float getFloat() {
        if (StringUtils.isBlank(content)) {
            return null;
        }

        return Float.valueOf(content);
    }

    public Double getDouble() {
        if (StringUtils.isBlank(content)) {
            return null;
        }

        return Double.valueOf(content);
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

    public BigInteger getBigInteger() {
        if (StringUtils.isBlank(content)) {
            return null;
        }

        return new BigInteger(content);
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
            throw new NumberFormatException(e.getMessage());
        }
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

    public LocalDate getLocalDate() {
        return getLocalDate(context.getLocalDateFormatter());
    }

    public LocalDate getLocalDate(DateTimeFormatter dateTimeFormatter) {
        if (StringUtils.isBlank(content)) {
            return null;
        }

        return LocalDate.parse(content, dateTimeFormatter);
    }

    public LocalDateTime getLocalDateTime() {
        return getLocalDateTime(context.getLocalDateTimeFormatter());
    }

    public LocalDateTime getLocalDateTime(DateTimeFormatter dateTimeFormatter) {
        if (StringUtils.isBlank(content)) {
            return null;
        }

        return LocalDateTime.parse(content, dateTimeFormatter);
    }
}
