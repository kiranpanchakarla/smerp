package com.smerp.service.emailids;

import java.util.List;

import javax.validation.constraints.Email;

 

public interface EmailIdService {

	String getToEmailIds(String moduleName, String operation);
	
	String getCCEmailIds(String moduleName, String operation);
	
	String getToYMLEmailIds(String moduleName, String operation);
	
	/*String getBCCEmailIds(String moduleName, String operation);*/
}
