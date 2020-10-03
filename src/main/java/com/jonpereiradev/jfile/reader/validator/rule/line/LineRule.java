package com.jonpereiradev.jfile.reader.validator.rule.line;

import com.jonpereiradev.jfile.reader.file.LineValue;
import com.jonpereiradev.jfile.reader.validator.rule.Rule;
import com.jonpereiradev.jfile.reader.validator.rule.RuleUtils;

public interface LineRule extends Rule<LineValue> {

    @Override
    default boolean canValidate(LineValue lineValue) {
        return RuleUtils.isNotBlank(lineValue.getContent());
    }

}
