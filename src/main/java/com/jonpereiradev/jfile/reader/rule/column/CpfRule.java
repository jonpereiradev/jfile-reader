package com.jonpereiradev.jfile.reader.rule.column;

import com.jonpereiradev.jfile.reader.file.JFileColumn;

import java.util.Arrays;
import java.util.List;

public class CpfRule extends AbstractColumnRule {

    private static final List<String> INVALID_CPFS = Arrays.asList(
        "00000000000",
        "11111111111",
        "22222222222",
        "33333333333",
        "44444444444",
        "55555555555",
        "66666666666",
        "77777777777",
        "88888888888",
        "99999999999"
    );

    public CpfRule(int position) {
        super(position);
    }

    @Override
    public boolean isValid(JFileColumn fileColumn) {
        String cpf = fileColumn.getText();

        if (cpf.length() != 11 || INVALID_CPFS.contains(cpf)) {
            return false;
        }

        int i;
        int j;
        int digit;
        int coeficient;
        int sum;
        int[] foundDv = {0, 0};

        int dv1 = Integer.parseInt(String.valueOf(cpf.charAt(cpf.length() - 2)));
        int dv2 = Integer.parseInt(String.valueOf(cpf.charAt(cpf.length() - 1)));

        for (j = 0; j < 2; j++) {
            sum = 0;
            coeficient = 2;

            for (i = cpf.length() - 3 + j; i >= 0; i--) {
                digit = Integer.parseInt(String.valueOf(cpf.charAt(i)));
                sum += digit * coeficient;
                coeficient++;
            }

            foundDv[j] = 11 - sum % 11;

            if (foundDv[j] >= 10) {
                foundDv[j] = 0;
            }
        }

        return dv1 == foundDv[0] && dv2 == foundDv[1];
    }
}
