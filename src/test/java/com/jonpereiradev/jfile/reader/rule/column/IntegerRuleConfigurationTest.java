package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.rule.RuleViolation;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class IntegerRuleConfigurationTest extends AbstractColumnRuleConfigurationTest {

    @Test
    public void mustViolateTypeRule() throws IOException {
        Path path = createFileWithContent("a");
        getRuleConfigurator().column(1).integerType();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(IntegerTypeRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateNotNullRule() throws IOException {
        Path path = createFileWithContent("a||c");
        getRuleConfigurator().column(2).integerType().notNull();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMinIntegerRule() throws IOException {
        Path path = createFileWithContent("1");
        getRuleConfigurator().column(1).integerType().min(2);
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MinIntegerRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMaxIntegerRule() throws IOException {
        Path path = createFileWithContent("3");
        getRuleConfigurator().column(1).integerType().max(2);
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MaxIntegerRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateDomainIntegerRule() throws IOException {
        Path path = createFileWithContent("5");
        getRuleConfigurator().column(1).integerType().domain(1, 2);
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DomainIntegerRule.class.getName(), violations.get(0).getRule());
    }

}
