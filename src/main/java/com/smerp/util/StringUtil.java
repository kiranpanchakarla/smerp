package com.smerp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);
	private static Matcher matcher = null;
	
	public static boolean isValidEmail(final String email) {
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean isEmptyTrim(String val) {
		return val == null ? true : isEmpty(val.trim());
	}

	private static boolean isEmpty(String val) {
		return val == null ? true : val.isEmpty();
	}

	public static String get(String str) {
		return isEmptyTrim(str) ? "" : str.trim();
	}
	
	public static char getChar(String str) {
		return str.charAt(0);
	}
	
	public static boolean isValid(String val) {
		return !isEmptyTrim(val.trim());
	}
	
	
}
