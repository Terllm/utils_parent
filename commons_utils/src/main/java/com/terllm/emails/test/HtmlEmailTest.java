package com.terllm.email.test;

import com.terllm.emails.commons.Emails;
import com.terllm.emails.constant.EmailConstants;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
/**
 * html邮件发送
 * @author Terllm
 *
 */
public class HtmlEmailTest {

	public static void main(String[] args) throws EmailException {

		HtmlEmail  htmlEmail = (HtmlEmail) Emails.constrator(HtmlEmail.class);

		htmlEmail.addTo(EmailConstants.RECEIVE_USER);

		htmlEmail.setMsg("<!DOCTYPE html>"
				+"<html>"
				+"<head>"
				+"<meta charset='UTF-8'>"
				+"<title>"+"</title>"
				+"</head>"
				+"<body>"
				+"<h1>测试html</h1>"
				+"<img style='width:100% height:100%' src='C:\\Users\\Administrator\\Desktop\\个人/阿丽塔.jpg'></img>"
				+"<img style='width:100% height:100%' src='https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1563942171144&di=fad0cb694ff9f431e87356f991d07c53&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20170913%2Fb82cac751d7f48529d62bad9ed2a6ddf.jpeg'></img>"
				+"</body>"
				+"</html>");

		htmlEmail.setSubject("测试html邮件");

		htmlEmail.send();




	}


}
