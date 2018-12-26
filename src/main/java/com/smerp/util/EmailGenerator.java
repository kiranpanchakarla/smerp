package com.smerp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class EmailGenerator {

	@Autowired
	SendEmail sendEmail;
	
	public static final String Sending_Email = "Sending_Email";
	
	public SendEmail sendEmailToUser(String identifier) {
		if (identifier.equalsIgnoreCase(Sending_Email)) {
			return sendEmail;
		}else {
			return null;
		}
	}
}
