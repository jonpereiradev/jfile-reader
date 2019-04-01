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

    ReaderConfiguration withDateFormatter(DateFormat dateFormatter);

    ReaderConfiguration withLocalDateFormatter(DateTimeFormatter localDateFormatter);

    ReaderConfiguration withLocalDateTimeFormatter(DateTimeFormatter localDateTimeFormatter);

    ReaderConfiguration withBigDecimalFormat(DecimalFormat decimalFormat);

    ReaderConfiguration withRuleConfiguration(RuleConfiguration ruleConfiguration);

    ReaderConfiguration withStreamReader(Function<InputStream, StreamReader> streamReader);

    Pattern getPattern();

    Charset getCharset();

    DateFormat getDateFormat();

    DateTimeFormatter getLocalDateFormatter();

    DateTimeFormatter getLocalDateTimeFormatter();

    DecimalFormat getBigDecimalFormatter();

    RuleConfiguration getRuleConfiguration();

    Function<? super InputStream, ? extends StreamReader> getStreamReader();

    FileLineParser getFileLineParser();
}
