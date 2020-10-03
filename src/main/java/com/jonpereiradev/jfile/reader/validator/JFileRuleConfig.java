package com.jonpereiradev.jfile.reader.validator;

import com.jonpereiradev.jfile.reader.validator.rule.configurator.ColumnRuleConfigurator;
import com.jonpereiradev.jfile.reader.validator.rule.configurator.FileRuleConfigurator;
import com.jonpereiradev.jfile.reader.validator.rule.configurator.LineRuleConfigurator;

public interface JFileRuleConfig {

    FileRuleConfigurator files();

    LineRuleConfigurator lines();

    ColumnRuleConfigurator columns();

}
