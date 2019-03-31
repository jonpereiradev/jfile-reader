package com.jonpereiradev.jfile.reader.stream;

import java.io.InputStream;
import java.nio.charset.Charset;

public abstract class AbstractStreamReader implements StreamReader {

    private final InputStream inputStream;
    private final Charset charset;

    protected AbstractStreamReader(InputStream inputStream, Charset charset) {
        this.inputStream = inputStream;
        this.charset = charset;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public Charset getCharset() {
        return charset;
    }
}
