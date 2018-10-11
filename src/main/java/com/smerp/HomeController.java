package com.smerp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smerp.email.EmailGeneratorFactory;
import com.smerp.util.Context;
import com.smerp.util.RequestContext;

@Controller
public class HomeController {
	
	private static final Logger logger = LogManager.getLogger(HomeController.class);
	
	
	@Autowired
	EmailGeneratorFactory emailGeneratorFactory;
	
	@RequestMapping("/")
	public String welcome() throws Exception {
		logger.info("welcome to smerp");
		/*String mailTo="g.boddu@manuhindia.com";
		
		Context context=RequestContext.get();
		
		context.getConfigMap().put("mail.template", "activation.ftl");
		emailGeneratorFactory.get(EmailGeneratorFactory.REGISTRATION_EMAIL).sendMail(mailTo);*/
		return "index";
	}
	


}
