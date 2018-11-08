package com.smerp.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

private static final String DEFAULT_PASSWORD = "Welcome1";
	
	public static String encodePassword(String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		/*if(password == null || password.isEmpty()) {
			password = DEFAULT_PASSWORD;
		}*/
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		byte[] sha1hash = new byte[40];
		md.update(password.getBytes("iso-8859-1"), 0, password.length());
		sha1hash = md.digest();
		return convertToHex(sha1hash);
	}

	private static String convertToHex(byte[] data) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			if (i % 4 == 0 && i != 0)
				buffer.append("");
			int x = (int) data[i];
			if (x < 0)
				x += 256;
			if (x < 16)
				buffer.append("0");
			buffer.append(Integer.toString(x, 16));
		}
		return buffer.toString();
	}
}
