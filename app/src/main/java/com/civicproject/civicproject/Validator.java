package com.civicproject.civicproject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    //private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
    private static final String PASSWORD_PATTERN = "((?=.*\\d).{6,20})";
    private static final String PHONENUMBER_PATTERN = "\\d{3}-\\d{3}-\\d{3}";

    public boolean emailValidator(String input) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public boolean passwordValidator(String input) {
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public boolean phoneNumberValidator(String input) {
        pattern = Pattern.compile(PHONENUMBER_PATTERN);
        matcher = pattern.matcher(input);
        return matcher.matches();
    }
}

