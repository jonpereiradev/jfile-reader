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
package com.jonpereiradev.jfile.reader.validator.rule;


import com.jonpereiradev.jfile.reader.validator.rule.column.BigDecimalRuleTest;
import com.jonpereiradev.jfile.reader.validator.rule.column.BigIntegerRuleTest;
import com.jonpereiradev.jfile.reader.validator.rule.column.BooleanRuleTest;
import com.jonpereiradev.jfile.reader.validator.rule.column.CharacterRuleTest;
import com.jonpereiradev.jfile.reader.validator.rule.column.DateRuleTest;
import com.jonpereiradev.jfile.reader.validator.rule.column.DoubleRuleTest;
import com.jonpereiradev.jfile.reader.validator.rule.column.FloatRuleTest;
import com.jonpereiradev.jfile.reader.validator.rule.column.IntegerRuleTest;
import com.jonpereiradev.jfile.reader.validator.rule.column.LocalDateRuleTest;
import com.jonpereiradev.jfile.reader.validator.rule.column.LocalDateTimeRuleTest;
import com.jonpereiradev.jfile.reader.validator.rule.column.LongRuleTest;
import com.jonpereiradev.jfile.reader.validator.rule.column.RefRuleTest;
import com.jonpereiradev.jfile.reader.validator.rule.column.ShortRuleTest;
import com.jonpereiradev.jfile.reader.validator.rule.column.StringRuleTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
    BigDecimalRuleTest.class,
    BigIntegerRuleTest.class,
    BooleanRuleTest.class,
    CharacterRuleTest.class,
    DateRuleTest.class,
    DoubleRuleTest.class,
    FloatRuleTest.class,
    IntegerRuleTest.class,
    LocalDateRuleTest.class,
    LocalDateTimeRuleTest.class,
    LongRuleTest.class,
    ShortRuleTest.class,
    StringRuleTest.class,
    RefRuleTest.class
})
public class ColumnRuleSuiteTests {

}
