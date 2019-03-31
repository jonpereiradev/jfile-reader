package com.jonpereiradev.jfile.reader.stream;

import java.io.Closeable;
import java.io.InputStream;
import java.nio.charset.Charset;

public interface StreamReader extends Iterable<String>, Closeable {

    static StreamReader defaultStreamReader(InputStream inputStream, Charset charset) {
        return new DefaultStreamReader(inputStream, charset);
    }

    InputStream getInputStream();

    Charset getCharset();

}
