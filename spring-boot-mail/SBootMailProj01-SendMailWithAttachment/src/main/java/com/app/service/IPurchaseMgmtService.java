package com.app.service;

import jakarta.mail.MessagingException;

public interface IPurchaseMgmtService {

	public String shopping(
			String[] items,
			float[] prices,
			String fromMail,
			String tomail[]
			) throws MessagingException;
}
