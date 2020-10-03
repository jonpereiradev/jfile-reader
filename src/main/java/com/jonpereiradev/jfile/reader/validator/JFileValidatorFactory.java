package com.jonpereiradev.jfile.reader.validator;

import com.jonpereiradev.jfile.reader.JFilePatternConfig;

/**
 * Factory to create the {@link JFileValidator} implementation.
 */
public final class JFileValidatorFactory {

    private JFileValidatorFactory() {
        throw new UnsupportedOperationException();
    }

    /**
     * Create a File Validator with the desired parameters.
     *
     * @param validatorConfig the config that tells how to validate the file.
     *
     * @return an instance of JFileReader for the file and config specified.
     */
    public static JFileValidator newJFileValidator(JFileValidatorConfig validatorConfig) {
        return new JFileValidatorEngine(validatorConfig);
    }

    /**
     * Create a File Validator Config to configure the validator engine.
     *
     * @return a new instance of the validator config.
     */
    public static JFileValidatorConfig newValidatorConfig() {
        return new JFileValidatorConfigImpl();
    }

    /**
     * Create a File Validator Config from a Pattern Config to configure the validator engine.
     *
     * @param filePatternConfig the config that will be copy to the new validator config.
     *
     * @return a new instance of the validator config.
     */
    public static JFileValidatorConfig newValidatorConfig(JFilePatternConfig filePatternConfig) {
        return new JFileValidatorConfigImpl(filePatternConfig);
    }

}
