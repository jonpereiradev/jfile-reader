package com.jonpereiradev.jfile.reader.rule;

import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;

public final class RuleConfiguratorContext {

    private final ReaderConfiguration readerConfiguration;
    private final RuleConfiguration ruleConfiguration;

    RuleConfiguratorContext(ReaderConfiguration readerConfiguration, RuleConfiguration ruleConfiguration) {
        this.readerConfiguration = readerConfiguration;
        this.ruleConfiguration = ruleConfiguration;
    }

    public ReaderConfiguration getReaderConfiguration() {
        return readerConfiguration;
    }

    public RuleConfiguration getRuleConfiguration() {
        return ruleConfiguration;
    }
}
