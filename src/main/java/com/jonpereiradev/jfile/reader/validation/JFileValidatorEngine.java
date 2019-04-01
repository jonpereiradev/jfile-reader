package com.jonpereiradev.jfile.reader.validation;

import com.jonpereiradev.jfile.reader.JFileReaderContext;
import com.jonpereiradev.jfile.reader.file.JFileLine;
import com.jonpereiradev.jfile.reader.rule.RuleViolation;

import java.util.List;

public interface JFileValidatorEngine {

    static JFileValidatorEngine defaultEngine(JFileReaderContext context) {
        return new JFileValidatorEngineImpl(context);
    }

    List<RuleViolation> validate(JFileLine line);

}
