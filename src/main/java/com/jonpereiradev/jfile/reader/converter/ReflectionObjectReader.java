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
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Consumer;


final class ReflectionObjectReader {

    private static final String PREFIX_GETTER_METHOD = "get";
    private static final String PREFIX_SETTER_METHOD = "set";
    private static final String PREFIX_IS_METHOD = "is";

    private Map<Integer, GetterSetterPair> getterSetterMapping;

    Map<Integer, GetterSetterPair> read(Class<?> clazz) {
        getterSetterMapping = new TreeMap<>();

        for (final Method method : clazz.getMethods()) {
            final String methodName = method.getName();

            String objectName;

            if (isJavaBeanGetterMethod(method)) {
                objectName = methodName.substring(PREFIX_GETTER_METHOD.length());
                Optional<Field> fieldByName = findFieldByName(clazz, objectName);
                fieldByName.ifPresent(field -> putIfAbsent(field, o -> o.setGetter(method)));
            } else if (isJavaBeanSetterMethod(method)) {
                objectName = methodName.substring(PREFIX_SETTER_METHOD.length());
                Optional<Field> fieldByName = findFieldByName(clazz, objectName);
                fieldByName.ifPresent(field -> putIfAbsent(field, o -> o.setSetter(method)));
            } else if (isJavaBeanBooleanGetterMethod(method)) {
                objectName = methodName.substring(PREFIX_IS_METHOD.length());
                Optional<Field> fieldByName = findFieldByName(clazz, objectName);
                fieldByName.ifPresent(field -> putIfAbsent(field, o -> o.setGetter(method)));
            }
        }

        return getterSetterMapping;
    }

    private void putIfAbsent(Field field, Consumer<GetterSetterPair> consumer) {
        if (field.isAnnotationPresent(FileColumn.class)) {
            FileColumn annotation = field.getAnnotation(FileColumn.class);
            GetterSetterPair getterSettingPair = getPairOrCreateIfNotExists(annotation);
            getterSettingPair.setField(field);
            consumer.accept(getterSettingPair);
        }
    }

    private Optional<Field> findFieldByName(Class<?> clazz, String fieldName) {
        return Arrays.stream(clazz.getDeclaredFields())
            .filter(o -> o.getName().equalsIgnoreCase(fieldName))
            .findFirst();
    }

    private GetterSetterPair getPairOrCreateIfNotExists(FileColumn fileColumn) {
        GetterSetterPair getterSettingPair = getterSetterMapping.get(fileColumn.value());

        if (getterSettingPair == null) {
            getterSettingPair = new GetterSetterPair();
            getterSettingPair.setAnnotation(fileColumn);
            getterSetterMapping.put(fileColumn.value(), getterSettingPair);
        }

        return getterSettingPair;
    }

    private boolean isJavaBeanGetterMethod(Method method) {
        return method.getName().startsWith(PREFIX_GETTER_METHOD) && method.getParameters().length == 0;
    }

    private boolean isJavaBeanSetterMethod(Method method) {
        return method.getName().startsWith(PREFIX_SETTER_METHOD) && method.getParameters().length == 1;
    }

    private boolean isJavaBeanBooleanGetterMethod(Method method) {
        return method.getName().startsWith(PREFIX_IS_METHOD) && method.getParameters().length == 0;
    }

}
