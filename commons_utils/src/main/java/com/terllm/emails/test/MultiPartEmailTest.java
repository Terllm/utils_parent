package com.terllm.emails.test;

import com.terllm.emails.commons.Emails;
import com.terllm.emails.constant.EmailConstants;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;


/**
 * 附件邮件发送
 * @author Terllm
 *
 */
public class MultiPartEmailTest {

	
	public static void main(String[] args) throws EmailException {
		
		MultiPartEmail multiPartEmail =  (MultiPartEmail) Emails.constrator(MultiPartEmail.class);
		//构造附件对象
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath("E:\\test.xls");
		attachment.setName("测试.xls");
		//附件描述方式
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		//附件描述内容
		attachment.setDescription("excel文件");
		multiPartEmail.attach(attachment);
		multiPartEmail.addTo(EmailConstants.RECEIVE_USER);
		multiPartEmail.setMsg("测试附件");
		multiPartEmail.send();
		
		
	}
	
	
}
