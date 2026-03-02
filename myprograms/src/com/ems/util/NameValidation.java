package com.ems.util;

public class NameValidation {

    private static final String NAME_REGEX = "[A-Za-z\\s]*";

    public static boolean isValid(String empName) {
        return empName != null && empName.matches(NAME_REGEX);
    }
}