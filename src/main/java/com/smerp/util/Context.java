package com.smerp.util;

import java.util.HashMap;
import java.util.Map;

public class Context {
	
	private Map<String, String> configMap = new HashMap<String, String>();

	public Map<String, String> getConfigMap() {
		return configMap;
	}

	public void setConfigMap(Map<String, String> configMap) {
		this.configMap = configMap;
	}

	
	

}
