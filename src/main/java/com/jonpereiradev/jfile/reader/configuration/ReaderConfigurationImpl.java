package com.jonpereiradev.jfile.reader.configuration;

import com.jonpereiradev.jfile.reader.parser.FileLineParser;
import com.jonpereiradev.jfile.reader.rule.RuleConfiguration;
import com.jonpereiradev.jfile.reader.stream.StreamReader;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;
import java.util.regex.Pattern;

final class ReaderConfigurationImpl implements ReaderConfiguration {

    private final Pattern pattern;
    private final Charset charset;
    private final FileLineParser fileLineParser;

    private DateFormat dateFormat;
    private DateTimeFormatter localDateFormatter;
    private DateTimeFormatter localDateTimeFormatter;
    private DecimalFormat bigDecimalFormatter;
    private RuleConfiguration ruleConfiguration;
    private Function<InputStream, StreamReader> streamReader;
    private int maxViolationSize = -1;

    ReaderConfigurationImpl(Pattern pattern, Charset charset) {
        this.pattern = pattern;
        this.charset = charset;
        this.fileLineParser = FileLineParser.defaultParser(this);
        this.dateFormat = DateFormat.getInstance();
        this.localDateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        this.localDateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        this.bigDecimalFormatter = new DecimalFormat();
        this.bigDecimalFormatter.setParseBigDecimal(true);
        this.streamReader = inputStream -> StreamReader.defaultStreamReader(inputStream, charset);
    }

    @Override
    public ReaderConfiguration withDateFormatter(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
        return this;
    }

    @Override
    public ReaderConfiguration withLocalDateFormatter(DateTimeFormatter localDateFormatter) {
        this.localDateFormatter = localDateFormatter;
        return this;
    }

    @Override
    public ReaderConfiguration withLocalDateTimeFormatter(DateTimeFormatter localDateTimeFormatter) {
        this.localDateTimeFormatter = localDateTimeFormatter;
        return this;
    }

    @Override
    public ReaderConfiguration withBigDecimalFormat(DecimalFormat decimalFormat) {
        this.bigDecimalFormatter = decimalFormat;
        return this;
    }

    @Override
    public ReaderConfiguration withRuleConfiguration(RuleConfiguration ruleConfiguration) {
        this.ruleConfiguration = ruleConfiguration;
        return this;
    }

    @Override
    public ReaderConfiguration withStreamReader(Function<InputStream, StreamReader> streamReader) {
        this.streamReader = streamReader;
        return this;
    }

    @Override
    public ReaderConfiguration withMaxViolationSize(int maxViolationSize) {
        this.maxViolationSize = maxViolationSize;
        return this;
    }

    @Override
    public Pattern getPattern() {
        return pattern;
    }

    @Override
    public Charset getCharset() {
        return charset;
    }

    @Override
    public DateFormat getDateFormat() {
        return dateFormat;
    }

    @Override
    public DateTimeFormatter getLocalDateFormatter() {
        return localDateFormatter;
    }

    @Override
    public DateTimeFormatter getLocalDateTimeFormatter() {
        return localDateTimeFormatter;
    }

    @Override
    public DecimalFormat getBigDecimalFormatter() {
        return bigDecimalFormatter;
    }

    @Override
    public RuleConfiguration getRuleConfiguration() {
        return ruleConfiguration;
    }

    @Override
    public Function<? super InputStream, ? extends StreamReader> getStreamReader() {
        return streamReader;
    }

    @Override
    public FileLineParser getFileLineParser() {
        return fileLineParser;
    }

    @Override
    public int getMaxViolationSize() {
        return maxViolationSize;
    }
}
