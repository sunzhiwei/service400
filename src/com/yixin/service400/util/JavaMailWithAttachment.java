package com.yixin.service400.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class JavaMailWithAttachment {
	private MimeMessage message;
	private Session session;
	private Transport transport;

	private String mailHost = "";
	private String sender_username = "";
	private String sender_password = "";

	private Properties properties = new Properties();

	/*
	 * 初始化方法
	 */
	public JavaMailWithAttachment(boolean debug) {
		InputStream in = JavaMailWithAttachment.class.getClassLoader()
				.getResourceAsStream("conf.properties");
		try {
			properties.load(in);
			this.mailHost = properties.getProperty("mail.smtp.host");
			this.sender_username = properties
					.getProperty("mail.sender.username");
			this.sender_password = properties
					.getProperty("mail.sender.password");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		session = Session.getInstance(properties);
		session.setDebug(debug);// 开启后有调试信息
		message = new MimeMessage(session);
	}

	public void doSendHtmlEmail(String subject, String sendHtml,
			List<String> receiveuserlist, List<String> copyreceiverlist,
			List<File> attachment) {
		try {
			// 发件人
			InternetAddress from = new InternetAddress(sender_username);
			message.setFrom(from);

			// 收件人
			InternetAddress to[] = new InternetAddress[receiveuserlist.size()];
			for (int i = 0; i < receiveuserlist.size(); i++) {
				to[i] = new InternetAddress(receiveuserlist.get(i));
			}
			message.setRecipients(Message.RecipientType.TO, to);
			copyreceiverlist.add(sender_username); // 抄送给公共邮箱（管理者邮箱：400telephone@creditease.cn）
			if (copyreceiverlist.size() != 0) {
				InternetAddress[] ccAdresses = new InternetAddress[copyreceiverlist
						.size()];
				for (int i = 0; i < copyreceiverlist.size(); i++) {
					ccAdresses[i] = new InternetAddress(copyreceiverlist.get(i));
				}
				message.setRecipients(Message.RecipientType.CC, ccAdresses); // 抄送人
			}
			// 邮件主题
			message.setSubject(subject);
			// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
			Multipart multipart = new MimeMultipart();
			// 添加邮件正文
			BodyPart contentPart = new MimeBodyPart();
			contentPart.setContent(sendHtml, "text/html;charset=UTF-8");
			multipart.addBodyPart(contentPart);
			// 添加附件的内容
			if (attachment != null) {
				for (int i = 0; i < attachment.size(); i++) {
					BodyPart attachmentBodyPart = new MimeBodyPart();
					DataSource source = new FileDataSource(attachment.get(i));
					attachmentBodyPart.setDataHandler(new DataHandler(source));
					attachmentBodyPart.setFileName(MimeUtility
							.encodeWord(attachment.get(i).getName()));
					multipart.addBodyPart(attachmentBodyPart);
				}
			}
			// 将multipart对象放到message中
			message.setContent(multipart);
			// 保存邮件
			message.saveChanges();
			transport = session.getTransport("smtp");
			// smtp验证，就是你用来发邮件的邮箱用户名密码
			transport.connect(mailHost, sender_username, sender_password);
			// 发送
			transport.sendMessage(message, message.getAllRecipients());
			System.out.println("send success!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (transport != null) {
				try {
					transport.close();
				} catch (MessagingException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
	}

	public void doSendHtmlEmail_WG_IVR(String subject, String sendHtml,
			List<String> copyreceiverlist, List<File> attachment) {
		try {
			// 发件人
			InternetAddress from = new InternetAddress(sender_username);
			message.setFrom(from);
			// 收件人
			InternetAddress to = new InternetAddress(sender_username);
			message.setRecipient(Message.RecipientType.TO, to);
			if (copyreceiverlist.size() != 0) {
				InternetAddress[] ccAdresses = new InternetAddress[copyreceiverlist
						.size()];
				for (int i = 0; i < copyreceiverlist.size(); i++) {
					ccAdresses[i] = new InternetAddress(copyreceiverlist.get(i));
				}
				message.setRecipients(Message.RecipientType.CC, ccAdresses); // 抄送人
			}
			// 邮件主题
			message.setSubject(subject);
			// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
			Multipart multipart = new MimeMultipart();
			// 添加邮件正文
			BodyPart contentPart = new MimeBodyPart();
			contentPart.setContent(sendHtml, "text/html;charset=UTF-8");
			multipart.addBodyPart(contentPart);
			// 添加附件的内容
			if (attachment != null) {
				for (int i = 0; i < attachment.size(); i++) {
					BodyPart attachmentBodyPart = new MimeBodyPart();
					DataSource source = new FileDataSource(attachment.get(i));
					attachmentBodyPart.setDataHandler(new DataHandler(source));
					attachmentBodyPart.setFileName(MimeUtility
							.encodeWord(attachment.get(i).getName()));
					multipart.addBodyPart(attachmentBodyPart);
				}
			}
			// 将multipart对象放到message中
			message.setContent(multipart);
			// 保存邮件
			message.saveChanges();
			transport = session.getTransport("smtp");
			// smtp验证，就是你用来发邮件的邮箱用户名密码
			transport.connect(mailHost, sender_username, sender_password);
			// 发送
			transport.sendMessage(message, message.getAllRecipients());
			System.out.println("send success!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (transport != null) {
				try {
					transport.close();
				} catch (MessagingException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
	}
}
