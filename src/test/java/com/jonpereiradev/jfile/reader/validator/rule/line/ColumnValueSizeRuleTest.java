package com.jonpereiradev.jfile.reader.validator.rule.line;

import com.jonpereiradev.jfile.reader.JFileReader;
import com.jonpereiradev.jfile.reader.JFileReaderConfig;
import com.jonpereiradev.jfile.reader.JFileReaderFactory;
import com.jonpereiradev.jfile.reader.infrastructure.AbstractFileReaderTest;
import com.jonpereiradev.jfile.reader.validator.JFileValidator;
import com.jonpereiradev.jfile.reader.validator.JFileValidatorConfig;
import com.jonpereiradev.jfile.reader.validator.JFileValidatorFactory;
import com.jonpereiradev.jfile.reader.validator.rule.RuleViolation;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class ColumnValueSizeRuleTest extends AbstractFileReaderTest {

    @Test
    public void mustViolateRule() throws IOException {
        Path path = createFileWithContent("1|2|3|4");
        JFileReaderConfig readerConfig = JFileReaderFactory.newUtf8ReaderConfig("\\|");
        JFileReader reader = JFileReaderFactory.newJFileReader(path, readerConfig);

        JFileValidatorConfig validatorConfig = JFileValidatorFactory.newValidatorConfig();
        JFileValidator validator = JFileValidatorFactory.newJFileValidator(validatorConfig);

        validatorConfig.files().lines().columnsSize(5);

        List<RuleViolation> violations = validator.validate(reader).getViolations();
        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void mustSuccessRule() throws IOException {
        Path path = createFileWithContent("1|2|3|4|5");
        JFileReaderConfig readerConfig = JFileReaderFactory.newUtf8ReaderConfig("\\|");
        JFileReader reader = JFileReaderFactory.newJFileReader(path, readerConfig);

        JFileValidatorConfig validatorConfig = JFileValidatorFactory.newValidatorConfig();
        JFileValidator validator = JFileValidatorFactory.newJFileValidator(validatorConfig);

        validatorConfig.files().lines().columnsSize(5);

        List<RuleViolation> violations = validator.validate(reader).getViolations();
        Assert.assertTrue(violations.isEmpty());
    }

}
