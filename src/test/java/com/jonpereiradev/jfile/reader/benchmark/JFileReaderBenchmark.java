package com.jonpereiradev.jfile.reader.benchmark;

import com.jonpereiradev.jfile.reader.JFileReader;
import com.jonpereiradev.jfile.reader.JFileReaderFactory;
import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;
import com.jonpereiradev.jfile.reader.file.JFileLine;
import com.jonpereiradev.jfile.reader.infrastructure.AbstractFileReaderTest;
import com.jonpereiradev.jfile.reader.parser.FileColumn;
import com.jonpereiradev.jfile.reader.rule.RuleConfigurator;
import com.jonpereiradev.jfile.reader.rule.RuleViolation;
import com.jonpereiradev.jfile.reader.validation.Report;
import org.junit.Assert;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@Threads(1)
@Fork(1)
@Warmup(time = 1, iterations = 1)
@Measurement(iterations = 5)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class JFileReaderBenchmark extends AbstractFileReaderTest {

    private static final int NUMBER_OF_LINES = 500000;

    private Path pathToFakeFile;

    @Setup
    public void setUp() throws IOException {
        pathToFakeFile = createFakeTempFile(NUMBER_OF_LINES);
    }

    @TearDown
    public void tearDown() throws IOException {
        Files.deleteIfExists(pathToFakeFile);
    }

    @Benchmark
    public void measureReadingPerformance() {
        JFileReader reader = JFileReaderFactory.newInstance(pathToFakeFile, ReaderConfiguration.utf8Reader(";"));

        int countNumberOfLines = 0;

        for (JFileLine line : reader) {
            countNumberOfLines++;
        }

        Assert.assertEquals(NUMBER_OF_LINES, countNumberOfLines);
    }

    @Benchmark
    public void measureValidatingWithSuccessPerformance() {
        ReaderConfiguration configuration = ReaderConfiguration.utf8Reader(";");

        RuleConfigurator.defaultConfigurator(configuration).files().lines()
            .column(1).stringType().notNull()
            .column(2).stringType().notNull()
            .column(3).integerType().notNull()
            .column(4).stringType().notNull()
            .column(5).stringType().notNull()
            .column(6).stringType().notNull();

        JFileReader reader = JFileReaderFactory.newInstance(pathToFakeFile, configuration);

        for (JFileLine line : reader) {
            reader.validate(line);
        }
    }

    @Benchmark
    public void measureValidatingWithViolationPerformance() {
        ReaderConfiguration configuration = ReaderConfiguration.utf8Reader(";");

        RuleConfigurator.defaultConfigurator(configuration).files().lines()
            .column(1).integerType().notNull()
            .column(2).stringType().notNull()
            .column(3).integerType().notNull()
            .column(4).stringType().notNull()
            .column(5).stringType().notNull()
            .column(6).stringType().notNull();

        JFileReader reader = JFileReaderFactory.newInstance(pathToFakeFile, configuration);

        for (JFileLine line : reader) {
            Report validation = reader.validate(line);
            Assert.assertTrue(validation.isInvalid());
            Assert.assertFalse(validation.getViolations().isEmpty());
        }
    }

    @Benchmark
    public void measureValidatingAllFileWithViolationPerformance() {
        ReaderConfiguration configuration = ReaderConfiguration.utf8Reader(";").withMaxViolationSize(10000);

        RuleConfigurator.defaultConfigurator(configuration).files().lines()
            .column(1).integerType().notNull()
            .column(2).stringType().notNull()
            .column(3).integerType().notNull()
            .column(4).stringType().notNull()
            .column(5).stringType().notNull()
            .column(6).stringType().notNull();

        JFileReader reader = JFileReaderFactory.newInstance(pathToFakeFile, configuration);
        List<RuleViolation> violations = new ArrayList<>();


//        for (JFileLine line : reader) {
//            Report validation = reader.validate(line);
//
//            if (validation.isInvalid()) {
//                violations.addAll(validation.getViolations());
//            }
//
//            if (violations.size() > 1000) {
//                break;
//            }
//            Assert.assertTrue(validation.isInvalid());
//            Assert.assertFalse(validation.getViolations().isEmpty());
//        }
//
//        ReportValidation validation = reader.validate();

        Report report = reader.validate();
        Assert.assertTrue(report.isInvalid());
        Assert.assertFalse(report.getViolations().isEmpty());

//        Assert.assertFalse(violations.isEmpty());
    }

    @Benchmark
    public void measureConvertingPerformance() {
        ReaderConfiguration configuration = ReaderConfiguration.utf8Reader(";");
        JFileReader reader = JFileReaderFactory.newInstance(pathToFakeFile, configuration);

        int countNumberOfLines = 0;

        for (JFileLine line : reader) {
            reader.parse(line, PerformanceObject.class);
            countNumberOfLines++;
        }

        Assert.assertEquals(NUMBER_OF_LINES, countNumberOfLines);
    }

    @Benchmark
    public void measureValidatingAndConvertingPerformance() {
        ReaderConfiguration configuration = ReaderConfiguration.utf8Reader(";");

        RuleConfigurator.defaultConfigurator(configuration).files().lines()
            .column(1).stringType().notNull()
            .column(2).stringType().notNull()
            .column(3).integerType().notNull()
            .column(4).stringType().notNull()
            .column(5).stringType().notNull()
            .column(6).stringType().notNull();

        JFileReader reader = JFileReaderFactory.newInstance(pathToFakeFile, configuration);

        int countNumberOfLines = 0;

        for (JFileLine line : reader) {
            reader.validate(line);
            reader.parse(line, PerformanceObject.class);
            countNumberOfLines++;
        }

        Assert.assertEquals(NUMBER_OF_LINES, countNumberOfLines);
    }

    public static class PerformanceObject {

        @FileColumn(1)
        private String one;

        @FileColumn(2)
        private String two;

        @FileColumn(3)
        private Integer three;

        @FileColumn(4)
        private String four;

        @FileColumn(5)
        private String five;

        @FileColumn(6)
        private String six;

        public String getOne() {
            return one;
        }

        public void setOne(String one) {
            this.one = one;
        }

        public String getTwo() {
            return two;
        }

        public void setTwo(String two) {
            this.two = two;
        }

        public Integer getThree() {
            return three;
        }

        public void setThree(Integer three) {
            this.three = three;
        }

        public String getFour() {
            return four;
        }

        public void setFour(String four) {
            this.four = four;
        }

        public String getFive() {
            return five;
        }

        public void setFive(String five) {
            this.five = five;
        }

        public String getSix() {
            return six;
        }

        public void setSix(String six) {
            this.six = six;
        }
    }
}
