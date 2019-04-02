package com.jonpereiradev.jfile.reader.rule;

import com.jonpereiradev.jfile.reader.rule.column.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    BigDecimalRuleConfigurationTest.class,
    BigIntegerRuleConfigurationTest.class,
    BooleanRuleConfigurationTest.class,
    CharacterRuleConfigurationTest.class,
    DateRuleConfigurationTest.class,
    DoubleRuleConfigurationTest.class,
    FloatRuleConfigurationTest.class,
    IntegerRuleConfigurationTest.class,
    LocalDateRuleConfigurationTest.class,
    LocalDateTimeRuleConfigurationTest.class,
    LongRuleConfigurationTest.class,
    ShortRuleConfigurationTest.class,
    StringRuleConfigurationTest.class
})
public class ColumnRuleConfigurationSuiteTests {
}
