package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.rule.RuleViolation;
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

public class LocalDateRuleConfigurationTest extends AbstractColumnRuleConfigurationTest {

    @Test
    public void mustViolateTypeRule() throws IOException {
        Path path = createFileWithContent("a");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        getRuleConfigurator().column(1).localDateType(formatter).build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateTypeRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateNotNullRule() throws IOException {
        Path path = createFileWithContent("a||c");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        getRuleConfigurator().column(2).localDateType(formatter).notNull().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateFutureRule() throws IOException {
        Path path = createFileWithContent("19/12/1991");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        getRuleConfigurator().column(1).localDateType(formatter).future().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateFutureRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateFutureOrPresentRule() throws IOException {
        Path path = createFileWithContent("19/12/1991");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        getRuleConfigurator().column(1).localDateType(formatter).futureOrPresent().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateFutureOrPresentRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolatePastRule() throws IOException {
        String dataAtual = getDataUmDiaAposAtual();
        Path path = createFileWithContent(dataAtual);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        getRuleConfigurator().column(1).localDateType(formatter).past().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDatePastRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolatePastOrPresentRule() throws IOException {
        String dataAtual = getDataUmDiaAposAtual();
        Path path = createFileWithContent(dataAtual);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        getRuleConfigurator().column(1).localDateType(formatter).pastOrPresent().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDatePastOrPresentRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMinRule() throws IOException {
        Path path = createFileWithContent("19/12/1991");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate min = LocalDate.of(1992, 12, 19);
        getRuleConfigurator().column(1).localDateType(formatter).min(min).build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateMinRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMaxRule() throws IOException {
        Path path = createFileWithContent("19/12/1991");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate max = LocalDate.of(1991, 12, 18);
        getRuleConfigurator().column(1).localDateType(formatter).max(max).build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateMaxRule.class.getName(), violations.get(0).getRule());
    }

    private String getDataUmDiaAposAtual() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_MONTH, 1);
        Locale locale = new Locale("pt", "BR");
        return new SimpleDateFormat("dd/MM/yyyy", locale).format(instance.getTime());
    }

}
