package com.jonpereiradev.jfile.reader;

import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;
import com.jonpereiradev.jfile.reader.parser.FileLineParser;
import com.jonpereiradev.jfile.reader.rules.RuleConfiguration;
import com.jonpereiradev.jfile.reader.stream.StreamReader;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class JFileReaderContext {

    private final InputStream inputStream;
    private final ReaderConfiguration readerConfiguration;

    JFileReaderContext(InputStream inputStream, ReaderConfiguration readerConfiguration) {
        this.inputStream = inputStream;
        this.readerConfiguration = readerConfiguration;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public ReaderConfiguration getReaderConfiguration() {
        return readerConfiguration;
    }

    public Pattern getPattern() {
        return readerConfiguration.getPattern();
    }

    public Charset getCharset() {
        return readerConfiguration.getCharset();
    }

    public DateFormat getDateFormat() {
        return readerConfiguration.getDateFormat();
    }

    public DateTimeFormatter getLocalDateFormatter() {
        return readerConfiguration.getLocalDateFormatter();
    }

    public DateTimeFormatter getLocalDateTimeFormatter() {
        return readerConfiguration.getLocalDateTimeFormatter();
    }

    public DecimalFormat getBigDecimalFormatter() {
        return readerConfiguration.getBigDecimalFormatter();
    }

    public RuleConfiguration getRuleConfiguration() {
        return readerConfiguration.getRuleConfiguration();
    }

    public StreamReader getStreamReader() {
        return readerConfiguration.getStreamReader().apply(inputStream);
    }

    public FileLineParser getFileLineParser() {
        return readerConfiguration.getFileLineParser();
    }
}
