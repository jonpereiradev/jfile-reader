package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.rule.RuleViolation;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class CharacterRuleConfigurationTest extends AbstractColumnRuleConfigurationTest {

    @Test
    public void mustViolateTypeRule() throws IOException {
        Path path = createFileWithContent("a|ab|c");
        getRuleConfigurator().column(2).characterType().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(CharacterTypeRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateDomainRule() throws IOException {
        Path path = createFileWithContent("a|a|c");
        getRuleConfigurator().column(2).characterType().domain('0').build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DomainCharacterRule.class.getName(), violations.get(0).getRule());
    }

}
