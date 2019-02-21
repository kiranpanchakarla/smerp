package com.smerp.util;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.smerp.controller.inventorytransactions.InventoryGoodsIssueController;
import com.smerp.controller.purchase.GoodsReceiptController;
import com.smerp.controller.purchase.GoodsReturnController;
import com.smerp.controller.purchase.PurchaseOrderController;
import com.smerp.controller.purchase.PurchaseRequestController;
import com.smerp.controller.purchase.RequestForQuotationController;
import com.smerp.email.EmailerGenerator;
import com.smerp.model.admin.Department;
import com.smerp.model.admin.User;
import com.smerp.model.emailids.EmailId;
import com.smerp.model.inventory.CreditMemo;
import com.smerp.model.inventory.GoodsReceipt;
import com.smerp.model.inventory.GoodsReturn;
import com.smerp.model.inventory.InVoice;
import com.smerp.model.inventory.PurchaseOrder;
import com.smerp.model.inventory.RequestForQuotation;
import com.smerp.model.inventorytransactions.InventoryGoodsIssue;
import com.smerp.model.inventorytransactions.InventoryGoodsReceipt;
import com.smerp.model.inventorytransactions.InventoryGoodsTransfer;
import com.smerp.model.purchase.PurchaseRequest;
import com.smerp.repository.purchase.PurchaseRequestRepository;
import com.smerp.service.admin.DashboardCountService;
import com.smerp.service.admin.DepartmentService;
import com.smerp.service.emailids.EmailIdService;
import com.sun.jndi.cosnaming.IiopUrl.Address;

@Component
public class SendEmail extends EmailerGenerator{

	@Autowired
	EmailGenerator emailGenerator;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	PurchaseRequestController purchaseRequestController;
	
	@Autowired
	PurchaseRequestRepository purchaseRequestRepository;
	
	@Autowired
	RequestForQuotationController rfqController;

	@Autowired
	PurchaseOrderController purchaseOrderController;

	@Autowired
	GoodsReceiptController goodsReceiptController;
	
	@Autowired
	GoodsReturnController goodsReturnController;
	
	@Autowired
	DepartmentService departmentService;
	
	@Autowired
	DashboardCountService dashboardCountService;
	
	@Autowired
	EmailIdService emailIdService;
	
	private static final Logger logger = LogManager.getLogger(SendEmail.class);
	
	private PurchaseRequest pr;
	private RequestForQuotation rfq;
	private PurchaseOrder po;
	private GoodsReceipt goodsRec;
	private GoodsReturn goodsRet;
	private InVoice inv;
	private CreditMemo credit;
	private InventoryGoodsReceipt invgr;
	private InventoryGoodsIssue invgi;
	private InventoryGoodsTransfer invgt;
	
	private String toEmail = "";
	private String ccEmail = "";
	private String bccEmail = "";
	
	
	private static String environment;
	
	public static String getEnvironment() {
		return environment;
	}
	@Value(value = "${environment}")
	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public Map<Integer, Object> deptMap() {
		return departmentService.findAll().stream().collect(Collectors.toMap(Department::getId, Department::getName));
	}
	
