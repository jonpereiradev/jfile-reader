package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.validator.rule.RuleViolation;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class BooleanRuleTest extends AbstractColumnRuleTest {

    @Test
    public void mustViolateTypeRule() throws IOException {
        Path path = createFileWithContent("a|a|c");
        getRuleConfigurator().columns().column(2).booleanType();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(BooleanTypeRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateDomainRule() throws IOException {
        Path path = createFileWithContent("a|t|c");
        getRuleConfigurator().columns().column(2).booleanType().domain('0', '1');
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DomainCharacterRule.class.getName(), violations.get(0).getRule());
    }

}
