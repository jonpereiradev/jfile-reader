package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.rule.RuleViolation;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class RefRuleConfigurationTest extends AbstractColumnRuleConfigurationTest {

    @Test
    public void mustViolateRefFilledRule() throws IOException {
        Path path = createFileWithContent("1||3");
        getRuleConfigurator().column(2).integerType().depends(1).filled().notNull();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateRefFilledDomainRule() throws IOException {
        Path path = createFileWithContent("1||3");
        getRuleConfigurator().column(2).integerType().depends(1).filled('1').notNull();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateRefEmptyRule() throws IOException {
        Path path = createFileWithContent("||3");
        getRuleConfigurator().column(1).integerType().depends(2).empty().notNull();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMultipleRefRule() throws IOException {
        Path path = createFileWithContent("2||3");

        getRuleConfigurator()
            .column(1)
            .integerType()
            .depends(2)
            .filled(1)
            .notNull()
            .depends(3)
            .filled(3)
            .max(1);

        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MaxIntegerRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateOnlyNullRule() throws IOException {
        Path path = createFileWithContent("2||3");

        getRuleConfigurator()
            .column(1)
            .integerType()
            .depends(2)
            .filled(1)
            .notNull()
            .apply()
            .onlyNull();

        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(OnlyNullRule.class.getName(), violations.get(0).getRule());
    }

}
