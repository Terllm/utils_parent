package com.terllm.emails.commons;

import com.terllm.emails.constant.EmailConstants;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author terllm 2019-08-24
 * 构建邮件实例
 *
 */
public class Emails {


	public static Email constrator(Class clzz) throws EmailException {

		Email email = null;

		String clzzs = String.valueOf(clzz);

		email = getInstance(email, clzzs);

		initEmails(email);

		return email;

	}

	private static Email getInstance(Email email, String clzzs) {
		int i = 0;
		while (!clzzs.contains(EmailConstants.CLZZ_NAME[i])) {
			
			i++;
		}

		switch (EmailConstants.CLZZ_NAME[i]) {
		case "ImageHtmlEmail":
			email = new ImageHtmlEmail();
			break;
		case "SimpleEmail":
			email = new SimpleEmail();
			break;
		case "HtmlEmail":
			email = new HtmlEmail();
			break;
		case "MultiPartEmail":
			email = new MultiPartEmail();
			break;

		default:
			break;
		}
		return email;
	}

	private static void initEmails(Email email) throws EmailException {

		email.setHostName(EmailConstants.EMAIL_HOST);
		email.setAuthentication(EmailConstants.AUTHOR_USER, EmailConstants.AUTHOR_PWD);
		email.setFrom(EmailConstants.AUTHOR_USER);
		email.setSmtpPort(EmailConstants.EMAIL_PORT);
		email.setSSLOnConnect(true);
		email.setCharset("UTF-8");

	}

}
