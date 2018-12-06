package com.smerp.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ContextUtil {
	
	private static final Logger logger = LogManager.getLogger(ContextUtil.class);
			
	  public static String  populateContext(HttpServletRequest req) {
	       // Context ctx = new Context();
	        StringBuffer url = new StringBuffer();
	        String scheme = req.getScheme();  // either http or https
	        int port = req.getServerPort();
	        String urlPath = req.getContextPath();
	        url.append(scheme);
	        url.append("://");
	        url.append(req.getServerName());
	        if ((scheme.equals("http") && port != 80)
	                || (scheme.equals("https") && port != 443)) {
	            url.append(':');
	            url.append(req.getServerPort());
	        }
	        url.append(urlPath);
	       // System.out.println(url.toString());
	        logger.info("URL--->" + url.toString());
	        
	      return url.toString();
	        
	    }

}
