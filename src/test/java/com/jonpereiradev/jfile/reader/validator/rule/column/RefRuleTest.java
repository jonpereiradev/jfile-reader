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

public class RefRuleTest extends AbstractColumnRuleTest {

    @Test
    public void mustViolateRefFilledRule() throws IOException {
        Path path = createFileWithContent("1||3");
        getRuleConfigurator().columns().column(2).integerType().depends(1).filled().notNull();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateRefFilledDomainRule() throws IOException {
        Path path = createFileWithContent("1||3");
        getRuleConfigurator().columns().column(2).integerType().depends(1).filled('1').notNull();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateRefEmptyRule() throws IOException {
        Path path = createFileWithContent("||3");
        getRuleConfigurator().columns().column(1).integerType().depends(2).empty().notNull();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMultipleRefRule() throws IOException {
        Path path = createFileWithContent("2||3");

        getRuleConfigurator()
            .columns().column(1)
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
            .columns().column(1)
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
