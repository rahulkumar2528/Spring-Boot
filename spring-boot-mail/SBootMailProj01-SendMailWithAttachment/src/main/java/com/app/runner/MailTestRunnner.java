package com.app.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.app.service.IPurchaseMgmtService;

@Component
public class MailTestRunnner implements CommandLineRunner {

	private IPurchaseMgmtService purchaseMgmtService;
	
	private Environment env;
	
	public MailTestRunnner(Environment env, IPurchaseMgmtService purchaseMgmtService) {
		this.env = env;
		this.purchaseMgmtService = purchaseMgmtService;
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		String fromMail = env.getRequiredProperty("spring.mail.username");
		
		String msg = purchaseMgmtService.shopping(
				new String[] {"shirt", "trouser","cap"}, 
				new float[] {235, 300, 400}, 
				fromMail, 
				new String[] {"rk7373914@gmail.com", "rahulkumar.mofpi@gmail.com"});
		
		System.out.println(msg);
	}
	

}
