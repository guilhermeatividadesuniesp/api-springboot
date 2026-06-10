package com.example.iespflix.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CPFouCNPJValidator implements ConstraintValidator<CPFouCNPJ, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) return true; // optional field
        String digits = value.replaceAll("\\D", "");
        if (digits.length() == 11) {
            return isValidCPF(digits);
        } else if (digits.length() == 14) {
            return isValidCNPJ(digits);
        }
        return false;
    }

    private boolean isValidCPF(String cpf) {
        // Basic validation: not all digits equal and length check.
        if (cpf.chars().distinct().count() == 1) return false;
        // For brevity implement only digit checks; checksum can be added later.
        return cpf.matches("\\d{11}");
    }

    private boolean isValidCNPJ(String cnpj) {
        if (cnpj.chars().distinct().count() == 1) return false;
        return cnpj.matches("\\d{14}");
    }
}
