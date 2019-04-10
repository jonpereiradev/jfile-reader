package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

public class FilledRefRule extends AbstractRefRule {

    public FilledRefRule(int refPosition, int position) {
        super(refPosition, position);
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return new NotEmptyRule(fileColumn.getPosition()).isValid(fileColumn);
    }

}
