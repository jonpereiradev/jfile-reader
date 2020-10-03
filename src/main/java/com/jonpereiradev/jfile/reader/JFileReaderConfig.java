package com.jonpereiradev.jfile.reader;

import com.jonpereiradev.jfile.reader.converter.LineValueConverter;

import java.nio.charset.Charset;
import java.util.regex.Pattern;

public interface JFileReaderConfig extends JFilePatternConfig<JFileReaderConfig> {

    /**
     * @return the pattern to split the line into columns.
     */
    Pattern getPattern();

    /**
     * @return the charset of the string lines.
     */
    Charset getCharset();

    /**
     * @return the converter responsible to transform a line into an object.
     */
    LineValueConverter getLineValueConverter();



}
