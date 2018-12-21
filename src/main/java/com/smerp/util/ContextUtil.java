package com.smerp.util;

import javax.servlet.http.HttpServletRequest;
public class ContextUtil {
	
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
	        System.out.println(url.toString());
	        
	      return url.toString();
	        
	    }
	  
	  
	  public static  Context populateContexturl(HttpServletRequest req) {
			Context ctx = new Context();
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
			ctx.setContextPath(url.toString());
			return ctx;
		}

}
