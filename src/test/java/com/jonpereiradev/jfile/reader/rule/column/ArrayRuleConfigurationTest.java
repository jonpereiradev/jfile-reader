package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.rule.RuleViolation;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class ArrayRuleConfigurationTest extends AbstractColumnRuleConfigurationTest {

    @Test
    public void mustValidateArrayShortCommaSeparator() throws IOException {
        Path path = createFileWithContent("a, b, c");

        getRuleConfigurator().column(1).arrayOf().characterType().domain('1', '2', '3');
        getRuleConfigurator().build();

        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DomainCharacterRule.class.getName(), violations.get(0).getRule());
    }

}
