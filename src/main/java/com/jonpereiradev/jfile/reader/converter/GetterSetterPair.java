package com.jonpereiradev.jfile.reader.converter;


import java.lang.reflect.Field;
import java.lang.reflect.Method;


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
