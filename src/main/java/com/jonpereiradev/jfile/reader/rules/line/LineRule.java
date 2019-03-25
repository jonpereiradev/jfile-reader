package com.jonpereiradev.jfile.reader.rules.line;

import com.jonpereiradev.jfile.reader.file.JFileLine;
import com.jonpereiradev.jfile.reader.rules.GenericRule;
import org.apache.commons.lang3.StringUtils;

public interface LineRule extends GenericRule<JFileLine> {

    @Override
    default boolean canValidate(JFileLine fileLine) {
        return StringUtils.isNotBlank(fileLine.getContent());
    }

}
