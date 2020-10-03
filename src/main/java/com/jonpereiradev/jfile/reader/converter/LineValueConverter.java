package com.jonpereiradev.jfile.reader.converter;

import com.jonpereiradev.jfile.reader.file.LineValue;

public interface LineValueConverter {

    <T> T convert(LineValue lineValue, Class<T> classType);

}
