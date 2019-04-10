package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.rule.RuleViolation;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class DoubleRuleConfigurationTest extends AbstractColumnRuleConfigurationTest {

    @Test
    public void mustViolateTypeRule() throws IOException {
        Path path = createFileWithContent("a");
        getRuleConfigurator().column(1).doubleType();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DoubleTypeRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateNotNullRule() throws IOException {
        Path path = createFileWithContent("a||c");
        getRuleConfigurator().column(2).doubleType().notNull();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMinRule() throws IOException {
        Path path = createFileWithContent("1");
        getRuleConfigurator().column(1).doubleType().min(2);
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MinDoubleRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMaxRule() throws IOException {
        Path path = createFileWithContent("3");
        getRuleConfigurator().column(1).doubleType().max(2);
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MaxDoubleRule.class.getName(), violations.get(0).getRule());
    }

}
