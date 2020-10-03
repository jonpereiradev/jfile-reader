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
package com.jonpereiradev.jfile.reader.validator.rule.configurator;

import com.jonpereiradev.jfile.reader.validator.JFileValidatorConfig;
import com.jonpereiradev.jfile.reader.validator.rule.RuleNode;
import com.jonpereiradev.jfile.reader.validator.rule.column.CnpjRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.ColumnRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.CpfRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.DomainStringRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.EmailRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.MaxStringRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.MinStringRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.NotEmptyRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.RegexRule;

import java.util.Arrays;
import java.util.regex.Pattern;

final class StringTypeConfiguratorImpl extends AbstractRuleConfigurator<StringTypeConfigurator> implements StringTypeConfigurator {

    StringTypeConfiguratorImpl(int position, JFileValidatorConfig configuration, RuleNode<ColumnRule> ruleNode) {
        super(position, configuration, ruleNode);
    }

    @Override
    public StringTypeConfigurator notEmpty() {
        return rule(NotEmptyRule::new);
    }

    @Override
    public StringTypeConfigurator min(int min) {
        return rule(position -> new MinStringRule(position, min));
    }

    @Override
    public StringTypeConfigurator max(int max) {
        return rule(position -> new MaxStringRule(position, max));
    }

    @Override
    public StringTypeConfigurator domain(String... values) {
        return rule(position -> new DomainStringRule(position, Arrays.asList(values)));
    }

    @Override
    public StringTypeConfigurator email() {
        return rule(EmailRule::new);
    }

    @Override
    public StringTypeConfigurator cpf() {
        return rule(CpfRule::new);
    }

    @Override
    public StringTypeConfigurator cnpj() {
        return rule(CnpjRule::new);
    }

    @Override
    public StringTypeConfigurator regex(Pattern pattern) {
        return rule(position -> new RegexRule(position, pattern));
    }
}
