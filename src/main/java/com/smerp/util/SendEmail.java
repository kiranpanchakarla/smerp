package com.smerp.util;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.smerp.email.EmailerGenerator;
import com.smerp.model.admin.Plant;
import com.smerp.model.admin.User;
import com.smerp.model.purchase.PurchaseRequest;
import com.smerp.service.master.PlantService;

@Component
public class SendEmail extends EmailerGenerator{

	@Autowired
	EmailGenerator emailGenerator;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	PlantService plantService;
	
	private static final Logger logger = LogManager.getLogger(SendEmail.class);
	
	PurchaseRequest pr;
	
	public void sendPREmail(PurchaseRequest purchaseRequest) throws Exception {
		 if (shouldNotify()) {
	            logger.info("Sending notification for " + purchaseRequest.getReferenceUser().getUserEmail() + " ...");
	            try {
	                mailSender.send(createPRMessage(purchaseRequest));
	               // logger.info("Email notification successfully sent for " + mailTo);
	              //  doPRPostProcessing();
	            } catch (Exception e) {
	                logger.error("Error in sending email", e);
	                throw new Exception(e);
	            }
	        }
	}
	
	protected MimeMessagePreparator createPRMessage(PurchaseRequest purchaseRequest) {
		return new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				InternetAddress[] myBccList = InternetAddress.parse(getDefaultBccEmailFromAddress());
				mimeMessage.addRecipients(Message.RecipientType.BCC, myBccList);
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				pr = purchaseRequest;
				message.setFrom(getDefaultEmailFromAddress());
				message.setTo("k.panchakarla@manuhindia.com");
				message.setSubject("PurchaseRequest :" + pr.getDocNumber() + " Status :" + pr.getStatus());
				message.setText(getBody(), true);
			}

		};
	}
	
	 

	protected String getBody() {
		Writer out = new StringWriter();
		try {
			Map<String, Object> input = new HashMap<String, Object>(1);
			input.put("user", getUser());
			input.put("pr", getPurchaseRequest());
			input.put("plantMap", plantMap());
			input.put("contextPath", RequestContext.get().getContextPath());
			getTemplate().process(input, out);
			out.flush();
		} catch (Exception e) {
			logger.error("Failed to process exception email template for Registration email ", e);
			throw new RuntimeException(e);
		}
		return out.toString();
	}

	private User getUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public PurchaseRequest getPurchaseRequest() {
		return pr;
	}

	public Map<Integer, Object> plantMap() {
		return plantService.findAll().stream().collect(Collectors.toMap(Plant::getId, Plant::getPlantName));

	}

	@Override
	protected MimeMessagePreparator createMessage(String mailTo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	 

}
