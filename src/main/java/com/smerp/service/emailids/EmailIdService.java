package com.smerp.service.emailids;

import java.util.List;

 

public interface EmailIdService {

	String getToEmailIds(String moduleName, String operation);
	
	String getCCEmailIds(String moduleName, String operation);
	
	String getBCCEmailIds(String moduleName, String operation);
}
