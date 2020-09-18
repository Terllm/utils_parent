package com.terllm.emails.test;

import com.terllm.emails.commons.Emails;
import com.terllm.emails.constant.EmailConstants;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
/**
 * ��ͨ�����ʼ�����
 * @author Terllm
 *
 */
public class SimpleEmailTest {
	
	public static void main(String[] args) throws EmailException {
		
		SimpleEmail simpleEmail = (SimpleEmail) Emails.constrator(SimpleEmail.class);
		simpleEmail.addTo(EmailConstants.RECEIVE_USER);
		simpleEmail.setSubject("SimpleEmail");
		simpleEmail.setMsg("测试");
		simpleEmail.send();
		
	}
	
}
