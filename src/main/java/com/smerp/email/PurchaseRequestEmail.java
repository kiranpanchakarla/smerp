package com.smerp.email;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.smerp.model.admin.Plant;
import com.smerp.model.admin.User;
import com.smerp.model.inventory.RequestForQuotation;
import com.smerp.model.purchase.PurchaseRequest;
import com.smerp.service.master.PlantService;
import com.smerp.util.RequestContext;

@Component
public class PurchaseRequestEmail extends EmailerGenerator {

	@Autowired
	PlantService plantService;

	@PersistenceContext
	private EntityManager entityManager;

	private static final Logger logger = LogManager.getLogger(EmailerGenerator.class);

	PurchaseRequest pr;

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

	@Override
	protected MimeMessagePreparator createPRMessage(PurchaseRequest purchaseRequest) {
		return new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				InternetAddress[] myBccList = InternetAddress.parse(getDefaultBccEmailFromAddress());
				mimeMessage.addRecipients(Message.RecipientType.BCC, myBccList);
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				pr = purchaseRequest;
				message.setFrom(getDefaultEmailFromAddress());
				message.setTo("k.panchakarla@manuhindia.com");
				message.setSubject(getSubject());
				message.setText(getBody(), true);
				// message.setText("Ganapathi Test", htmlDiv);
			}

		};
	}

	protected String getSubject() {
		return "PurchaseRequest :" + pr.getDocNumber() + " Status :" + pr.getStatus();
	}

	protected String getBody() {
		Writer out = new StringWriter();
		try {
			Map<String, Object> input = new HashMap<String, Object>(1);
			// input.put("brandName", getBrandName());
			// input.put("domain", getDomain());
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
	protected MimeMessagePreparator createRFQMessage(RequestForQuotation requestForQuotation) {
		// TODO Auto-generated method stub
		return null;
	}

}
