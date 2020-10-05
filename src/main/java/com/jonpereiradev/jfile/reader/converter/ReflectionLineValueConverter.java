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
package com.jonpereiradev.jfile.reader.converter;


import com.jonpereiradev.jfile.reader.JFileReaderConfig;
import com.jonpereiradev.jfile.reader.file.LineValue;

import java.util.HashMap;
import java.util.Map;


public final class ReflectionLineValueConverter implements LineValueConverter {

    private static final Map<Class<?>, Map<Integer, GetterSetterPair>> CACHE_CLASS_REFLECTIONS = new HashMap<>();

    private final JFileReaderConfig readerConfig;

    public ReflectionLineValueConverter(JFileReaderConfig readerConfig) {
        this.readerConfig = readerConfig;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T convert(LineValue lineValue, Class<T> classType) {
        if (classType.isAssignableFrom(String.class)) {
            return (T) lineValue.getContent();
        }

        if (classType.equals(LineValue.class)) {
            return (T) lineValue;
        }

        return convertLineValueToObject(lineValue, classType);
    }

    private <T> T convertLineValueToObject(LineValue lineValue, Class<T> classType) {
        T object = newInstance(classType);
        Map<Integer, GetterSetterPair> pair = getGetterSetterPair(classType);
        ReflectionObjectWriter reflectionObjectWriter = new ReflectionObjectWriter(readerConfig);

        lineValue.getColumnValues().forEach(o -> {
            if (pair.containsKey(o.getColumnNumber())) {
                reflectionObjectWriter.write(object, o, pair.get(o.getColumnNumber()));
            }
        });

        return object;
    }

    private <T> Map<Integer, GetterSetterPair> getGetterSetterPair(Class<T> clazz) {
        if (!CACHE_CLASS_REFLECTIONS.containsKey(clazz)) {
            ReflectionObjectReader reflectionObjectReader = new ReflectionObjectReader();
            CACHE_CLASS_REFLECTIONS.put(clazz, reflectionObjectReader.read(clazz));
        }

        return CACHE_CLASS_REFLECTIONS.get(clazz);
    }

    private <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
