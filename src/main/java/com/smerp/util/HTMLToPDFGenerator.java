package com.smerp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HTMLToPDFGenerator {
	
	

	@Autowired
	HTMLSummaryToPDF hTMLSummaryToPDF;
	
	public static final String HTML_PDF_Offline = "HTML_PDF_offline";
	
	
	
	public HTMLSummaryToPDF getOfflineSummaryToPDF(String identifier) {
		if (identifier.equalsIgnoreCase(HTML_PDF_Offline)) {
			return hTMLSummaryToPDF;
		}else {
			return null;
		}
	}
	
	
}
