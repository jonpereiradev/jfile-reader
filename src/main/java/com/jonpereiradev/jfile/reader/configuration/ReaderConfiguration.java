package com.jonpereiradev.jfile.reader.configuration;

import com.jonpereiradev.jfile.reader.parser.FileLineParser;
import com.jonpereiradev.jfile.reader.rule.RuleConfiguration;
import com.jonpereiradev.jfile.reader.stream.StreamReader;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;
import java.util.regex.Pattern;

public interface ReaderConfiguration {

    static ReaderConfiguration utf8Reader(String regex) {
        return new ReaderConfigurationImpl(Pattern.compile(regex), StandardCharsets.UTF_8);
    }

    static ReaderConfiguration reader(String regex, Charset charset) {
        return new ReaderConfigurationImpl(Pattern.compile(regex), charset);
    }

    /**
     * Configure the default {@link java.util.Date} string formatter.
     */
    ReaderConfiguration withDateFormatter(DateFormat dateFormatter);

    /**
     * Configure the default {@link java.time.LocalDate} string formatter.
     */
    ReaderConfiguration withLocalDateFormatter(DateTimeFormatter localDateFormatter);

    /**
     * Configure the default {@link java.time.LocalDateTime} string formatter.
     */
    ReaderConfiguration withLocalDateTimeFormatter(DateTimeFormatter localDateTimeFormatter);

    /**
     * Configure the default {@link java.math.BigDecimal} string formatter.
     */
    ReaderConfiguration withBigDecimalFormat(DecimalFormat decimalFormat);

    /**
     * Configure the rules of validation.
     */
    ReaderConfiguration withRuleConfiguration(RuleConfiguration ruleConfiguration);

    /**
     * Configure the {@link StreamReader} to parse the file.
     */
    ReaderConfiguration withStreamReader(Function<InputStream, StreamReader> streamReader);

    /**
     * Configure the max violation size for lines.
     */
    ReaderConfiguration withMaxViolationSize(int maxViolationSize);

    /**
     * @return the pattern to split the line in columns.
     */
    Pattern getPattern();

    /**
     * @return the charset to read the bytes as string.
     */
    Charset getCharset();

    /**
     * @return the date formatter for {@link java.util.Date}
     */
    DateFormat getDateFormat();

    /**
     * @return the local date formatter for {@link java.time.LocalDate}
     */
    DateTimeFormatter getLocalDateFormatter();

    /**
     * @return the local date time formatter for {@link java.time.LocalDateTime}
     */
    DateTimeFormatter getLocalDateTimeFormatter();

    /**
     * @return the big decimal formatter for {@link java.math.BigDecimal}
     */
    DecimalFormat getBigDecimalFormatter();

    /**
     * @return the rules configuration for this reader.
     */
    RuleConfiguration getRuleConfiguration();

    /**
     * @return the responsible stream reader to read the file content.
     */
    Function<? super InputStream, ? extends StreamReader> getStreamReader();

    /**
     * @return the parser responsible to convert a line into an object.
     */
    FileLineParser getFileLineParser();

    /**
     * @return the max lines violation size.
     */
    int getMaxViolationSize();
}
