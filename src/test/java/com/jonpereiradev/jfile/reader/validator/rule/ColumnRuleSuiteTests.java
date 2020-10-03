package com.jonpereiradev.jfile.reader.validator.rule;

import com.jonpereiradev.jfile.reader.validator.rule.column.*;
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