	public void sendPREmail(PurchaseRequest purchaseRequest) throws Exception {
		
		/*PurchaseRequest purchaseRequestObj = purchaseRequestRepository.findById(purchaseRequest.getId()).get();
		logger.info(purchaseRequestObj.getCreatedBy().getUserEmail());*/
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
	
	public String getToEmails(String status,String operation,Integer id) {
		if(status != null && !status.isEmpty() && operation != null && !operation.isEmpty()) {
			
			
			if(operation.equals(EnumStatusUpdate.OPEN.getStatus())) {
				if(id == 1) {
					toEmail = emailIdService.getToEmailIds(status, operation);
					ccEmail = emailIdService.getCCEmailIds(status, operation);
				}
				if(id == 2) {
					toEmail = emailIdService.getToYMLEmailIds(status, operation);
					ccEmail = emailIdService.getCCEmailIds(status, operation);
				}
			}else {
				if(id == 1) {
					toEmail = emailIdService.getToEmailIds(status, operation);
					ccEmail = emailIdService.getCCEmailIds(status, operation);
					
				}
				if(id == 2) {
					toEmail = emailIdService.getToYMLEmailIds(status, operation);
					ccEmail = emailIdService.getCCEmailIds(status, operation);
				}
			}
		}
		return toEmail;
		
	}
	
	protected MimeMessagePreparator createPRMessage(PurchaseRequest purchaseRequest) {
		return new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				//InternetAddress[] myccList = InternetAddress.parse(getSUPPORT_CC_EMAIL());
				//mimeMessage.addRecipients(Message.RecipientType.CC, myBccList);
				/*if(purchaseRequest.getStatus().equals(EnumStatusUpdate.APPROVEED.getStatus()) || purchaseRequest.getStatus().equals(EnumStatusUpdate.REJECTED.getStatus())) {
					//ccEmail = emailIdService.getCCEmailIds(EnumStatusUpdate.PR.getStatus(), EnumStatusUpdate.APPROVAL.getStatus());
					toEmail = emailIdService.getToEmailIds(EnumStatusUpdate.PR.getStatus(), EnumStatusUpdate.APPROVAL.getStatus());
					
					//bccEmail = emailIdService.getBCCEmailIds(EnumStatusUpdate.PR.getStatus(), EnumStatusUpdate.APPROVAL.getStatus());
				}
				else if(purchaseRequest.getStatus().equals(EnumStatusUpdate.OPEN.getStatus())) {
					//ccEmail = emailIdService.getCCEmailIds(EnumStatusUpdate.PR.getStatus(), EnumStatusUpdate.OPEN.getStatus());
					toEmail = emailIdService.getToEmailIds(EnumStatusUpdate.PR.getStatus(), EnumStatusUpdate.OPEN.getStatus());
					//bccEmail = emailIdService.getBCCEmailIds(EnumStatusUpdate.PR.getStatus(), EnumStatusUpdate.OPEN.getStatus());
				}  */
				//mimeMessage.addRecipients(Message.RecipientType.TO, toEmail);
				//mimeMessage.addRecipients(Message.RecipientType.CC, ccEmail);
				//mimeMessage.addRecipients(Message.RecipientType.BCC, bccEmail);
				//String SendTo = getUser().getUserEmail() + "," + toEmail;
				
				Integer id = purchaseRequest.getPurchaseRequestLists().get(0).getWarehouse();
				
				if(purchaseRequest.getStatus().equals(EnumStatusUpdate.OPEN.getStatus()))
				{
					getToEmails(EnumStatusUpdate.PR.getStatus(),EnumStatusUpdate.OPEN.getStatus(),id);
				}else {
					getToEmails(EnumStatusUpdate.PR.getStatus(),EnumStatusUpdate.APPROVAL.getStatus(),id);
					
				}
				 
				 
				if(toEmail != null) {
					toEmail += "," + getUser().getUserEmail() /*+ ","+ purchaseRequest.getCreatedBy().getUserEmail()*/ ;
				}else {
					toEmail =  getUser().getUserEmail();
					
				}
				
				String[] recipientList = toEmail.split(",");
				logger.info("Sending purchaseRequest Email Log ---> ");
				logger.info("purchaseRequest ID ---> "+ purchaseRequest.getId());
				logger.info("purchaseRequest Doc Number ---> "+ purchaseRequest.getDocNumber() );
				logger.info("purchaseRequest Status ---> " + purchaseRequest.getStatus());
				logger.info("Email Send To ---> "  +  toEmail);
				logger.info("Email Send To ---> "  +  recipientList.toString());
				//logger.info("purchaseRequest Created By  ---> "+ purchaseRequest.getCreatedBy().getUsername());
			//	logger.info("purchaseRequest Last Updated By  ---> "+ purchaseRequest.getLastModifiedBy().getUsername());
				//mimeMessage.addRecipients(Message.RecipientType.CC, ccmailIds);
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				pr = purchaseRequest;
				message.setFrom(getDefaultEmailFromAddress());
				message.setTo(recipientList);
				message.setSubject(getEnvironment() +  " - " + " PurchaseRequest : " + " " + pr.getDocNumber() + " Status :" + pr.getStatus());
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
			input.put("credit", getCreditMemo());
			input.put("gr", getInventoryGoodsReceipt());
			input.put("gr", getInventoryGoodsIssue());
			input.put("gr", getInventoryGoodsTransfer());
			input.put("deptMap", deptMap());
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
	public CreditMemo getCreditMemo() {
		return credit;
	}
	
	public InventoryGoodsReceipt getInventoryGoodsReceipt() {
		return invgr;
	}
	public InventoryGoodsIssue getInventoryGoodsIssue() {
		return invgi;
	}
	public InventoryGoodsTransfer getInventoryGoodsTransfer() {
		return invgt;
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
				//InternetAddress[] myccList = InternetAddress.parse(getSUPPORT_CC_EMAIL());
				//mimeMessage.addRecipients(Message.RecipientType.CC, myBccList);
			    Integer id = requestForQuotation.getLineItems().get(0).getWarehouse();
				
				if(requestForQuotation.getStatus().equals(EnumStatusUpdate.OPEN.getStatus()))
				{
					getToEmails(EnumStatusUpdate.PR.getStatus(),EnumStatusUpdate.OPEN.getStatus(),id);
				}else {
					getToEmails(EnumStatusUpdate.PR.getStatus(),EnumStatusUpdate.APPROVAL.getStatus(),id);
					
				}
				//mimeMessage.addRecipients(Message.RecipientType.TO, toEmail);
				//mimeMessage.addRecipients(Message.RecipientType.CC, ccEmail);
				//mimeMessage.addRecipients(Message.RecipientType.BCC, bccEmail);
				if(toEmail != null) {
					toEmail += "," + getUser().getUserEmail();
				}else {
					toEmail = getUser().getUserEmail();
				}
				
				String[] recipientList = toEmail.split(",");
				logger.info("Sending purchaseRequest Email Log ---> ");
				logger.info("requestForQuotation ID ---> "+ requestForQuotation.getId());
				logger.info("requestForQuotation Doc Number ---> "+ requestForQuotation.getDocNumber() );
				logger.info("requestForQuotation Status ---> " + requestForQuotation.getStatus());
				logger.info("Email Send To ---> "  +  toEmail);
				//logger.info("requestForQuotation Created By  ---> "+ requestForQuotation.getCreatedBy().getUsername());
				//logger.info("requestForQuotation Last Updated By  ---> "+ requestForQuotation.getLastModifiedBy().getUsername());
				//mimeMessage.addRecipients(Message.RecipientType.CC, ccmailIds);
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				rfq = requestForQuotation;
				message.setFrom(getDefaultEmailFromAddress());
				message.setTo(recipientList);
				message.setSubject(getEnvironment() +  " - " + "RequestForQuotation :" + rfq.getDocNumber() + " Status :" + rfq.getStatus());
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
				//InternetAddress[] myccList = InternetAddress.parse(getSUPPORT_CC_EMAIL());
				//mimeMessage.addRecipients(Message.RecipientType.CC, myBccList);
				Integer id = purchaseOrder.getPurchaseOrderlineItems().get(0).getWarehouse();
				
				if(purchaseOrder.getStatus().equals(EnumStatusUpdate.OPEN.getStatus()))
				{
					getToEmails(EnumStatusUpdate.PO.getStatus(),EnumStatusUpdate.OPEN.getStatus(),id);
				}else {
					getToEmails(EnumStatusUpdate.PO.getStatus(),EnumStatusUpdate.APPROVAL.getStatus(),id);
					
				}
				
				if(toEmail != null) {
					toEmail += "," + getUser().getUserEmail();
				}else {
					toEmail =  getUser().getUserEmail();
					
				}
				String[] recipientList = toEmail.split(",");
				logger.info("Sending purchaseRequest Email Log ---> ");
				logger.info("purchaseOrder ID ---> "+ purchaseOrder.getId());
				logger.info("purchaseOrder Doc Number ---> "+ purchaseOrder.getDocNumber() );
				logger.info("purchaseOrder Status ---> " + purchaseOrder.getStatus());
				logger.info("Email Send To ---> "  +  toEmail);
				//logger.info("purchaseOrder Created By  ---> "+ purchaseOrder.getCreatedBy().getUsername());
				//logger.info("purchaseOrder Last Updated By  ---> "+ purchaseOrder.getLastModifiedBy().getUsername());
				//mimeMessage.addRecipients(Message.RecipientType.CC, ccmailIds);
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				po = purchaseOrder;
				message.setFrom(getDefaultEmailFromAddress());
				
				message.setTo(recipientList);
				message.setSubject(getEnvironment() +  " - " + "Purchase Order :" + po.getDocNumber() + " Status :" + po.getStatus());
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
				//InternetAddress[] myccList = InternetAddress.parse(getSUPPORT_CC_EMAIL());
				//mimeMessage.addRecipients(Message.RecipientType.CC, myBccList);
				Integer id = goodsReceipt.getGoodsReceiptLineItems().get(0).getWarehouse();
				
				if(goodsReceipt.getStatus().equals(EnumStatusUpdate.OPEN.getStatus()))
				{
					getToEmails(EnumStatusUpdate.GR.getStatus(),EnumStatusUpdate.OPEN.getStatus(),id);
				}else {
					getToEmails(EnumStatusUpdate.GR.getStatus(),EnumStatusUpdate.APPROVAL.getStatus(),id);
					
				}
				//mimeMessage.addRecipients(Message.RecipientType.TO, toEmail);
				//mimeMessage.addRecipients(Message.RecipientType.CC, ccEmail);
				//mimeMessage.addRecipients(Message.RecipientType.BCC, bccEmail);
				if(toEmail != null) {
					toEmail += "," + getUser().getUserEmail();
				}else {
					toEmail =  getUser().getUserEmail();
					
				}
				String[] recipientList = toEmail.split(",");
				logger.info("Sending purchaseRequest Email Log ---> ");
				logger.info("goodsReceipt ID ---> "+ goodsReceipt.getId());
				logger.info("goodsReceipt Doc Number ---> "+ goodsReceipt.getDocNumber() );
				logger.info("goodsReceipt Status ---> " + goodsReceipt.getStatus());
				logger.info("Email Send To ---> "  +  toEmail);
				//logger.info("goodsReceipt Created By  ---> "+ goodsReceipt.getCreatedBy().getUsername());
				//logger.info("goodsReceipt Last Updated By  ---> "+ goodsReceipt.getLastModifiedBy().getUsername());
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				goodsRec = goodsReceipt;
				message.setFrom(getDefaultEmailFromAddress());
				message.setTo(recipientList);
				message.setSubject(getEnvironment() +  " - " + "GoodsReceipt :" + goodsRec.getDocNumber() + " Status :" + goodsRec.getStatus());
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
				//InternetAddress[] myccList = InternetAddress.parse(getSUPPORT_CC_EMAIL());
				//mimeMessage.addRecipients(Message.RecipientType.CC, myBccList);
				Integer id = goodsReturn.getGoodsReturnLineItems().get(0).getWarehouse();
				
				if(goodsReturn.getStatus().equals(EnumStatusUpdate.OPEN.getStatus()))
				{
					getToEmails(EnumStatusUpdate.GRE.getStatus(),EnumStatusUpdate.OPEN.getStatus(),id);
				}else {
					getToEmails(EnumStatusUpdate.GRE.getStatus(),EnumStatusUpdate.APPROVAL.getStatus(),id);
					
				}
				//mimeMessage.addRecipients(Message.RecipientType.TO, toEmail);
				//mimeMessage.addRecipients(Message.RecipientType.CC, ccEmail);
				//mimeMessage.addRecipients(Message.RecipientType.BCC, bccEmail);
				if(toEmail != null) {
					toEmail += "," + getUser().getUserEmail();
				}else {
					toEmail =  getUser().getUserEmail();
					
				}
				String[] recipientList = toEmail.split(",");
				logger.info("Sending purchaseRequest Email Log ---> ");
				logger.info("goodsReturn ID ---> "+ goodsReturn.getId());
				logger.info("goodsReturn Doc Number ---> "+ goodsReturn.getDocNumber() );
				logger.info("goodsReturn Status ---> " + goodsReturn.getStatus());
				logger.info("Email Send To ---> "  +  toEmail);
				//logger.info("goodsReturn Created By  ---> "+ goodsReturn.getCreatedBy().getUsername());
				//logger.info("goodsReturn Last Updated By  ---> "+ goodsReturn.getLastModifiedBy().getUsername());
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				goodsRet = goodsReturn;
				message.setFrom(getDefaultEmailFromAddress());
				message.setTo(recipientList);
				message.setSubject(getEnvironment() +  " - " + "GoodsReturn :" + goodsRet.getDocNumber() + " Status :" + goodsRet.getStatus());
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
				//InternetAddress[] myccList = InternetAddress.parse(getSUPPORT_CC_EMAIL());
				//mimeMessage.addRecipients(Message.RecipientType.CC, myBccList);
				Integer id = invoice.getInVoiceLineItems().get(0).getWarehouse();
				
				if(invoice.getStatus().equals(EnumStatusUpdate.OPEN.getStatus()))
				{
					getToEmails(EnumStatusUpdate.INV.getStatus(),EnumStatusUpdate.OPEN.getStatus(),id);
				}else {
					getToEmails(EnumStatusUpdate.INV.getStatus(),EnumStatusUpdate.APPROVAL.getStatus(),id);
					
				}
				//mimeMessage.addRecipients(Message.RecipientType.TO, toEmail);
				//mimeMessage.addRecipients(Message.RecipientType.CC, ccEmail);
				//mimeMessage.addRecipients(Message.RecipientType.BCC, bccEmail);
				if(toEmail != null) {
					toEmail += "," + getUser().getUserEmail();
				}else {
					toEmail =  getUser().getUserEmail();
					
				}
				String[] recipientList = toEmail.split(",");
				logger.info("Sending purchaseRequest Email Log ---> ");
				logger.info("invoice ID ---> "+ invoice.getId());
				logger.info("invoice Doc Number ---> "+ invoice.getDocNumber() );
				logger.info("invoice Status ---> " + invoice.getStatus());
				logger.info("Email Send To ---> "  +  toEmail);
				//logger.info("invoice Created By  ---> "+ invoice.getCreatedBy().getUsername());
				//logger.info("invoice Last Updated By  ---> "+ invoice.getLastModifiedBy().getUsername());
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				inv = invoice;
				message.setFrom(getDefaultEmailFromAddress());
				message.setTo(recipientList);
				message.setSubject(getEnvironment() +  " - " + "Invoice : " + inv.getDocNumber() + "  Status :" + inv.getStatus());
				message.setText(getBody(), true);
			}

		};
	}
	
