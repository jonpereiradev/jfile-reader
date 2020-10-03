package com.jonpereiradev.jfile.reader.converter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DecimalFormatter {

    String value();

    char groupingSeparator() default '.';

    char decimalSeparator() default ',';

}
