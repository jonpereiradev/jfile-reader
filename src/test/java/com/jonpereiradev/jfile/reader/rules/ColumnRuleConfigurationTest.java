package com.jonpereiradev.jfile.reader.rules;

import com.jonpereiradev.jfile.reader.AbstractFileReaderTest;
import com.jonpereiradev.jfile.reader.JFileReader;
import com.jonpereiradev.jfile.reader.JFileReaderFactory;
import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;
import com.jonpereiradev.jfile.reader.rules.column.BigDecimalTypeRule;
import com.jonpereiradev.jfile.reader.rules.column.BigIntegerTypeRule;
import com.jonpereiradev.jfile.reader.rules.column.BooleanTypeRule;
import com.jonpereiradev.jfile.reader.rules.column.CnpjRule;
import com.jonpereiradev.jfile.reader.rules.column.CpfRule;
import com.jonpereiradev.jfile.reader.rules.column.DateFutureOrPresentRule;
import com.jonpereiradev.jfile.reader.rules.column.DateFutureRule;
import com.jonpereiradev.jfile.reader.rules.column.DatePastOrPresentRule;
import com.jonpereiradev.jfile.reader.rules.column.DatePastRule;
import com.jonpereiradev.jfile.reader.rules.column.DateTypeRule;
import com.jonpereiradev.jfile.reader.rules.column.DomainIntegerRule;
import com.jonpereiradev.jfile.reader.rules.column.DomainLongRule;
import com.jonpereiradev.jfile.reader.rules.column.DomainShortRule;
import com.jonpereiradev.jfile.reader.rules.column.DomainStringRule;
import com.jonpereiradev.jfile.reader.rules.column.DoubleTypeRule;
import com.jonpereiradev.jfile.reader.rules.column.EmailRule;
import com.jonpereiradev.jfile.reader.rules.column.FloatTypeRule;
import com.jonpereiradev.jfile.reader.rules.column.IntegerTypeRule;
import com.jonpereiradev.jfile.reader.rules.column.LocalDateFutureOrPresentRule;
import com.jonpereiradev.jfile.reader.rules.column.LocalDateFutureRule;
import com.jonpereiradev.jfile.reader.rules.column.LocalDatePastOrPresentRule;
import com.jonpereiradev.jfile.reader.rules.column.LocalDatePastRule;
import com.jonpereiradev.jfile.reader.rules.column.LocalDateTimeFutureOrPresentRule;
import com.jonpereiradev.jfile.reader.rules.column.LocalDateTimeFutureRule;
import com.jonpereiradev.jfile.reader.rules.column.LocalDateTimePastOrPresentRule;
import com.jonpereiradev.jfile.reader.rules.column.LocalDateTimePastRule;
import com.jonpereiradev.jfile.reader.rules.column.LocalDateTimeTypeRule;
import com.jonpereiradev.jfile.reader.rules.column.LocalDateTypeRule;
import com.jonpereiradev.jfile.reader.rules.column.LongTypeRule;
import com.jonpereiradev.jfile.reader.rules.column.MaxBigDecimalRule;
import com.jonpereiradev.jfile.reader.rules.column.MaxBigIntegerRule;
import com.jonpereiradev.jfile.reader.rules.column.MaxDoubleRule;
import com.jonpereiradev.jfile.reader.rules.column.MaxFloatRule;
import com.jonpereiradev.jfile.reader.rules.column.MaxIntegerRule;
import com.jonpereiradev.jfile.reader.rules.column.MaxLongRule;
import com.jonpereiradev.jfile.reader.rules.column.MaxShortRule;
import com.jonpereiradev.jfile.reader.rules.column.MaxStringRule;
import com.jonpereiradev.jfile.reader.rules.column.MinBigDecimalRule;
import com.jonpereiradev.jfile.reader.rules.column.MinBigIntegerRule;
import com.jonpereiradev.jfile.reader.rules.column.MinDoubleRule;
import com.jonpereiradev.jfile.reader.rules.column.MinFloatRule;
import com.jonpereiradev.jfile.reader.rules.column.MinIntegerRule;
import com.jonpereiradev.jfile.reader.rules.column.MinLongRule;
import com.jonpereiradev.jfile.reader.rules.column.MinShortRule;
import com.jonpereiradev.jfile.reader.rules.column.MinStringRule;
import com.jonpereiradev.jfile.reader.rules.column.NotEmptyRule;
import com.jonpereiradev.jfile.reader.rules.column.NotNullRule;
import com.jonpereiradev.jfile.reader.rules.column.OnlyNullRule;
import com.jonpereiradev.jfile.reader.rules.column.RegexRule;
import com.jonpereiradev.jfile.reader.rules.column.ShortTypeRule;
import com.jonpereiradev.jfile.reader.rules.configurator.LineRuleConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class ColumnRuleConfigurationTest extends AbstractFileReaderTest {

    private LineRuleConfigurator ruleConfigurator;
    private ReaderConfiguration readerConfiguration;

    @Before
    public void beforeEach() {
        readerConfiguration = ReaderConfiguration.utf8Reader("\\|");
        ruleConfigurator = RuleConfigurator.defaultConfigurator(readerConfiguration).files().lines();
    }

    @Test
    public void mustViolateShortTypeRuleColumn() throws IOException {
        Path path = createFileWithContent("a");
        ruleConfigurator.column(1).shortType().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(ShortTypeRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateShortNotNullRuleColumn() throws IOException {
        Path path = createFileWithContent("a||c");
        ruleConfigurator.column(2).shortType().notNull().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMinShortRuleColumn() throws IOException {
        Path path = createFileWithContent("1");
        ruleConfigurator.column(1).shortType().min((short) 2).build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MinShortRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMaxShortRuleColumn() throws IOException {
        Path path = createFileWithContent("3");
        ruleConfigurator.column(1).shortType().max((short) 2).build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MaxShortRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateDomainShortRuleColumn() throws IOException {
        Path path = createFileWithContent("5");
        ruleConfigurator.column(1).shortType().domain((short) 1).build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DomainShortRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateIntegerTypeRuleColumn() throws IOException {
        Path path = createFileWithContent("a");
        ruleConfigurator.column(1).integerType().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(IntegerTypeRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateIntegerNotNullRuleColumn() throws IOException {
        Path path = createFileWithContent("a||c");
        ruleConfigurator.column(2).integerType().notNull().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMinIntegerRuleColumn() throws IOException {
        Path path = createFileWithContent("1");
        ruleConfigurator.column(1).integerType().min(2).build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MinIntegerRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMaxIntegerRuleColumn() throws IOException {
        Path path = createFileWithContent("3");
        ruleConfigurator.column(1).integerType().max(2).build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MaxIntegerRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateDomainIntegerRuleColumn() throws IOException {
        Path path = createFileWithContent("5");
        ruleConfigurator.column(1).integerType().domain(1, 2).build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DomainIntegerRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateLongTypeRuleColumn() throws IOException {
        Path path = createFileWithContent("a");
        ruleConfigurator.column(1).longType().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LongTypeRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateLongNotNullRuleColumn() throws IOException {
        Path path = createFileWithContent("a||c");
        ruleConfigurator.column(2).longType().notNull().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMinLongRuleColumn() throws IOException {
        Path path = createFileWithContent("1");
        ruleConfigurator.column(1).longType().min(2).build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MinLongRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMaxLongRuleColumn() throws IOException {
        Path path = createFileWithContent("3");
        ruleConfigurator.column(1).longType().max(2).build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MaxLongRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateDomainLongRuleColumn() throws IOException {
        Path path = createFileWithContent("5");
        ruleConfigurator.column(1).longType().domain(1L, 2L).build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DomainLongRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateFloatTypeRuleColumn() throws IOException {
        Path path = createFileWithContent("a");
        ruleConfigurator.column(1).floatType().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(FloatTypeRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateFloatNotNullRuleColumn() throws IOException {
        Path path = createFileWithContent("a||c");
        ruleConfigurator.column(2).floatType().notNull().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMinFloatRuleColumn() throws IOException {
        Path path = createFileWithContent("1");
        ruleConfigurator.column(1).floatType().min(2).build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MinFloatRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMaxFloatRuleColumn() throws IOException {
        Path path = createFileWithContent("3");
        ruleConfigurator.column(1).floatType().max(2).build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MaxFloatRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateDoubleTypeRuleColumn() throws IOException {
        Path path = createFileWithContent("a");
        ruleConfigurator.column(1).doubleType().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DoubleTypeRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateDoubleNotNullRuleColumn() throws IOException {
        Path path = createFileWithContent("a||c");
        ruleConfigurator.column(2).doubleType().notNull().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMinDoubleRuleColumn() throws IOException {
        Path path = createFileWithContent("1");
        ruleConfigurator.column(1).doubleType().min(2).build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MinDoubleRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMaxDoubleRuleColumn() throws IOException {
        Path path = createFileWithContent("3");
        ruleConfigurator.column(1).doubleType().max(2).build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MaxDoubleRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateBigIntegerTypeRuleColumn() throws IOException {
        Path path = createFileWithContent("a");
        ruleConfigurator.column(1).bigIntegerType().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(BigIntegerTypeRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateBigIntegerNotNullRuleColumn() throws IOException {
        Path path = createFileWithContent("a||c");
        ruleConfigurator.column(2).bigIntegerType().notNull().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMinBigIntegerRuleColumn() throws IOException {
        Path path = createFileWithContent("1");
        ruleConfigurator.column(1).bigIntegerType().min(BigInteger.valueOf(2)).build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MinBigIntegerRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMaxBigIntegerRuleColumn() throws IOException {
        Path path = createFileWithContent("3");
        ruleConfigurator.column(1).bigIntegerType().max(BigInteger.valueOf(2)).build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MaxBigIntegerRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateBigDecimalTypeRuleColumn() throws IOException {
        Path path = createFileWithContent("a");
        ruleConfigurator.column(1).bigDecimalType(getBigDecimalFormat()).build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(BigDecimalTypeRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateBigDecimalNotNullRuleColumn() throws IOException {
        Path path = createFileWithContent("a||c");

        ruleConfigurator
            .column(2)
            .bigDecimalType(getBigDecimalFormat())
            .notNull()
            .build();

        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMinBigDecimalRuleColumn() throws IOException {
        Path path = createFileWithContent("1");

        ruleConfigurator
            .column(1)
            .bigDecimalType(getBigDecimalFormat())
            .min(BigDecimal.valueOf(2))
            .build();

        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MinBigDecimalRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMaxBigDecimalRuleColumn() throws IOException {
        Path path = createFileWithContent("3");

        ruleConfigurator
            .column(1)
            .bigDecimalType(getBigDecimalFormat())
            .max(BigDecimal.valueOf(2))
            .build();

        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MaxBigDecimalRule.class.getSimpleName(), violations.get(0).getRule());
    }

    private DecimalFormat getBigDecimalFormat() {
        DecimalFormat format = new DecimalFormat();
        format.setParseBigDecimal(true);
        return format;
    }

    @Test
    public void mustViolateDateTypeRuleColumn() throws IOException {
        Path path = createFileWithContent("a");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ruleConfigurator.column(1).dateType(dateFormat).build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DateTypeRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateDateNotNullRuleColumn() throws IOException {
        Path path = createFileWithContent("a||c");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ruleConfigurator.column(2).dateType(dateFormat).notNull().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateDateFutureRuleColumn() throws IOException {
        Path path = createFileWithContent("19/12/1991");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ruleConfigurator.column(1).dateType(dateFormat).future().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DateFutureRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateDateFutureOrPresentRuleColumn() throws IOException {
        Path path = createFileWithContent("19/12/1991");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ruleConfigurator.column(1).dateType(dateFormat).futureOrPresent().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DateFutureOrPresentRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateDatePastRuleColumn() throws IOException {
        String dataAtual = getDataUmDiaAposAtual();
        Path path = createFileWithContent(dataAtual);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ruleConfigurator.column(1).dateType(dateFormat).past().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DatePastRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateDatePastOrPresentRuleColumn() throws IOException {
        String dataAtual = getDataUmDiaAposAtual();
        Path path = createFileWithContent(dataAtual);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ruleConfigurator.column(1).dateType(dateFormat).pastOrPresent().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DatePastOrPresentRule.class.getSimpleName(), violations.get(0).getRule());
    }

    private String getDataUmDiaAposAtual() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_MONTH, 1);
        Locale locale = new Locale("pt", "BR");
        return new SimpleDateFormat("dd/MM/yyyy", locale).format(instance.getTime());
    }

    @Test
    public void mustViolateLocalDateTypeRuleColumn() throws IOException {
        Path path = createFileWithContent("a");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ruleConfigurator.column(1).localDateType(formatter).build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateTypeRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateLocalDateNotNullRuleColumn() throws IOException {
        Path path = createFileWithContent("a||c");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ruleConfigurator.column(2).localDateType(formatter).notNull().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateLocalDateFutureRuleColumn() throws IOException {
        Path path = createFileWithContent("19/12/1991");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ruleConfigurator.column(1).localDateType(formatter).future().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateFutureRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateLocalDateFutureOrPresentRuleColumn() throws IOException {
        Path path = createFileWithContent("19/12/1991");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ruleConfigurator.column(1).localDateType(formatter).futureOrPresent().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateFutureOrPresentRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateLocalDatePastRuleColumn() throws IOException {
        String dataAtual = getDataUmDiaAposAtual();
        Path path = createFileWithContent(dataAtual);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ruleConfigurator.column(1).localDateType(formatter).past().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDatePastRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateLocalDatePastOrPresentRuleColumn() throws IOException {
        String dataAtual = getDataUmDiaAposAtual();
        Path path = createFileWithContent(dataAtual);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ruleConfigurator.column(1).localDateType(formatter).pastOrPresent().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDatePastOrPresentRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateLocalDateTimeTypeRuleColumn() throws IOException {
        Path path = createFileWithContent("a");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ruleConfigurator.column(1).localDateTimeType(formatter).build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateTimeTypeRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateLocalDateTimeNotNullRuleColumn() throws IOException {
        Path path = createFileWithContent("a||c");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ruleConfigurator.column(2).localDateTimeType(formatter).notNull().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateLocalDateTimeFutureRuleColumn() throws IOException {
        Path path = createFileWithContent("01/01/1991 10:10:10.100");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");

        ruleConfigurator
            .column(1)
            .localDateTimeType(formatter)
            .future()
            .build();

        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateTimeFutureRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateLocalDateTimeFutureOrPresentRuleColumn() throws IOException {
        Path path = createFileWithContent("01/01/1991 10:10:10.100");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");

        ruleConfigurator
            .column(1)
            .localDateTimeType(formatter)
            .futureOrPresent()
            .build();

        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateTimeFutureOrPresentRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateLocalDateTimePastRuleColumn() throws IOException {
        String dataAtual = getDataHoraUmDiaAposAtual();
        Path path = createFileWithContent(dataAtual);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");

        ruleConfigurator
            .column(1)
            .localDateTimeType(formatter)
            .past()
            .build();

        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateTimePastRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateLocalDateTimePastOrPresentRuleColumn() throws IOException {
        String dataAtual = getDataHoraUmDiaAposAtual();
        Path path = createFileWithContent(dataAtual);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");

        ruleConfigurator
            .column(1)
            .localDateTimeType(formatter)
            .pastOrPresent()
            .build();

        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(LocalDateTimePastOrPresentRule.class.getSimpleName(), violations.get(0).getRule());
    }

    private String getDataHoraUmDiaAposAtual() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_MONTH, 1);
        Locale locale = new Locale("pt", "BR");
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS", locale).format(instance.getTime());
    }

    @Test
    public void mustViolateStringNotNullRuleColumn() throws IOException {
        Path path = createFileWithContent("a||c");
        ruleConfigurator.column(2).stringType().notNull().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateStringNotEmptyRuleColumn() throws IOException {
        Path path = createFileWithContent("a|     |c");
        ruleConfigurator.column(2).stringType().notEmpty().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotEmptyRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMinStringRuleColumn() throws IOException {
        Path path = createFileWithContent("a| ab |c");
        ruleConfigurator.column(2).stringType().min(3).build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MinStringRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateMaxStringRuleColumn() throws IOException {
        Path path = createFileWithContent("a| abcde |c");
        ruleConfigurator.column(2).stringType().max(3).build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(MaxStringRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateDomainStringRuleColumn() throws IOException {
        Path path = createFileWithContent("a|2|c");
        ruleConfigurator.column(2).stringType().domain("1").build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DomainStringRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateRegexRuleColumn() throws IOException {
        Path path = createFileWithContent("a|a|c");
        Pattern pattern = Pattern.compile("\\d+");
        ruleConfigurator.column(2).stringType().regex(pattern).build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(RegexRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateEmailRuleColumn() throws IOException {
        Path path = createFileWithContent("a|a|c");
        ruleConfigurator.column(2).stringType().email().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(EmailRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateCnpjRuleColumn() throws IOException {
        Path path = createFileWithContent("a|a|c");
        ruleConfigurator.column(2).stringType().cnpj().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(CnpjRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateCpfRuleColumn() throws IOException {
        Path path = createFileWithContent("a|a|c");
        ruleConfigurator.column(2).stringType().cpf().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(CpfRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateBooleanRuleColumn() throws IOException {
        Path path = createFileWithContent("a|a|c");
        ruleConfigurator.column(2).booleanType().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(BooleanTypeRule.class.getSimpleName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateOnlyNullRuleColumn() throws IOException {
        Path path = createFileWithContent("a|a|c");
        ruleConfigurator.column(2).stringType().onlyNull().build();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(OnlyNullRule.class.getSimpleName(), violations.get(0).getRule());
    }

    private List<RuleViolation> validate(Path path) {
        JFileReader reader = JFileReaderFactory.newInstance(path, readerConfiguration);
        return reader.validate();
    }

}
