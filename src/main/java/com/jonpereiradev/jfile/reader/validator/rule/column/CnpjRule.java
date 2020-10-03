package com.jonpereiradev.jfile.reader.validator.rule.column;

import com.jonpereiradev.jfile.reader.file.ColumnValue;

import java.util.Arrays;
import java.util.List;

public class CnpjRule extends AbstractColumnRule {

    private static final List<String> INVALID_CNPJS = Arrays.asList(
        "00000000000000",
        "11111111111111",
        "22222222222222",
        "33333333333333",
        "44444444444444",
        "55555555555555",
        "66666666666666",
        "77777777777777",
        "88888888888888",
        "99999999999999"
    );

    public CnpjRule(int position) {
        super(position);
    }

    @Override
    public boolean isValid(ColumnValue fileColumn) {
        String cnpj = fileColumn.getText();

        if (cnpj.length() != 14 || INVALID_CNPJS.contains(cnpj)) {
            return false;
        }

        int i;
        int j;
        int digit;
        int coeficient;
        int sum;
        int[] foundDv = {0, 0};

        int dv1 = Integer.parseInt(String.valueOf(cnpj.charAt(cnpj.length() - 2)));
        int dv2 = Integer.parseInt(String.valueOf(cnpj.charAt(cnpj.length() - 1)));

        for (j = 0; j < 2; j++) {
            sum = 0;
            coeficient = 2;

            for (i = cnpj.length() - 3 + j; i >= 0; i--) {
                digit = Integer.parseInt(String.valueOf(cnpj.charAt(i)));
                sum += digit * coeficient;
                coeficient++;

                if (coeficient > 9) {
                    coeficient = 2;
                }
            }

            foundDv[j] = 11 - sum % 11;

            if (foundDv[j] >= 10) {
                foundDv[j] = 0;
            }
        }

        return dv1 == foundDv[0] && dv2 == foundDv[1];
    }
}
