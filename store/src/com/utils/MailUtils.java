package com.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {

	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {
		// 1.创建一个程序与服务器会话对象Session

		Properties props = new Properties();
		//设置发送的协议
		props.setProperty("mail.transport.protocol", "SMTP");
		
		//设置发送邮件的服务器
		props.setProperty("mail.host", "localhost");
		props.setProperty("mail.smtp.auth", "true");// 鎸囧畾楠岃瘉涓簍rue

		// 创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				//设置发送人和号码
				return new PasswordAuthentication("service", "123");
			}
		};

		Session session = Session.getInstance(props, auth);

		// 2.创建一个Message,相当于邮件的内容
		Message message = new MimeMessage(session);

	 //设置发送者
		message.setFrom(new InternetAddress("service@store.com"));

			//设置发送方式与接收者
		message.setRecipient(RecipientType.TO, new InternetAddress(email)); 

		//设置邮件主题
		message.setSubject("鐢ㄦ埛婵�娲�");
		 
		//设置邮件内容
		message.setContent(emailMsg, "text/html;charset=utf-8");

		// 3.创建Transport用于将邮件发送
		Transport.send(message);
	}
}
