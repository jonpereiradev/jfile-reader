package com.jonpereiradev.jfile.reader.rule.line;

import com.jonpereiradev.jfile.reader.AbstractFileReaderTest;
import com.jonpereiradev.jfile.reader.JFileReader;
import com.jonpereiradev.jfile.reader.JFileReaderFactory;
import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;
import com.jonpereiradev.jfile.reader.rule.RuleConfigurator;
import com.jonpereiradev.jfile.reader.rule.RuleViolation;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class LineColumnSizeRuleConfigurationTest extends AbstractFileReaderTest {

    @Test
    public void mustViolateRule() throws IOException {
        Path path = createFileWithContent("1|2|3|4");
        ReaderConfiguration readerConfiguration = ReaderConfiguration.utf8Reader("\\|");
        RuleConfigurator.defaultConfigurator(readerConfiguration).files().lines().columns(5).build();

        JFileReader reader = JFileReaderFactory.newInstance(path, readerConfiguration);
        List<RuleViolation> violations = reader.validate().getViolations();

        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void mustSuccessRule() throws IOException {
        Path path = createFileWithContent("1|2|3|4|5");
        ReaderConfiguration readerConfiguration = ReaderConfiguration.utf8Reader("\\|");
        RuleConfigurator.defaultConfigurator(readerConfiguration).files().lines().columns(5).build();

        JFileReader reader = JFileReaderFactory.newInstance(path, readerConfiguration);
        List<RuleViolation> violations = reader.validate().getViolations();

        Assert.assertTrue(violations.isEmpty());
    }

}
