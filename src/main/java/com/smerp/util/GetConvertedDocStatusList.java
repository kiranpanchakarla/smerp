package com.smerp.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class GetConvertedDocStatusList {
	
	public List<String> getPRStatusList(){
		List<String> prStatusList = new ArrayList<String>();
		prStatusList.add(EnumStatusUpdate.APPROVEED.getStatus());
		return prStatusList;
	}
	
	public List<String> getRFQStatusList(){
		List<String> rfqStatusList = new ArrayList<String>();
		rfqStatusList.add(EnumStatusUpdate.APPROVEED.getStatus());
		return rfqStatusList;
	}
	
	public List<String> getPOStatusList(){
		List<String> poStatusList = new ArrayList<String>();
		poStatusList.add(EnumStatusUpdate.APPROVEED.getStatus());
		poStatusList.add(EnumStatusUpdate.PARTIALLY_RECEIVED.getStatus());
		return poStatusList;
	}
	
	public List<String> getGRStatusList(){
		List<String> grStatusList = new ArrayList<String>();
		grStatusList.add(EnumStatusUpdate.APPROVEED.getStatus());
		grStatusList.add(EnumStatusUpdate.PARTIALLY_RETURNED.getStatus());
		/*grStatusList.add(EnumStatusUpdate.GOODS_RETURN.getStatus());*/
		return grStatusList;
	}
	
	public List<String> getINVStatusList(){
		List<String> invStatusList = new ArrayList<String>();
		invStatusList.add(EnumStatusUpdate.APPROVEED.getStatus());
		return invStatusList;
	}
}
