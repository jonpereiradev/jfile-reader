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

import com.jonpereiradev.jfile.reader.JFileReader;
import com.jonpereiradev.jfile.reader.JFileReaderConfig;
import com.jonpereiradev.jfile.reader.JFileReaderFactory;
import com.jonpereiradev.jfile.reader.infrastructure.AbstractFileReaderTest;
import com.jonpereiradev.jfile.reader.validator.JFileValidator;
import com.jonpereiradev.jfile.reader.validator.JFileValidatorConfig;
import com.jonpereiradev.jfile.reader.validator.JFileValidatorFactory;
import com.jonpereiradev.jfile.reader.validator.rule.RuleViolation;
import com.jonpereiradev.jfile.reader.validator.rule.configurator.LineRuleConfigurator;
import org.junit.Before;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

abstract class AbstractColumnRuleTest extends AbstractFileReaderTest {

    private LineRuleConfigurator ruleConfigurator;
    private JFileReaderConfig readerConfig;
    private JFileValidatorConfig validatorConfig;

    @Before
    public void beforeEach() {
        readerConfig = JFileReaderFactory.newUtf8ReaderConfig("\\|");
        validatorConfig = JFileValidatorFactory.newValidatorConfig();
        ruleConfigurator = validatorConfig.files().lines();
    }

    protected List<RuleViolation> validate(Path path) throws IOException {
        JFileReader reader = JFileReaderFactory.newJFileReader(path, readerConfig);
        JFileValidator validator = JFileValidatorFactory.newJFileValidator(validatorConfig);
        List<RuleViolation> ruleViolations = new ArrayList<>();

        reader.forEach(lineValue -> {
            ruleViolations.addAll(validator.validate(lineValue).getViolations());
        });

        return ruleViolations;
    }

    public LineRuleConfigurator getRuleConfigurator() {
        return ruleConfigurator;
    }

    public JFileReaderConfig getReaderConfig() {
        return readerConfig;
    }

    public JFileValidatorConfig getValidatorConfig() {
        return validatorConfig;
    }

}
