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

public class JFileColumn implements Comparable<JFileColumn> {

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

    public String getText() {
        return content;
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

    public Date getDate() throws ParseException {
        return getDate(context.getDateFormat());
    }

    public Date getDate(DateFormat pattern) throws ParseException {
        if (StringUtils.isBlank(content)) {
            return null;
        }

        return pattern.parse(content);
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
