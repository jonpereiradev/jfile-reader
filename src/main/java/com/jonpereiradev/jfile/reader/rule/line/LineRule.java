package com.jonpereiradev.jfile.reader.rule.line;

import com.jonpereiradev.jfile.reader.file.JFileLine;
import com.jonpereiradev.jfile.reader.rule.GenericRule;
import org.apache.commons.lang3.StringUtils;

public interface LineRule extends GenericRule<JFileLine> {

    @Override
    default boolean canValidate(JFileLine fileLine) {
        return StringUtils.isNotBlank(fileLine.getContent());
    }

}
