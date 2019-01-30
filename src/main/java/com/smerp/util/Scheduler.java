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
	//@Scheduled(cron = "0 0 */5 * * ?")
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
	 
	//@Scheduled(cron = "0 0 */5  * * ?")
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
	

}
