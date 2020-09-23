package com.jonpereiradev.jfile.reader.rule.line;

import com.jonpereiradev.jfile.reader.file.JFileLine;
import com.jonpereiradev.jfile.reader.rule.Rule;
import com.jonpereiradev.jfile.reader.rule.RuleUtils;

public interface LineRule extends Rule<JFileLine> {

    @Override
    default boolean canValidate(JFileLine fileLine) {
        return RuleUtils.isNotBlank(fileLine.getContent());
    }

}
