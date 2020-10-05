/*
 * MIT License
 *
 * Copyright (c) 2020 Jonathan de Almeida Pereira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.jonpereiradev.jfile.reader.validator.rule.column;


import com.jonpereiradev.jfile.reader.validator.rule.RuleViolation;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;


public class StringRuleTest extends AbstractColumnRuleTest {

    @Test
    public void mustViolateNotNullRule() throws IOException {
        Path path = createFileWithContent("a||c");
        getRuleConfigurator().columns().column(2).stringType().notNull();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateNotEmptyRule() throws IOException {
        Path path = createFileWithContent("a|     |c");
        getRuleConfigurator().columns().column(2).stringType().notEmpty();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotEmptyRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMinRule() throws IOException {
        Path path = createFileWithContent("a| ab |c");
        getRuleConfigurator().columns().column(2).stringType().minLength(3);
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MinLengthStringRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMaxRule() throws IOException {
        Path path = createFileWithContent("a| abcde |c");
        getRuleConfigurator().columns().column(2).stringType().maxLength(3);
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MaxLengthStringRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateDomainRule() throws IOException {
        Path path = createFileWithContent("a|2|c");
        getRuleConfigurator().columns().column(2).stringType().domain("1");
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DomainStringRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateRegexRule() throws IOException {
        Path path = createFileWithContent("a|a|c");
        Pattern pattern = Pattern.compile("\\d+");
        getRuleConfigurator().columns().column(2).stringType().regex(pattern);
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(RegexRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateEmailRule() throws IOException {
        Path path = createFileWithContent("a|a|c");
        getRuleConfigurator().columns().column(2).stringType().email();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(EmailRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateCnpjRule() throws IOException {
        Path path = createFileWithContent("a|a|c");
        getRuleConfigurator().columns().column(2).stringType().cnpj();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(CnpjRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateCpfRule() throws IOException {
        Path path = createFileWithContent("a|a|c");
        getRuleConfigurator().columns().column(2).stringType().cpf();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(CpfRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateOnlyNullRule() throws IOException {
        Path path = createFileWithContent("a|a|c");
        getRuleConfigurator().columns().column(2).stringType().onlyNull();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(OnlyNullRule.class.getName(), violations.get(0).getRule());
    }

}
