package com.smerp.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.smerp.util.RequestContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

	@Autowired
	private EmailGenerator emailGenerator;

	private static final Logger logger = LogManager.getLogger(Scheduler.class);

	// @Scheduled(cron = "0 0 */5 * * ?")
	public void sendDadhboardEmail() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String strDate = sdf.format(now);
		logger.info("Java cron job expression:: " + strDate);

		try {
			RequestContext.initialize();
			RequestContext.get().getConfigMap().put("mail.template", "dashboardEmail.ftl"); // Sending Email
			emailGenerator.sendEmailToUser(EmailGenerator.Sending_Email).sendDashboardEmail();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Scheduled(cron = "0 30 19 * * *")
	public void sendMinQtyProductsEmail() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String strDate = sdf.format(now);
		logger.info("Java cron job expression:: " + strDate);

		try {
			RequestContext.initialize();
			RequestContext.get().getConfigMap().put("mail.template", "minQtyEmail.ftl"); // Sending Email
			for (int i = 1; i <= 2; i++) {
				logger.info("send Inventory Qty Email to Warehouse ID :" + i);
				emailGenerator.sendEmailToUser(EmailGenerator.Sending_Email).sendMinQtyProductsEmail(i);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Scheduled(cron = "0 0 19 * * *")
	public void sendInventoryQtyEmail() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String strDate = sdf.format(now);
		logger.info("Scheduled - Inventory Status :: " + strDate);

		try {
			RequestContext.initialize();
			RequestContext.get().getConfigMap().put("mail.template", "inventoryQtyEmail.ftl"); // Sending Inventory Qty
																								// Email
			for (int i = 1; i <= 2; i++) {
				logger.info("send Inventory Qty Email to Warehouse ID :" + i);
				emailGenerator.sendEmailToUser(EmailGenerator.Sending_Email).sendInventoryQtyEmail(i);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Scheduled(cron = "0 15 19 * * *")
	public void sendInventoryGIEmail() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String strDate = sdf.format(now);
		logger.info("Inventory Goods Issue Report Email : " + strDate);

		try {
			RequestContext.initialize();
			RequestContext.get().getConfigMap().put("mail.template", "inventoryGoodsIssueReportEmail.ftl"); // Sending
			for (int i = 1; i <= 2; i++) {
				logger.info("send Inventory GI Email to Warehouse ID :" + i);
				emailGenerator.sendEmailToUser(EmailGenerator.Sending_Email).sendInventoryGIEmail(i);// Email
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Scheduled(cron = "0 54 18 * * *")
	public void sendTestEmail3() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String strDate = sdf.format(now);
		logger.info("sendTestEmail : " + strDate);

		try {
			RequestContext.initialize();
			emailGenerator.sendEmailToUser(EmailGenerator.Sending_Email).sendTestEmail();// Email
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Scheduled(cron = "0 9 19 * * *")
	public void sendTestEmail1() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String strDate = sdf.format(now);
		logger.info("sendTestEmail : " + strDate);

		try {
			RequestContext.initialize();
			emailGenerator.sendEmailToUser(EmailGenerator.Sending_Email).sendTestEmail();// Email
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Scheduled(cron = "0 24 19 * * *")
	public void sendTestEmail2() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String strDate = sdf.format(now);
		logger.info("sendTestEmail : " + strDate);

		try {
			RequestContext.initialize();
			emailGenerator.sendEmailToUser(EmailGenerator.Sending_Email).sendTestEmail();// Email
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
