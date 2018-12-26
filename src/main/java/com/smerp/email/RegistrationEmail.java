package com.smerp.email;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.smerp.model.inventory.RequestForQuotation;
import com.smerp.model.purchase.PurchaseRequest;

@Component
public class RegistrationEmail extends EmailerGenerator {
	
	private static final Logger logger = LogManager.getLogger(EmailerGenerator.class);

	@Override
	protected MimeMessagePreparator createMessage(String mailTo) {
		return new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				InternetAddress[] myBccList = InternetAddress.parse(getDefaultBccEmailFromAddress());
				mimeMessage.addRecipients(Message.RecipientType.BCC, myBccList);
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				message.setFrom(getDefaultEmailFromAddress());
				message.setTo(mailTo.split(","));
				message.setSubject(getSubject());
				message.setText(getBody(), true);
			}

		
		};
	}

	protected String getSubject() {
		return "Welcome to " + getBrandName() + " !!";
	}



	protected String getBody() {
		Writer out = new StringWriter();
		try {
			Map<String, Object> input = new HashMap<String, Object>(1);
			input.put("brandName", getBrandName());
			input.put("domain", getDomain());
		//	input.put("user", getUser());
		//	input.put("contextPath", RequestContext.get().getContextPath());
			getTemplate().process(input, out);
			out.flush();
		} catch (Exception e) {
			logger.error("Failed to process exception email template for Registration email ", e);
			throw new RuntimeException(e);
		}
		return out.toString();
	}

	@Override
	protected MimeMessagePreparator createPRMessage(PurchaseRequest purchaseRequest) {
		// TODO Auto-generated method stub
		return null;
	}

 

	@Override
	protected MimeMessagePreparator createRFQMessage(RequestForQuotation requestForQuotation) {
		// TODO Auto-generated method stub
		return null;
	}


	/*private User getUser() {
		return RequestContext.get().getUser();
	}*/

	  

}
