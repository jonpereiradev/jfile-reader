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
package com.jonpereiradev.jfile.reader.validator.rule.line;

import com.jonpereiradev.jfile.reader.JFileReader;
import com.jonpereiradev.jfile.reader.JFileReaderConfig;
import com.jonpereiradev.jfile.reader.JFileReaderFactory;
import com.jonpereiradev.jfile.reader.infrastructure.AbstractFileReaderTest;
import com.jonpereiradev.jfile.reader.validator.JFileValidator;
import com.jonpereiradev.jfile.reader.validator.JFileValidatorConfig;
import com.jonpereiradev.jfile.reader.validator.JFileValidatorFactory;
import com.jonpereiradev.jfile.reader.validator.rule.RuleViolation;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class ColumnValueSizeRuleTest extends AbstractFileReaderTest {

    @Test
    public void mustViolateRule() throws IOException {
        Path path = createFileWithContent("1|2|3|4");
        JFileReaderConfig readerConfig = JFileReaderFactory.newUtf8ReaderConfig("\\|");
        JFileReader reader = JFileReaderFactory.newJFileReader(path, readerConfig);

        JFileValidatorConfig validatorConfig = JFileValidatorFactory.newValidatorConfig();
        JFileValidator validator = JFileValidatorFactory.newJFileValidator(validatorConfig);

        validatorConfig.files().lines().columnsSize(5);

        List<RuleViolation> violations = validator.validate(reader).getViolations();
        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void mustSuccessRule() throws IOException {
        Path path = createFileWithContent("1|2|3|4|5");
        JFileReaderConfig readerConfig = JFileReaderFactory.newUtf8ReaderConfig("\\|");
        JFileReader reader = JFileReaderFactory.newJFileReader(path, readerConfig);

        JFileValidatorConfig validatorConfig = JFileValidatorFactory.newValidatorConfig();
        JFileValidator validator = JFileValidatorFactory.newJFileValidator(validatorConfig);

        validatorConfig.files().lines().columnsSize(5);

        List<RuleViolation> violations = validator.validate(reader).getViolations();
        Assert.assertTrue(violations.isEmpty());
    }

}
