package com.jonpereiradev.jfile.reader.parser;


import java.lang.reflect.Field;
import java.lang.reflect.Method;


public final class GetterSetterPair {

    private Field field;
    private Method getter;
    private Method setter;
    private FileColumn annotation;

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Method getGetter() {
        return getter;
    }

    public void setGetter(Method getter) {
        this.getter = getter;
    }

    public Method getSetter() {
        return setter;
    }

    public void setSetter(Method setter) {
        this.setter = setter;
    }

    public FileColumn getAnnotation() {
        return annotation;
    }

    public void setAnnotation(FileColumn annotation) {
        this.annotation = annotation;
    }

    public boolean hasGetterAndSetter() {
        return getter != null && setter != null;
    }

}
