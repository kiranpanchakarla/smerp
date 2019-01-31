package com.smerp.util;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.smerp.controller.purchase.GoodsReceiptController;
import com.smerp.controller.purchase.GoodsReturnController;
import com.smerp.controller.purchase.PurchaseOrderController;
import com.smerp.controller.purchase.PurchaseRequestController;
import com.smerp.controller.purchase.RequestForQuotationController;
import com.smerp.email.EmailerGenerator;
import com.smerp.model.admin.User;
import com.smerp.model.inventory.GoodsReceipt;
import com.smerp.model.inventory.GoodsReturn;
import com.smerp.model.inventory.InVoice;
import com.smerp.model.inventory.PurchaseOrder;
import com.smerp.model.inventory.RequestForQuotation;
import com.smerp.model.purchase.PurchaseRequest;
import com.smerp.service.admin.DashboardCountService;

@Component
public class SendEmail extends EmailerGenerator{

	@Autowired
	EmailGenerator emailGenerator;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	PurchaseRequestController purchaseRequestController;
	
	@Autowired
	RequestForQuotationController rfqController;

	@Autowired
	PurchaseOrderController purchaseOrderController;

	@Autowired
	GoodsReceiptController goodsReceiptController;
	
	@Autowired
	GoodsReturnController goodsReturnController;
	
	@Autowired
	DashboardCountService dashboardCountService;
	
	private static final Logger logger = LogManager.getLogger(SendEmail.class);
	
