package com.ead.authuser.validations;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordConstraintImpl implements ConstraintValidator<PasswordConstraint, String> {

    private static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()\\[\\]{}:;',?/*~$^+=<>-]).{6,20}$";

    @Override
    public void initialize(PasswordConstraint constraint) {
        ConstraintValidator.super.initialize(constraint);
    } 

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        if(password == null || password.trim().isEmpty() || password.contains(" ")) {
            return false;
        } else if(!Pattern.matches(PASSWORD_PATTERN, password)) {
            return false;
        }
        return true;
    }

}
