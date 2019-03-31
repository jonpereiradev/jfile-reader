package com.jonpereiradev.jfile.reader.rules.column;

import java.util.regex.Pattern;

public class EmailRule extends RegexRule {

    public EmailRule(int position) {
        super(position, Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"));
    }

}
