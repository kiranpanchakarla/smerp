package com.smerp.util;

import java.util.HashMap;
import java.util.Map;

public class Context {
	
	private Map<String, String> configMap = new HashMap<String, String>();
	
	private String tokenId;
	
	private String message;
	
	
	
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public Map<String, String> getConfigMap() {
		return configMap;
	}

	public void setConfigMap(Map<String, String> configMap) {
		this.configMap = configMap;
	}

	@Override
	public String toString() {
		return "Context [configMap=" + configMap + ", tokenId=" + tokenId + ", message=" + message + "]";
	}

	
	
	

}
