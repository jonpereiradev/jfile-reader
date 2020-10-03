package com.jonpereiradev.jfile.reader.validator;

import com.jonpereiradev.jfile.reader.JFilePatternConfig;
import com.jonpereiradev.jfile.reader.validator.rule.RuleRoot;

public interface JFileValidatorConfig extends JFilePatternConfig<JFileValidatorConfig>, JFileRuleConfig {

    /**
     * Configure the max violation size to limit the number of errors that a file
     * can have before stops the validation. The default is to not have a limit.
     *
     * @param maxViolationSize an int number for the max violation size.
     *
     * @return the object with the maxViolationSize configured.
     */
    JFileValidatorConfig maxViolationSize(int maxViolationSize);

    /**
     * @return the max violation size configured.
     */
    int getMaxViolationSize();

    /**
     * @return the root of all rules configured for the validator.
     */
    RuleRoot getRuleRoot();

}
