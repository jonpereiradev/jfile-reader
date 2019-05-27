package com.jonpereiradev.jfile.reader.stream;

import java.io.Closeable;
import java.io.InputStream;
import java.nio.charset.Charset;

public interface StreamReader extends Iterable<String>, Closeable {

    static StreamReader defaultStreamReader(InputStream inputStream, Charset charset) {
        return new DefaultStreamReader(inputStream, charset);
    }

    /**
     * @return the input stream with the file content.
     */
    InputStream getInputStream();

    /**
     * @return the charset of the file content.
     */
    Charset getCharset();

}
