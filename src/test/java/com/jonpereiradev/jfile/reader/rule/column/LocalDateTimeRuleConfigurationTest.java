package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.rule.RuleViolation;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class LocalDateTimeRuleConfigurationTest extends AbstractColumnRuleConfigurationTest {

    @Test
    public void mustViolateTypeRule() throws IOException {
        Path path = createFileWithContent("a");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        getRuleConfigurator().column(1).localDateTimeType(formatter);
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateTimeTypeRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateNotNullRule() throws IOException {
        Path path = createFileWithContent("a||c");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        getRuleConfigurator().column(2).localDateTimeType(formatter).notNull();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateFutureRule() throws IOException {
        Path path = createFileWithContent("01/01/1991 10:10:10.100");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");

        getRuleConfigurator()
            .column(1)
            .localDateTimeType(formatter)
            .future();

        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateTimeFutureRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateFutureOrPresentRule() throws IOException {
        Path path = createFileWithContent("01/01/1991 10:10:10.100");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");

        getRuleConfigurator()
            .column(1)
            .localDateTimeType(formatter)
            .futureOrPresent();

        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateTimeFutureOrPresentRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolatePastRule() throws IOException {
        String dataAtual = getDateWithDayCalculated(1);
        Path path = createFileWithContent(dataAtual);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");

        getRuleConfigurator()
            .column(1)
            .localDateTimeType(formatter)
            .past();

        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateTimePastRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolatePastOrPresentRule() throws IOException {
        String dataAtual = getDateWithDayCalculated(1);
        Path path = createFileWithContent(dataAtual);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");

        getRuleConfigurator()
            .column(1)
            .localDateTimeType(formatter)
            .pastOrPresent();

        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateTimePastOrPresentRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateAfterRule() throws IOException {
        String dataAtual = getDateWithDayCalculated(1);
        Path path = createFileWithContent(dataAtual);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");
        LocalDateTime min = LocalDateTime.now().plusDays(1);
        getRuleConfigurator().column(1).localDateTimeType(formatter).after(min);
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateTimeAfterRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateAfterColumnRule() throws IOException {
        String dataInicio = getDateWithDayCalculated(1);
        String dataFim = getDateWithDayCalculated(-1);
        Path path = createFileWithContent(dataInicio + "|" + dataFim);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");
        getRuleConfigurator().column(2).localDateTimeType(formatter).after(1);
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateTimeAfterRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateBeforeRule() throws IOException {
        String dataAtual = getDateWithDayCalculated(1);
        Path path = createFileWithContent(dataAtual);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");
        LocalDateTime max = LocalDateTime.now().minusDays(1);
        getRuleConfigurator().column(1).localDateTimeType(formatter).before(max);
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateTimeBeforeRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateBeforeColumnRule() throws IOException {
        String dataInicio = getDateWithDayCalculated(1);
        String dataFim = getDateWithDayCalculated(-1);
        Path path = createFileWithContent(dataInicio + "|" + dataFim);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");
        getRuleConfigurator().column(1).localDateTimeType(formatter).before(2);
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateTimeBeforeRule.class.getName(), violations.get(0).getRule());
    }

    private String getDateWithDayCalculated(int amount) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_MONTH, amount);
        Locale locale = new Locale("pt", "BR");
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS", locale).format(instance.getTime());
    }

}