	PurchaseRequest pr;
	RequestForQuotation rfq;
	PurchaseOrder po;
	GoodsReceipt goodsRec;
	GoodsReturn goodsRet;
	InVoice inv;
	

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
				InternetAddress[] myBccList = InternetAddress.parse(getSUPPORT_CC_EMAIL());
				mimeMessage.addRecipients(Message.RecipientType.CC, myBccList);
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				pr = purchaseRequest;
				message.setFrom(getDefaultEmailFromAddress());
				message.setTo(getUser().getUserEmail());
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
			input.put("rfq", getRequestForQuotation());
			input.put("po", getPurchaseOrder());
			input.put("goodsRec", getGoodsRec());
			input.put("goodsRet", getGoodsRet());
			input.put("inv", getInvoice());
			input.put("plantMap", purchaseRequestController.plantMap());
			input.put("taxCodeMap", purchaseOrderController.taxCode());
			input.put("contextPath", RequestContext.get().getContextPath());
			getTemplate().process(input, out);
			out.flush();
		} catch (Exception e) {
			logger.error("Failed to process exception email template for email ", e);
			throw new RuntimeException(e);
		}
		return out.toString();
	}
	
	protected String getDashboardBody() {
		Writer out = new StringWriter();
		try {
			Map<String, Object> input = new HashMap<String, Object>(1);
			input.put("prCount", dashboardCountService.findAll());
			input.put("rfqCount", dashboardCountService.findRFQCount());
			input.put("poCount", dashboardCountService.findPOCount());
			input.put("grCount", dashboardCountService.findGoodsReceiptCount());
			getTemplate().process(input, out);
			out.flush();
		} catch (Exception e) {
			logger.error("Failed to process exception email template for Dashboard email ", e);
			throw new RuntimeException(e);
		}
		return out.toString();
	}
	
	protected String getsendMinQtyProductsEmailBody() {
		Writer out = new StringWriter();
		try {
			Map<String, Object> input = new HashMap<String, Object>(1);
			input.put("proCount", dashboardCountService.minProductQtyList());
			getTemplate().process(input, out);
			out.flush();
		} catch (Exception e) {
			logger.error("Failed to process exception email template for Minimum Quantity Products email ", e);
			throw new RuntimeException(e);
		}
		return out.toString();
	}
	
	protected String RejectMessage() {
		  
		return "Document has Been Rejected";
	}

	private User getUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public PurchaseRequest getPurchaseRequest() {
		return pr;
	}

	public RequestForQuotation getRequestForQuotation() {
		return rfq;
	}
	
	public PurchaseOrder getPurchaseOrder() {
		return po;
	}
	
	public GoodsReceipt getGoodsRec() {
		return goodsRec;
	}
	public GoodsReturn getGoodsRet() {
		return goodsRet;
	}
	
	public InVoice getInvoice() {
		return inv;
	}
	
	@Override
	protected MimeMessagePreparator createMessage(String mailTo) {
		// TODO Auto-generated method stub
		return null;
	}


	public void sendRFQEmail(RequestForQuotation requestForQuotation) throws Exception {
		 if (shouldNotify()) {
	            logger.info("Sending notification about " + getUser().getUserEmail() + " ...");
	            try {
	                mailSender.send(createRFQMessage(requestForQuotation));
	               // logger.info("Email notification successfully sent for " + mailTo);
	              //  doPRPostProcessing();
	            } catch (Exception e) {
	                logger.error("Error in sending email", e);
	                throw new Exception(e);
	            }
	        }
	}
	 
	protected MimeMessagePreparator createRFQMessage(RequestForQuotation requestForQuotation) {
		return new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				InternetAddress[] myBccList = InternetAddress.parse(getSUPPORT_CC_EMAIL());
				mimeMessage.addRecipients(Message.RecipientType.CC, myBccList);
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				rfq = requestForQuotation;
				message.setFrom(getDefaultEmailFromAddress());
				message.setTo(getUser().getUserEmail());
				message.setSubject("RequestForQuotation :" + rfq.getDocNumber() + " Status :" + rfq.getStatus());
				message.setText(getBody(), true);
			}

		};
	}
	
	public void sendPOEmail(PurchaseOrder purchaseOrder) throws Exception {
		 if (shouldNotify()) {
	            logger.info("Sending notification for " + "k.panchakarla@manuhindia.com" + " ...");
	            try {
	                mailSender.send(createPOMessage(purchaseOrder));
	               // logger.info("Email notification successfully sent for " + mailTo);
	              //  doPRPostProcessing();
	            } catch (Exception e) {
	                logger.error("Error in sending email", e);
	                throw new Exception(e);
	            }
	        }
	}
	
	protected MimeMessagePreparator createPOMessage(PurchaseOrder purchaseOrder) {
		return new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				InternetAddress[] myBccList = InternetAddress.parse(getSUPPORT_CC_EMAIL());
				mimeMessage.addRecipients(Message.RecipientType.CC, myBccList);
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				po = purchaseOrder;
				message.setFrom(getDefaultEmailFromAddress());
				message.setTo(getUser().getUserEmail());
				message.setSubject("Purchase Order :" + po.getDocNumber() + " Status :" + po.getStatus());
				message.setText(getBody(), true);
			}

		};
	}
	
	public void sendGoodsReceiptEmail(GoodsReceipt goodsReceipt) throws Exception {
		 if (shouldNotify()) {
	            logger.info("Sending notification for " + "k.panchakarla@manuhindia.com" + " ...");
	            try {
	                mailSender.send(createGoodsReceiptMessage(goodsReceipt));
	               // logger.info("Email notification successfully sent for " + mailTo);
	              //  doPRPostProcessing();
	            } catch (Exception e) {
	                logger.error("Error in sending email", e);
	                throw new Exception(e);
	            }
	        }
	}
	
	protected MimeMessagePreparator createGoodsReceiptMessage(GoodsReceipt goodsReceipt) {
		return new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				InternetAddress[] myBccList = InternetAddress.parse(getSUPPORT_CC_EMAIL());
				mimeMessage.addRecipients(Message.RecipientType.CC, myBccList);
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				goodsRec = goodsReceipt;
				message.setFrom(getDefaultEmailFromAddress());
				message.setTo(getUser().getUserEmail());
				message.setSubject("GoodsReceipt :" + goodsRec.getDocNumber() + " Status :" + goodsRec.getStatus());
				message.setText(getBody(), true);
			}

		};
	}
	
	public void sendGoodsReturnEmail(GoodsReturn goodsReturn) throws Exception {
		 if (shouldNotify()) {
	            logger.info("Sending notification for " + "k.panchakarla@manuhindia.com" + " ...");
	            try {
	                mailSender.send(createGoodsReturnMessage(goodsReturn));
	               // logger.info("Email notification successfully sent for " + mailTo);
	              //  doPRPostProcessing();
	            } catch (Exception e) {
	                logger.error("Error in sending email", e);
	                throw new Exception(e);
	            }
	        }
	}
	
	protected MimeMessagePreparator createGoodsReturnMessage(GoodsReturn goodsReturn) {
		return new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				InternetAddress[] myBccList = InternetAddress.parse(getSUPPORT_CC_EMAIL());
				mimeMessage.addRecipients(Message.RecipientType.CC, myBccList);
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				goodsRet = goodsReturn;
				message.setFrom(getDefaultEmailFromAddress());
				message.setTo(getUser().getUserEmail());
				message.setSubject("GoodsReturn :" + goodsRet.getDocNumber() + " Status :" + goodsRet.getStatus());
				message.setText(getBody(), true);
			}

		};
	}
	
	public void sendGoodsReturnRejectEmail(GoodsReturn goodsReturn) throws Exception {
		 if (shouldNotify()) {
	            logger.info("Sending notification for " + "k.panchakarla@manuhindia.com" + " ...");
	            try {
	                mailSender.send(createGoodsReturnRejectMessage(goodsReturn));
	               // logger.info("Email notification successfully sent for " + mailTo);
	              //  doPRPostProcessing();
	            } catch (Exception e) {
	                logger.error("Error in sending email", e);
	                throw new Exception(e);
	            }
	        }
	}
	
	public void sendInvoiceEmail(InVoice invoice) throws Exception {
		 if (shouldNotify()) {
	            logger.info("Sending notification for " + "k.panchakarla@manuhindia.com" + " ...");
	            try {
	                mailSender.send(createInvoiceMessage(invoice));
	               // logger.info("Email notification successfully sent for " + mailTo);
	              //  doPRPostProcessing();
	            } catch (Exception e) {
	                logger.error("Error in sending email", e);
	                throw new Exception(e);
	            }
	        }
	}
	
	protected MimeMessagePreparator createInvoiceMessage(InVoice invoice) {
		return new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				InternetAddress[] myBccList = InternetAddress.parse(getDefaultBccEmailFromAddress());
				mimeMessage.addRecipients(Message.RecipientType.CC, myBccList);
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				inv = invoice;
				message.setFrom(getDefaultEmailFromAddress());
				message.setTo(getUser().getUserEmail());
				message.setSubject("Invoice : " + inv.getDocNumber() + "  Status :" + inv.getStatus());
				message.setText(getBody(), true);
			}

		};
	}
	
	protected MimeMessagePreparator createGoodsReturnRejectMessage(GoodsReturn goodsReturn) {
		return new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				InternetAddress[] myBccList = InternetAddress.parse(getSUPPORT_CC_EMAIL());
				mimeMessage.addRecipients(Message.RecipientType.CC, myBccList);
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				goodsRet = goodsReturn;
				message.setFrom(getDefaultEmailFromAddress());
				message.setTo(getUser().getUserEmail());
				message.setSubject("GoodsReturn :" + goodsRet.getDocNumber() + " Status :" + goodsRet.getStatus());
				message.setText(goodsRet.getDocNumber() + " " + RejectMessage(), true);
			}

		};
	}

	public void sendsendMinQtyProductsEmailEmail() throws Exception {
		 if (shouldNotify()) {
			 
	            try {
	                mailSender.send(createsendMinQtyProductsEmailMessage());
	                
	            } catch (Exception e) {
	                logger.error("Error in sending email", e);
	                throw new Exception(e);
	            }
	        }
	}
	
	protected MimeMessagePreparator createsendMinQtyProductsEmailMessage() {
		return new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				InternetAddress[] myBccList = InternetAddress.parse(getDefaultBccEmailFromAddress());
				mimeMessage.addRecipients(Message.RecipientType.CC, myBccList);
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				message.setFrom(getDefaultEmailFromAddress());
				message.setTo(getSEND_EMAIL());
				message.setSubject("Products With Minimum Quantity");
				message.setText(getsendMinQtyProductsEmailBody(), true);
				
			}

		};
	}
	
	public void sendDashboardEmail() throws Exception {
		 if (shouldNotify()) {
			 
	            try {
	                mailSender.send(createDashboardMessage());
	                
	            } catch (Exception e) {
	                logger.error("Error in sending email", e);
	                throw new Exception(e);
	            }
	        }
	}
	
	protected MimeMessagePreparator createDashboardMessage() {
		return new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				InternetAddress[] myBccList = InternetAddress.parse(getDefaultBccEmailFromAddress());
				mimeMessage.addRecipients(Message.RecipientType.CC, myBccList);
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				message.setFrom(getDefaultEmailFromAddress());
				message.setTo(getSEND_EMAIL());
				message.setSubject("Dashboard Email");
				message.setText(getDashboardBody(), true);
			}

		};
	}
}
