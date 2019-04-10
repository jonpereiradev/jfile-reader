package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

public class EmptyRefRule extends AbstractRefRule {

    public EmptyRefRule(int refPosition, int position) {
        super(refPosition, position);
    }

    @Override
    public boolean canValidate(JFileColumn fileColumn) {
        return new OnlyNullRule(fileColumn.getPosition()).isValid(fileColumn);
    }

}
