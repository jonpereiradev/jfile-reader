package com.jonpereiradev.jfile.reader.stream;

import java.io.Closeable;
import java.io.InputStream;
import java.nio.charset.Charset;

public abstract class StreamReader implements Iterable<String>, Closeable {

    private final InputStream inputStream;
    private final Charset charset;

    protected StreamReader(InputStream inputStream, Charset charset) {
        this.inputStream = inputStream;
        this.charset = charset;
    }

    public static StreamReader defaultStreamReader(InputStream inputStream, Charset charset) {
        return new DefaultStreamReader(inputStream, charset);
    }

}
