package com.jonpereiradev.jfile.reader.rules;

public interface GenericRule<T> {

    boolean isValid(T object);

    boolean canValidate(T object);

}
