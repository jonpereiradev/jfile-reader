package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.JFileReader;
import com.jonpereiradev.jfile.reader.JFileReaderFactory;
import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;
import com.jonpereiradev.jfile.reader.infrastructure.AbstractFileReaderTest;
import com.jonpereiradev.jfile.reader.rule.RuleConfigurator;
import com.jonpereiradev.jfile.reader.rule.RuleViolation;
import com.jonpereiradev.jfile.reader.rule.configurator.LineRuleConfigurator;
import org.junit.Before;

import java.nio.file.Path;
import java.util.List;

abstract class AbstractColumnRuleConfigurationTest extends AbstractFileReaderTest {

    private LineRuleConfigurator ruleConfigurator;
    private ReaderConfiguration readerConfiguration;

    @Before
    public void beforeEach() {
        readerConfiguration = ReaderConfiguration.utf8Reader("\\|");
        ruleConfigurator = RuleConfigurator.defaultConfigurator(readerConfiguration).files().lines();
    }

    protected List<RuleViolation> validate(Path path) {
        JFileReader reader = JFileReaderFactory.newInstance(path, readerConfiguration);
        return reader.validate().getViolations();
    }

    public LineRuleConfigurator getRuleConfigurator() {
        return ruleConfigurator;
    }

    public ReaderConfiguration getReaderConfiguration() {
        return readerConfiguration;
    }
}
