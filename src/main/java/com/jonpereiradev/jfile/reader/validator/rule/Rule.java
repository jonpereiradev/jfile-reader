package com.jonpereiradev.jfile.reader.validator.rule;

public interface Rule<T> {

    boolean isValid(T object);

    boolean canValidate(T object);

}
