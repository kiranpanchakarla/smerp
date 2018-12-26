package com.smerp.email;

import com.smerp.model.purchase.PurchaseRequest;

public interface Emailer {

	public void sendMail(String mailTo) throws Exception;
	
	public void sendPR(PurchaseRequest purchaseRequest) throws Exception;
	
	
}
