package com.ems.util;

public class IdValidation {

    private static final String ID_REGEX = "^TEK\\d+$";

    public static boolean isValid(String empId) {
        return empId != null && empId.matches(ID_REGEX);
    }
}