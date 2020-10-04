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
package com.jonpereiradev.jfile.reader.benchmark;

import com.jonpereiradev.jfile.reader.JFileReader;
import com.jonpereiradev.jfile.reader.JFileReaderConfig;
import com.jonpereiradev.jfile.reader.JFileReaderFactory;
import com.jonpereiradev.jfile.reader.converter.FileColumn;
import com.jonpereiradev.jfile.reader.file.LineValue;
import com.jonpereiradev.jfile.reader.infrastructure.AbstractFileReaderTest;
import com.jonpereiradev.jfile.reader.validator.JFileValidator;
import com.jonpereiradev.jfile.reader.validator.JFileValidatorConfig;
import com.jonpereiradev.jfile.reader.validator.JFileValidatorFactory;
import com.jonpereiradev.jfile.reader.validator.ValidationReport;
import org.junit.Assert;
import org.junit.Ignore;
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
    public void measureReadingPerformance() throws IOException {
        JFileReaderConfig readerConfig = JFileReaderFactory.newUtf8ReaderConfig(";");
        JFileReader reader = JFileReaderFactory.newJFileReader(pathToFakeFile, readerConfig);

        int countNumberOfLines = 0;

        for (LineValue line : reader) {
            countNumberOfLines++;
        }

        Assert.assertEquals(NUMBER_OF_LINES, countNumberOfLines);
    }

    @Benchmark
    public void measureValidatingWithSuccessPerformance() throws IOException {
        JFileReaderConfig configuration = JFileReaderFactory.newUtf8ReaderConfig(";");
        JFileValidatorConfig validatorConfig = JFileValidatorFactory.newValidatorConfig();

        validatorConfig
            .columns()
            .column(1).stringType().notNull()
            .column(2).stringType().notNull()
            .column(3).integerType().notNull()
            .column(4).stringType().notNull()
            .column(5).stringType().notNull()
            .column(6).stringType().notNull();

        JFileReader reader = JFileReaderFactory.newJFileReader(pathToFakeFile, configuration);
        JFileValidator validator = JFileValidatorFactory.newJFileValidator(validatorConfig);

        for (LineValue line : reader) {
            validator.validate(line);
        }
    }

    @Benchmark
    public void measureValidatingWithViolationPerformance() throws IOException {
        JFileReaderConfig configuration = JFileReaderFactory.newUtf8ReaderConfig(";");
        JFileValidatorConfig validatorConfig = JFileValidatorFactory.newValidatorConfig();

        validatorConfig
            .columns()
            .column(1).integerType().notNull()
            .column(2).stringType().notNull()
            .column(3).integerType().notNull()
            .column(4).stringType().notNull()
            .column(5).stringType().notNull()
            .column(6).stringType().notNull();

        JFileReader reader = JFileReaderFactory.newJFileReader(pathToFakeFile, configuration);
        JFileValidator validator = JFileValidatorFactory.newJFileValidator(validatorConfig);

        for (LineValue line : reader) {
            ValidationReport validation = validator.validate(line);
            Assert.assertTrue(validation.isNotValid());
            Assert.assertFalse(validation.getViolations().isEmpty());
        }
    }

    @Benchmark
    @Ignore
    public void measureValidatingAllFileWithViolationPerformance() throws IOException {
        JFileReaderConfig configuration = JFileReaderFactory.newUtf8ReaderConfig(";");
        JFileValidatorConfig validatorConfig = JFileValidatorFactory.newValidatorConfig().maxViolationSize(100);

        validatorConfig
            .columns()
            .column(1).integerType().notNull()
            .column(2).stringType().notNull()
            .column(3).integerType().notNull()
            .column(4).stringType().notNull()
            .column(5).stringType().notNull()
            .column(6).stringType().notNull();

        JFileReader reader = JFileReaderFactory.newJFileReader(pathToFakeFile, configuration);
        JFileValidator validator = JFileValidatorFactory.newJFileValidator(validatorConfig);

        reader.forEach(lineValue -> {
            ValidationReport report = validator.validate(lineValue);
            Assert.assertTrue(report.isNotValid());
            Assert.assertFalse(report.getViolations().isEmpty());
        });
    }

    @Benchmark
    public void measureConvertingPerformance() throws IOException {
        JFileReaderConfig configuration = JFileReaderFactory.newUtf8ReaderConfig(";");
        JFileReader reader = JFileReaderFactory.newJFileReader(pathToFakeFile, configuration);

        int countNumberOfLines = 0;

        for (LineValue line : reader) {
            reader.convert(line, PerformanceObject.class);
            countNumberOfLines++;
        }

        Assert.assertEquals(NUMBER_OF_LINES, countNumberOfLines);
    }

    @Benchmark
    public void measureValidatingAndConvertingPerformance() throws IOException {
        JFileReaderConfig configuration = JFileReaderFactory.newUtf8ReaderConfig(";");
        JFileValidatorConfig validatorConfig = JFileValidatorFactory.newValidatorConfig();

        validatorConfig
            .columns()
            .column(1).stringType().notNull()
            .column(2).stringType().notNull()
            .column(3).integerType().notNull()
            .column(4).stringType().notNull()
            .column(5).stringType().notNull()
            .column(6).stringType().notNull();

        JFileReader reader = JFileReaderFactory.newJFileReader(pathToFakeFile, configuration);
        JFileValidator validator = JFileValidatorFactory.newJFileValidator(validatorConfig);

        int countNumberOfLines = 0;

        for (LineValue line : reader) {
            validator.validate(line);
            reader.convert(line, PerformanceObject.class);
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