	protected MimeMessagePreparator createGoodsReturnRejectMessage(GoodsReturn goodsReturn) {
		return new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				//InternetAddress[] myBccList = InternetAddress.parse(getSUPPORT_CC_EMAIL());
				//mimeMessage.addRecipients(Message.RecipientType.CC, myBccList);
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				goodsRet = goodsReturn;
				message.setFrom(getDefaultEmailFromAddress());
				message.setTo(getUser().getUserEmail());
				message.setSubject(getEnvironment() +  " - " + "GoodsReturn :" + goodsRet.getDocNumber() + " Status :" + goodsRet.getStatus());
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
				//InternetAddress[] myccList = InternetAddress.parse(getSUPPORT_CC_EMAIL());
				//mimeMessage.addRecipients(Message.RecipientType.CC, myBccList);
					//ccEmail = emailIdService.getCCEmailIds(EnumStatusUpdate.PRODUCTQTY.getStatus(), EnumStatusUpdate.PRODUCTQTY.getStatus());
					toEmail = emailIdService.getToEmailIds(EnumStatusUpdate.PRODUCTQTY.getStatus(), EnumStatusUpdate.PRODUCTQTY.getStatus());
					//bccEmail = emailIdService.getBCCEmailIds(EnumStatusUpdate.RFQ.getStatus(), EnumStatusUpdate.APPROVAL.getStatus());
				 
				//mimeMessage.addRecipients(Message.RecipientType.TO, toEmail);
				//mimeMessage.addRecipients(Message.RecipientType.CC, ccEmail);
				//mimeMessage.addRecipients(Message.RecipientType.BCC, bccEmail);
				//toEmail += "," + getUser().getUserEmail();
				//String[] recipientList = toEmail.split(",");
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				message.setFrom(getDefaultEmailFromAddress());
				message.setTo(toEmail);
				message.setSubject(getEnvironment() +  " - " + "Products With Minimum Quantity");
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
				//InternetAddress[] myBccList = InternetAddress.parse(getDefaultBccEmailFromAddress());
				//mimeMessage.addRecipients(Message.RecipientType.CC, myBccList);
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				message.setFrom(getDefaultEmailFromAddress());
				message.setTo(getSEND_EMAIL());
				message.setSubject("Dashboard Email");
				message.setText(getDashboardBody(), true);
			}

		};
	}
	
	public void sendCreditMemoEmail(CreditMemo creditMemo) throws Exception {
		 if (shouldNotify()) {
	            logger.info("Sending notification for " + "k.panchakarla@manuhindia.com" + " ...");
	            try {
	                mailSender.send(createCreditMemoMessage(creditMemo));
	               // logger.info("Email notification successfully sent for " + mailTo);
	              //  doPRPostProcessing();
	            } catch (Exception e) {
	                logger.error("Error in sending email", e);
	                throw new Exception(e);
	            }
	        }
	}
	
	protected MimeMessagePreparator createCreditMemoMessage(CreditMemo creditMemo) {
		return new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				//InternetAddress[] myccList = InternetAddress.parse(getSUPPORT_CC_EMAIL());
				//mimeMessage.addRecipients(Message.RecipientType.CC, myBccList);
				Integer id = creditMemo.getCreditMemoLineItems().get(0).getWarehouse();
				
				if(creditMemo.getStatus().equals(EnumStatusUpdate.OPEN.getStatus()))
				{
					getToEmails(EnumStatusUpdate.CM.getStatus(),EnumStatusUpdate.OPEN.getStatus(),id);
				}else {
					getToEmails(EnumStatusUpdate.CM.getStatus(),EnumStatusUpdate.APPROVAL.getStatus(),id);
					
				} 
				//mimeMessage.addRecipients(Message.RecipientType.TO, toEmail);
				//mimeMessage.addRecipients(Message.RecipientType.CC, ccEmail);
				//mimeMessage.addRecipients(Message.RecipientType.BCC, bccEmail);
				if(toEmail != null) {
					toEmail += "," + getUser().getUserEmail();
				}else {
					toEmail =  getUser().getUserEmail();
					
				}
				String[] recipientList = toEmail.split(",");
				logger.info("Sending purchaseRequest Email Log ---> ");
				logger.info("creditMemo ID ---> "+ creditMemo.getId());
				logger.info("creditMemo Doc Number ---> "+ creditMemo.getDocNumber() );
				logger.info("creditMemo Status ---> " + creditMemo.getStatus());
				logger.info("Email Send To ---> "  +  toEmail);
				//logger.info("creditMemo Created By  ---> "+ creditMemo.getCreatedBy().getUsername());
				//logger.info("creditMemo Last Updated By  ---> "+ creditMemo.getLastModifiedBy().getUsername());
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				credit = creditMemo;
				message.setFrom(getDefaultEmailFromAddress());
				message.setTo(recipientList);
				message.setSubject(getEnvironment() +  " - " + "Credit Memo : " + credit.getDocNumber() + "  Status :" + credit.getStatus());
				message.setText(getBody(), true);
			}

		};
	}
	
	public void sendInvGoodsReceiptEmail(InventoryGoodsReceipt inventoryGoodsReceipt) throws Exception {
		 if (shouldNotify()) {
	            //logger.info("Sending notification for " + "k.panchakarla@manuhindia.com" + " ...");
	            try {
	                mailSender.send(createInvGoodsReceiptMessage(inventoryGoodsReceipt));
	               // logger.info("Email notification successfully sent for " + mailTo);
	              //  doPRPostProcessing();
	            } catch (Exception e) {
	                logger.error("Error in sending email", e);
	                throw new Exception(e);
	            }
	        }
	}
	
	protected MimeMessagePreparator createInvGoodsReceiptMessage(InventoryGoodsReceipt inventoryGoodsReceipt) {
		return new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				//InternetAddress[] myccList = InternetAddress.parse(getSUPPORT_CC_EMAIL());
				//mimeMessage.addRecipients(Message.RecipientType.CC, myBccList);
				Integer id = inventoryGoodsReceipt.getInventoryGoodsReceiptList().get(0).getWarehouse();
				
				if(inventoryGoodsReceipt.getStatus().equals(EnumStatusUpdate.OPEN.getStatus()))
				{
					getToEmails(EnumStatusUpdate.INVGR.getStatus(),EnumStatusUpdate.OPEN.getStatus(),id);
				}else {
					getToEmails(EnumStatusUpdate.INVGR.getStatus(),EnumStatusUpdate.APPROVAL.getStatus(),id);
					
				}
				//mimeMessage.addRecipients(Message.RecipientType.TO, toEmail);
				//mimeMessage.addRecipients(Message.RecipientType.CC, ccEmail);
				//mimeMessage.addRecipients(Message.RecipientType.BCC, bccEmail);
				if(toEmail != null) {
					toEmail += "," + getUser().getUserEmail();
				}else {
					toEmail =  getUser().getUserEmail();
					
				}
				String[] recipientList = toEmail.split(",");
				logger.info("Sending purchaseRequest Email Log ---> ");
				logger.info("creditMemo ID ---> "+ inventoryGoodsReceipt.getId());
				logger.info("creditMemo Doc Number ---> "+ inventoryGoodsReceipt.getDocNumber() );
				logger.info("creditMemo Status ---> " + inventoryGoodsReceipt.getStatus());
				logger.info("Email Send To ---> "  +  toEmail);
				//logger.info("creditMemo Created By  ---> "+ inventoryGoodsReceipt.getCreatedBy().getUsername());
				//logger.info("creditMemo Last Updated By  ---> "+ inventoryGoodsReceipt.getLastModifiedBy().getUsername());
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				invgr = inventoryGoodsReceipt;
				message.setFrom(getDefaultEmailFromAddress());
				message.setTo(recipientList);
				message.setSubject(getEnvironment() +  " - " + "Inventory Goods Receipt :" + inventoryGoodsReceipt.getDocNumber() + " Status :" + inventoryGoodsReceipt.getStatus());
				message.setText(getBody(), true);
			}

		};
	}
	
	public void sendInvGoodsIssueEmail(InventoryGoodsIssue inventoryGoodsIssue) throws Exception {
		 if (shouldNotify()) {
	            logger.info("Sending notification for " + "k.panchakarla@manuhindia.com" + " ...");
	            try {
	                mailSender.send(createInvGoodsIssueMessage(inventoryGoodsIssue));
	               // logger.info("Email notification successfully sent for " + mailTo);
	              //  doPRPostProcessing();
	            } catch (Exception e) {
	                logger.error("Error in sending email", e);
	                throw new Exception(e);
	            }
	        }
	}
	
	protected MimeMessagePreparator createInvGoodsIssueMessage(InventoryGoodsIssue inventoryGoodsIssue) {
		return new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				//InternetAddress[] myccList = InternetAddress.parse(getSUPPORT_CC_EMAIL());
				//mimeMessage.addRecipients(Message.RecipientType.CC, myBccList);
				Integer id = inventoryGoodsIssue.getInventoryGoodsIssueList().get(0).getWarehouse();
				
				if(inventoryGoodsIssue.getStatus().equals(EnumStatusUpdate.OPEN.getStatus()))
				{
					getToEmails(EnumStatusUpdate.INVGI.getStatus(),EnumStatusUpdate.OPEN.getStatus(),id);
				}else {
					getToEmails(EnumStatusUpdate.INVGI.getStatus(),EnumStatusUpdate.APPROVAL.getStatus(),id);
					
				} 
				//mimeMessage.addRecipients(Message.RecipientType.TO, toEmail);
				///mimeMessage.addRecipients(Message.RecipientType.CC, ccEmail);
				//mimeMessage.addRecipients(Message.RecipientType.BCC, bccEmail);
				if(toEmail != null) {
					toEmail += "," + getUser().getUserEmail();
				}else {
					toEmail =  getUser().getUserEmail();
					
				}
				String[] recipientList = toEmail.split(",");
				logger.info("Sending purchaseRequest Email Log ---> ");
				logger.info("inventoryGoodsIssue ID ---> "+ inventoryGoodsIssue.getId());
				logger.info("inventoryGoodsIssue Doc Number ---> "+ inventoryGoodsIssue.getDocNumber() );
				logger.info("inventoryGoodsIssue Status ---> " + inventoryGoodsIssue.getStatus());
				logger.info("Email Send To ---> "  +  toEmail);
				//logger.info("inventoryGoodsIssue Created By  ---> "+ inventoryGoodsIssue.getCreatedBy().getUsername());
				//logger.info("inventoryGoodsIssue Last Updated By  ---> "+ inventoryGoodsIssue.getLastModifiedBy().getUsername());
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				invgi = inventoryGoodsIssue;
				message.setFrom(getDefaultEmailFromAddress());
				message.setTo(recipientList);
				message.setSubject(getEnvironment() +  " - " + "Inventory Goods Issue :" + inventoryGoodsIssue.getDocNumber() + " Status :" + inventoryGoodsIssue.getStatus());
				message.setText(getBody(), true);
			}

		};
	}
	
	public void sendInvGoodsTransferEmail(InventoryGoodsTransfer inventoryGoodsTransfer) throws Exception {
		 if (shouldNotify()) {
	            logger.info("Sending notification for " + "k.panchakarla@manuhindia.com" + " ...");
	            try {
	                mailSender.send(createInvGoodsTransferMessage(inventoryGoodsTransfer));
	               // logger.info("Email notification successfully sent for " + mailTo);
	              //  doPRPostProcessing();
	            } catch (Exception e) {
	                logger.error("Error in sending email", e);
	                throw new Exception(e);
	            }
	        }
	}
	
	protected MimeMessagePreparator createInvGoodsTransferMessage(InventoryGoodsTransfer inventoryGoodsTransfer) {
		return new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				//InternetAddress[] myccList = InternetAddress.parse(getSUPPORT_CC_EMAIL());
				//mimeMessage.addRecipients(Message.RecipientType.CC, myBccList);
				Integer id = inventoryGoodsTransfer.getInventoryGoodsTransferList().get(0).getFromWarehouse();
				
				if(inventoryGoodsTransfer.getStatus().equals(EnumStatusUpdate.OPEN.getStatus()))
				{
					getToEmails(EnumStatusUpdate.INVGT.getStatus(),EnumStatusUpdate.OPEN.getStatus(),id);
				}else {
					getToEmails(EnumStatusUpdate.INVGT.getStatus(),EnumStatusUpdate.APPROVAL.getStatus(),id);
					
				}
				//mimeMessage.addRecipients(Message.RecipientType.TO, toEmail);
				//mimeMessage.addRecipients(Message.RecipientType.CC, ccEmail);
				//mimeMessage.addRecipients(Message.RecipientType.BCC, bccEmail);
				if(toEmail != null && !toEmail.equals("")) {
					toEmail += "," + getUser().getUserEmail();
				}else {
					toEmail =  getUser().getUserEmail();
					
				}
				String[] recipientList = toEmail.split(",");
				logger.info("Sending purchaseRequest Email Log ---> ");
				logger.info("inventoryGoodsTransfer ID ---> "+ inventoryGoodsTransfer.getId());
				logger.info("inventoryGoodsTransfer Doc Number ---> "+ inventoryGoodsTransfer.getDocNumber() );
				logger.info("inventoryGoodsTransfer Status ---> " + inventoryGoodsTransfer.getStatus());
				logger.info("Email Send To ---> "  +  toEmail);
				//logger.info("inventoryGoodsTransfer Created By  ---> "+ inventoryGoodsTransfer.getCreatedBy().getUsername());
				//logger.info("inventoryGoodsTransfer Last Updated By  ---> "+ inventoryGoodsTransfer.getLastModifiedBy().getUsername());
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				invgt = inventoryGoodsTransfer;
				message.setFrom(getDefaultEmailFromAddress());
				message.setTo(recipientList);
				message.setSubject(getEnvironment() +  " - " + "Inventory Goods Transfer :" + inventoryGoodsTransfer.getDocNumber() + " Status :" + inventoryGoodsTransfer.getStatus());
				message.setText(getBody(), true);
			}

		};
	}
}
