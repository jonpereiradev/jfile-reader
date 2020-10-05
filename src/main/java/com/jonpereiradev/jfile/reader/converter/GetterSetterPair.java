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


import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * @author jonpereiradev
 * @since 0.1.0
 */
final class GetterSetterPair {

    private Field field;
    private Method getter;
    private Method setter;
    private FileColumn annotation;

    Field getField() {
        return field;
    }

    void setField(Field field) {
        this.field = field;
    }

    Method getGetter() {
        return getter;
    }

    void setGetter(Method getter) {
        this.getter = getter;
    }

    Method getSetter() {
        return setter;
    }

    void setSetter(Method setter) {
        this.setter = setter;
    }

    FileColumn getAnnotation() {
        return annotation;
    }

    void setAnnotation(FileColumn annotation) {
        this.annotation = annotation;
    }

    boolean hasGetterAndSetter() {
        return getter != null && setter != null;
    }

}
