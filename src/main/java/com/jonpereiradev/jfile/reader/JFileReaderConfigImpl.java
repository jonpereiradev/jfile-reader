package com.jonpereiradev.jfile.reader;

import com.jonpereiradev.jfile.reader.converter.LineValueConverter;
import com.jonpereiradev.jfile.reader.converter.ReflectionLineValueConverter;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

final class JFileReaderConfigImpl implements JFileReaderConfig {

    private final Pattern pattern;
    private final Charset charset;
    private final LineValueConverter lineValueConverter;

    private DateFormat dateFormat;
    private DateTimeFormatter localDateFormatter;
    private DateTimeFormatter localDateTimeFormatter;
    private DecimalFormat bigDecimalFormatter;

    JFileReaderConfigImpl(Pattern pattern, Charset charset) {
        this.pattern = pattern;
        this.charset = charset;
        this.lineValueConverter = new ReflectionLineValueConverter(this);
        this.dateFormat = DateFormat.getInstance();
        this.localDateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        this.localDateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        this.bigDecimalFormatter = new DecimalFormat();
        this.bigDecimalFormatter.setParseBigDecimal(true);
    }

    @Override
    public JFileReaderConfig dateFormatter(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
        return this;
    }

    @Override
    public DateFormat getDateFormat() {
        return dateFormat;
    }

    @Override
    public JFileReaderConfig localDateFormatter(DateTimeFormatter localDateFormatter) {
        this.localDateFormatter = localDateFormatter;
        return this;
    }

    @Override
    public DateTimeFormatter getLocalDateFormatter() {
        return localDateFormatter;
    }

    @Override
    public JFileReaderConfig localDateTimeFormatter(DateTimeFormatter localDateTimeFormatter) {
        this.localDateTimeFormatter = localDateTimeFormatter;
        return this;
    }

    @Override
    public DateTimeFormatter getLocalDateTimeFormatter() {
        return localDateTimeFormatter;
    }

    @Override
    public JFileReaderConfig bigDecimalFormat(DecimalFormat decimalFormat) {
        this.bigDecimalFormatter = decimalFormat;
        return this;
    }

    @Override
    public DecimalFormat getBigDecimalFormatter() {
        return bigDecimalFormatter;
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
    public LineValueConverter getLineValueConverter() {
        return lineValueConverter;
    }

}
