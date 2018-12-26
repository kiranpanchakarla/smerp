package com.smerp.email;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.smerp.model.inventory.RequestForQuotation;
import com.smerp.model.purchase.PurchaseRequest;
import com.smerp.util.RequestContext;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Component
public abstract class EmailerGenerator implements Emailer {
	
	private static final Logger logger = LogManager.getLogger(EmailerGenerator.class);

	@Value(value = "${domain}")
	private static String domain;

	@Value(value = "${support.email}")
	private String SUPPORT_EMAIL;

	@Value("#{'${support.email.bcc}'.split(',')}")
	private String SUPPORT_BCC_EMAIL;
	
	private  String bccMail;
	
	private  String ccMail;
	
	@Value(value = "${brandName}")
	private static String brandName;
	
	
	

	public String getBccMail() {
		return bccMail;
	}

	public String getSUPPORT_EMAIL() {
		return SUPPORT_EMAIL;
	}

	public String getDefaultEmailFromAddress() {
        return SUPPORT_EMAIL;
    }
    
    public String getDefaultBccEmailFromAddress() {
        return SUPPORT_BCC_EMAIL;
    }
    
	public void setSUPPORT_BCC_EMAIL(String sUPPORT_BCC_EMAIL) {
		SUPPORT_BCC_EMAIL = sUPPORT_BCC_EMAIL;
	}

	public void setBccMail(String bccMail) {
		this.bccMail = bccMail;
	}

	public String getCcMail() {
		return ccMail;
	}

	public void setCcMail(String ccMail) {
		this.ccMail = ccMail;
	}

	public static String getBrandName() {
		return brandName;
	}

	public static void setBrandName(String brandName) {
		EmailerGenerator.brandName = brandName;
	}

	public static String getDomain() {
		return domain;
	}

	@Value(value = "${domain}")
	public void setDomain(String domain) {
		EmailerGenerator.domain = domain;
	}

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	@Qualifier("fmConfig")
	private Configuration fmConfig;

	public Template getTemplate() throws IOException {
		return fmConfig.getTemplate(RequestContext.get().getConfigMap().get("mail.template"));
	}

	@Override
	public void sendMail(String mailTo) throws Exception {
		 if (shouldNotify()) {
	            logger.info("Sending notification for " + mailTo + " ...");
	            try {
	                mailSender.send(createMessage(mailTo));
	                logger.info("Email notification successfully sent for " + mailTo);
	                doPRPostProcessing();
	            } catch (Exception e) {
	                logger.error("Error in sending email", e);
	                throw new Exception(e);
	            }
	        }
	}
	
	@Override
	public void sendPR(PurchaseRequest purchaseRequest) throws Exception {
		 if (shouldNotify()) {
	            logger.info("Sending notification for " + purchaseRequest.getReferenceUser().getUserEmail() + " ...");
	            try {
	                mailSender.send(createPRMessage(purchaseRequest));
	               // logger.info("Email notification successfully sent for " + mailTo);
	                doPRPostProcessing();
	            } catch (Exception e) {
	                logger.error("Error in sending email", e);
	                throw new Exception(e);
	            }
	        }
	}
	  
	

	protected abstract MimeMessagePreparator createPRMessage(PurchaseRequest purchaseRequest);
	
	   protected void doPRPostProcessing() {
	        // Hook to allow any post processing stuff
	    }
	   
	   protected abstract MimeMessagePreparator createRFQMessage(RequestForQuotation requestForQuotation);
		
	   protected void doRFQPostProcessing() {
	        // Hook to allow any post processing stuff
	    }
	
	 protected abstract MimeMessagePreparator createMessage(String mailTo);

	    protected boolean shouldNotify() {
	        return true;
	    }

}
