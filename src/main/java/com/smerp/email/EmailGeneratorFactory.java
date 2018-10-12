package com.smerp.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailGeneratorFactory {
	
	@Autowired
	RegistrationEmail registrationEmail;
	
	public static final String REGISTRATION_EMAIL = "REGISTRATION_EMAIL";

	public Emailer get(String identifier) {
		if (identifier.equalsIgnoreCase(REGISTRATION_EMAIL)) {
			return registrationEmail;
		}
		return registrationEmail;
	}
	
}
