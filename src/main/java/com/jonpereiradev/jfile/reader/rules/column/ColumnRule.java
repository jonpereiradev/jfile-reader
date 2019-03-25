package com.jonpereiradev.jfile.reader.rules.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;
import com.jonpereiradev.jfile.reader.rules.GenericRule;
import org.apache.commons.lang3.StringUtils;

public interface ColumnRule extends GenericRule<JFileColumn> {

    @Override
    boolean isValid(JFileColumn fileColumn);

    @Override
    default boolean canValidate(JFileColumn fileColumn) {
        return StringUtils.isNotBlank(fileColumn.getText());
    }

    int getPosition();

}
