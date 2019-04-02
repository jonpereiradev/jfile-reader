package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.rule.RuleViolation;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DateRuleConfigurationTest extends AbstractColumnRuleConfigurationTest {

    @Test
    public void mustViolateTypeRule() throws IOException {
        Path path = createFileWithContent("a");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        getRuleConfigurator().column(1).dateType(dateFormat).build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DateTypeRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateNotNullRule() throws IOException {
        Path path = createFileWithContent("a||c");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        getRuleConfigurator().column(2).dateType(dateFormat).notNull().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateFutureRule() throws IOException {
        Path path = createFileWithContent("19/12/1991");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        getRuleConfigurator().column(1).dateType(dateFormat).future().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DateFutureRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateFutureOrPresentRule() throws IOException {
        Path path = createFileWithContent("19/12/1991");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        getRuleConfigurator().column(1).dateType(dateFormat).futureOrPresent().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DateFutureOrPresentRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolatePastRule() throws IOException {
        String dataAtual = getDataUmDiaAposAtual();
        Path path = createFileWithContent(dataAtual);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        getRuleConfigurator().column(1).dateType(dateFormat).past().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DatePastRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolatePastOrPresentRule() throws IOException {
        String dataAtual = getDataUmDiaAposAtual();
        Path path = createFileWithContent(dataAtual);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        getRuleConfigurator().column(1).dateType(dateFormat).pastOrPresent().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DatePastOrPresentRule.class.getName(), violations.get(0).getRule());
    }

    private String getDataUmDiaAposAtual() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_MONTH, 1);
        Locale locale = new Locale("pt", "BR");
        return new SimpleDateFormat("dd/MM/yyyy", locale).format(instance.getTime());
    }

}
