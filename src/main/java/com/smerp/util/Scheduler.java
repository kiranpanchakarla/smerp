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
	EmailGenerator emailGenerator;

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

	// @Scheduled(cron = "0 0 */5 * * ?")
	public void sendMinQtyProductsEmail() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String strDate = sdf.format(now);
		logger.info("Java cron job expression:: " + strDate);

		try {
			RequestContext.initialize();
			RequestContext.get().getConfigMap().put("mail.template", "minQtyEmail.ftl"); // Sending Email
			emailGenerator.sendEmailToUser(EmailGenerator.Sending_Email).sendsendMinQtyProductsEmailEmail();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//@Scheduled(cron = "0 */3 * * * ?")
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
				emailGenerator.sendEmailToUser(EmailGenerator.Sending_Email).sendInventoryQtyEmail(i); // for warehouse
																										// 1,2
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// @Scheduled(cron = "0 */5 * * * ?")
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
}
