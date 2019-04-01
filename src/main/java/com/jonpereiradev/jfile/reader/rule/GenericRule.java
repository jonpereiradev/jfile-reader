package com.jonpereiradev.jfile.reader.rule;

public interface GenericRule<T> {

    boolean isValid(T object);

    boolean canValidate(T object);

}
