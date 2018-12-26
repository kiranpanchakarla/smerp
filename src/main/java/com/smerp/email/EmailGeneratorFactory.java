package com.smerp.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailGeneratorFactory {
	
	@Autowired
	RegistrationEmail registrationEmail;
	
	@Autowired
	PurchaseRequestEmail purchaseRequestEmail;
	
	
	public static final String REGISTRATION_EMAIL = "REGISTRATION_EMAIL";
	public static final String PURCHASEREQUEST_EMAIL = "PURCHASEREQUEST_EMAIL";
	public static final String REQUESTFORQUOTATION_EMAIL = "REQUESTFORQUOTATION_EMAIL";

	public Emailer get(String identifier) {
		if (identifier.equalsIgnoreCase(REGISTRATION_EMAIL)) {
			return registrationEmail;
		}
		 else if (identifier.equalsIgnoreCase(PURCHASEREQUEST_EMAIL)) {
				return purchaseRequestEmail;
			} 
		 else {
				return null;
			}
	}
	
}