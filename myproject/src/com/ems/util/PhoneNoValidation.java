package com.ems.util;


public class PhoneNoValidation {

    private static final String PHONE_REGEX = "^[6-9][0-9]{9}$";
//	String regex = "^(\\+91\\s?)?[6-9][0-9]{9}$";

    	public static boolean isValid(String empPhoneNumber) {
		return empPhoneNumber != null && empPhoneNumber.matches(PHONE_REGEX);
		
	}

		}