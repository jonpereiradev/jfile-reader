package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.rule.RuleViolation;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

public class StringRuleConfigurationTest extends AbstractColumnRuleConfigurationTest {

    @Test
    public void mustViolateNotNullRule() throws IOException {
        Path path = createFileWithContent("a||c");
        getRuleConfigurator().column(2).stringType().notNull();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateNotEmptyRule() throws IOException {
        Path path = createFileWithContent("a|     |c");
        getRuleConfigurator().column(2).stringType().notEmpty();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotEmptyRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMinRule() throws IOException {
        Path path = createFileWithContent("a| ab |c");
        getRuleConfigurator().column(2).stringType().min(3);
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MinStringRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMaxRule() throws IOException {
        Path path = createFileWithContent("a| abcde |c");
        getRuleConfigurator().column(2).stringType().max(3);
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MaxStringRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateDomainRule() throws IOException {
        Path path = createFileWithContent("a|2|c");
        getRuleConfigurator().column(2).stringType().domain("1");
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DomainStringRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateRegexRule() throws IOException {
        Path path = createFileWithContent("a|a|c");
        Pattern pattern = Pattern.compile("\\d+");
        getRuleConfigurator().column(2).stringType().regex(pattern);
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(RegexRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateEmailRule() throws IOException {
        Path path = createFileWithContent("a|a|c");
        getRuleConfigurator().column(2).stringType().email();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(EmailRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateCnpjRule() throws IOException {
        Path path = createFileWithContent("a|a|c");
        getRuleConfigurator().column(2).stringType().cnpj();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(CnpjRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateCpfRule() throws IOException {
        Path path = createFileWithContent("a|a|c");
        getRuleConfigurator().column(2).stringType().cpf();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(CpfRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateOnlyNullRule() throws IOException {
        Path path = createFileWithContent("a|a|c");
        getRuleConfigurator().column(2).stringType().onlyNull();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(OnlyNullRule.class.getName(), violations.get(0).getRule());
    }

}
