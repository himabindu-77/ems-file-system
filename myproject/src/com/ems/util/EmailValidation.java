package com.ems.util;

public class EmailValidation {

	private static final String EMAIL_REGEX = "^[A-Za-z0-9._-]+@[A-Za-z]+\\.[A-Za-z.]{2,7}$";

	public static boolean isValid(String empEmail) {
		return empEmail != null && empEmail.matches(EMAIL_REGEX);
	}
}