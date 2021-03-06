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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class LocalDateRuleTest extends AbstractColumnRuleTest {

    @Test
    public void mustViolateTypeRule() throws IOException {
        Path path = createFileWithContent("a");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        getRuleConfigurator().columns().column(1).localDateType(formatter);
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateTypeRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateNotNullRule() throws IOException {
        Path path = createFileWithContent("a||c");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        getRuleConfigurator().columns().column(2).localDateType(formatter).notNull();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateFutureRule() throws IOException {
        Path path = createFileWithContent("19/12/1991");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        getRuleConfigurator().columns().column(1).localDateType(formatter).future();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateFutureRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateFutureOrPresentRule() throws IOException {
        Path path = createFileWithContent("19/12/1991");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        getRuleConfigurator().columns().column(1).localDateType(formatter).futureOrPresent();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateFutureOrPresentRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolatePastRule() throws IOException {
        String dataAtual = getDateWithDayCalculated(1);
        Path path = createFileWithContent(dataAtual);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        getRuleConfigurator().columns().column(1).localDateType(formatter).past();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDatePastRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolatePastOrPresentRule() throws IOException {
        String dataAtual = getDateWithDayCalculated(1);
        Path path = createFileWithContent(dataAtual);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        getRuleConfigurator().columns().column(1).localDateType(formatter).pastOrPresent();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDatePastOrPresentRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateAfterRule() throws IOException {
        Path path = createFileWithContent("19/12/1991");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate min = LocalDate.of(1992, 12, 19);
        getRuleConfigurator().columns().column(1).localDateType(formatter).after(min);
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateAfterRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateAfterColumnRule() throws IOException {
        Path path = createFileWithContent("19/12/1991|18/12/1991");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        getRuleConfigurator().columns().column(2).localDateType(formatter).after(1);
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateAfterRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateBeforeRule() throws IOException {
        Path path = createFileWithContent("19/12/1991");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate max = LocalDate.of(1991, 12, 18);
        getRuleConfigurator().columns().column(1).localDateType(formatter).before(max);
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateBeforeRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateBeforeColumnRule() throws IOException {
        Path path = createFileWithContent("19/12/1991|18/12/1991");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        getRuleConfigurator().columns().column(1).localDateType(formatter).before(2);
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateBeforeRule.class.getName(), violations.get(0).getRule());
    }

    private String getDateWithDayCalculated(int amount) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_MONTH, amount);
        Locale locale = new Locale("pt", "BR");
        return new SimpleDateFormat("dd/MM/yyyy", locale).format(instance.getTime());
    }

}
