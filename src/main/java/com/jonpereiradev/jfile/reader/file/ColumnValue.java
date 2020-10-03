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

    int getPosition();

    <T> T getContent(Class<T> clazz);

    String getText();

    String[] getTextArray();

    String[] getTextArray(Pattern pattern);

    Character getCharacter();

    Character[] getCharacterArray();

    Character[] getCharacterArray(Pattern pattern);

    Short getShort();

    Short[] getShortArray();

    Short[] getShortArray(Pattern pattern);

    Integer getInt();

    Integer[] getIntArray();

    Integer[] getIntArray(Pattern pattern);

    Long getLong();

    Long[] getLongArray();

    Long[] getLongArray(Pattern pattern);

    Float getFloat();

    Float[] getFloatArray();

    Float[] getFloatArray(Pattern pattern);

    Double getDouble();

    Double[] getDoubleArray();

    Double[] getDoubleArray(Pattern pattern);

    Boolean getBoolean();

    Boolean[] getBooleanArray();

    Boolean[] getBooleanArray(Pattern pattern);

    BigInteger getBigInteger();

    BigInteger[] getBigIntegerArray();

    BigInteger[] getBigIntegerArray(Pattern pattern);

    BigDecimal getBigDecimal();

    BigDecimal getBigDecimal(DecimalFormat bigDecimalFormatter);

    BigDecimal[] getBigDecimalArray();

    BigDecimal[] getBigDecimalArray(Pattern pattern);

    BigDecimal[] getBigDecimalArray(Pattern pattern, DecimalFormat bigDecimalFormatter);

    Date getDate();

    Date getDate(DateFormat pattern);

    Date[] getDateArray();

    Date[] getDateArray(Pattern pattern);

    Date[] getDateArray(Pattern pattern, DateFormat dateFormat);

    LocalDate getLocalDate();

    LocalDate getLocalDate(DateTimeFormatter dateTimeFormatter);

    LocalDate[] getLocalDateArray();

    LocalDate[] getLocalDateArray(Pattern pattern);

    LocalDate[] getLocalDateArray(Pattern pattern, DateTimeFormatter dateTimeFormatter);

    LocalDateTime getLocalDateTime();

    LocalDateTime getLocalDateTime(DateTimeFormatter dateTimeFormatter);

    LocalDateTime[] getLocalDateTimeArray();

    LocalDateTime[] getLocalDateTimeArray(Pattern pattern);

    LocalDateTime[] getLocalDateTimeArray(Pattern pattern, DateTimeFormatter dateTimeFormatter);

    JFilePatternConfig getPatternConfig();

}
