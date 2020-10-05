/*
 * MIT License
 *
 * Copyright (c) 2020 Jonathan de Almeida Pereira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
