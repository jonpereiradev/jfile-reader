package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.rule.RuleViolation;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.List;

public class BigDecimalRuleConfigurationTest extends AbstractColumnRuleConfigurationTest {

    @Test
    public void mustViolateTypeRule() throws IOException {
        Path path = createFileWithContent("a");
        getRuleConfigurator().column(1).bigDecimalType(getBigDecimalFormat());
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(BigDecimalTypeRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateNotNullRule() throws IOException {
        Path path = createFileWithContent("a||c");

        getRuleConfigurator()
            .column(2)
            .bigDecimalType(getBigDecimalFormat())
            .notNull();

        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMinRule() throws IOException {
        Path path = createFileWithContent("1");

        getRuleConfigurator()
            .column(1)
            .bigDecimalType(getBigDecimalFormat())
            .min(BigDecimal.valueOf(2));

        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MinBigDecimalRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMaxRule() throws IOException {
        Path path = createFileWithContent("3");

        getRuleConfigurator()
            .column(1)
            .bigDecimalType(getBigDecimalFormat())
            .max(BigDecimal.valueOf(2));

        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MaxBigDecimalRule.class.getName(), violations.get(0).getRule());
    }

    private DecimalFormat getBigDecimalFormat() {
        DecimalFormat format = new DecimalFormat();
        format.setParseBigDecimal(true);
        return format;
    }

}
