package com.app.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class IPurchaseMgmtServiceImpl implements IPurchaseMgmtService {

	@Autowired
	private JavaMailSender sender;
	
	@Override
	public String shopping(String[] items, float[] prices, String fromMail, String[] toMail) throws MessagingException {
		float billAmt=0.0f;
		for(float p : prices) {
			billAmt+=p;
		}
		String msg = Arrays.toString(items) + " are purchased with the prices" + Arrays.toString(prices)
				+ " having billAmt::" + billAmt;
		
		return sendMailWithAttachment(msg, fromMail, toMail);
	}
	
	private String sendMailWithAttachment(String msg, String fromMail, String[] toMail) throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(fromMail);
		helper.setCc(toMail);
		helper.setSubject("Open it to know it");
		helper.setSentDate(new Date());
		helper.setText(msg);
		helper.addAttachment("nit.png", new ClassPathResource("nit.png"));
		sender.send(message);
		return "mail sent successfully";	
	}

}
