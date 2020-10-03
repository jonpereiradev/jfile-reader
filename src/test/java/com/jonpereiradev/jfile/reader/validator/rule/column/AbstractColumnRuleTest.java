package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.JFileReader;
import com.jonpereiradev.jfile.reader.JFileReaderConfig;
import com.jonpereiradev.jfile.reader.JFileReaderFactory;
import com.jonpereiradev.jfile.reader.infrastructure.AbstractFileReaderTest;
import com.jonpereiradev.jfile.reader.validator.JFileValidator;
import com.jonpereiradev.jfile.reader.validator.JFileValidatorConfig;
import com.jonpereiradev.jfile.reader.validator.JFileValidatorFactory;
import com.jonpereiradev.jfile.reader.validator.rule.RuleViolation;
import com.jonpereiradev.jfile.reader.validator.rule.configurator.LineRuleConfigurator;
import org.junit.Before;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

abstract class AbstractColumnRuleTest extends AbstractFileReaderTest {

    private LineRuleConfigurator ruleConfigurator;
    private JFileReaderConfig readerConfig;
    private JFileValidatorConfig validatorConfig;

    @Before
    public void beforeEach() {
        readerConfig = JFileReaderFactory.newUtf8ReaderConfig("\\|");
        validatorConfig = JFileValidatorFactory.newValidatorConfig();
        ruleConfigurator = validatorConfig.files().lines();
    }

    protected List<RuleViolation> validate(Path path) throws IOException {
        JFileReader reader = JFileReaderFactory.newJFileReader(path, readerConfig);
        JFileValidator validator = JFileValidatorFactory.newJFileValidator(validatorConfig);
        return validator.validate(reader).getViolations();
    }

    public LineRuleConfigurator getRuleConfigurator() {
        return ruleConfigurator;
    }

    public JFileReaderConfig getReaderConfig() {
        return readerConfig;
    }

    public JFileValidatorConfig getValidatorConfig() {
        return validatorConfig;
    }

}
