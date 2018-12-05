package com.smerp.util;

public class EnumStatusUpdate {

	public enum StatusUpdate
	{
	    Open("Open Stage"),
	    Cancel("Cancel Stage"),
	    Draft("Draft Stage"),
	    Approve("Approved"),
	    Reject("Rejected");
	 
	    private String status;
	 
	    StatusUpdate(String status) {
	        this.status = status;
	    }
	 
	    public String getStatus() {
	        return status;
	    }
	}
}
