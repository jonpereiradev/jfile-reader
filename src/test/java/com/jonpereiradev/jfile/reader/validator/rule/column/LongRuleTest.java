package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.validator.rule.RuleViolation;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class LongRuleTest extends AbstractColumnRuleTest {

    @Test
    public void mustViolateTypeRule() throws IOException {
        Path path = createFileWithContent("a");
        getRuleConfigurator().columns().column(1).longType();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LongTypeRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateNotNullRule() throws IOException {
        Path path = createFileWithContent("a||c");
        getRuleConfigurator().columns().column(2).longType().notNull();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMinLongRule() throws IOException {
        Path path = createFileWithContent("1");
        getRuleConfigurator().columns().column(1).longType().min(2);
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MinLongRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMaxLongRule() throws IOException {
        Path path = createFileWithContent("3");
        getRuleConfigurator().columns().column(1).longType().max(2);
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MaxLongRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateDomainLongRule() throws IOException {
        Path path = createFileWithContent("5");
        getRuleConfigurator().columns().column(1).longType().domain(1L, 2L);
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DomainLongRule.class.getName(), violations.get(0).getRule());
    }

}
