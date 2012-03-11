package com.ddimitroff.projects.dwallet.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class MailSender {

	private static final Properties mailProperties = new Properties();
	private static final Logger logger = Logger.getLogger(MailSender.class);

	private static final String GMAIL_USER = "dwallet.service@gmail.com";
	private static final String GMAIL_PASSWORD = "dwallet123!";

	private static Authenticator authenticator = null;

	public MailSender() {
		fetchConfig();
		authenticateGmailUser();
	}

	private static void fetchConfig() {
		mailProperties.put("mail.smtp.host", "smtp.gmail.com");
		mailProperties.put("mail.smtp.socketFactory.port", "465");
		mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		mailProperties.put("mail.smtp.auth", "true");
		mailProperties.put("mail.smtp.port", "465");
		mailProperties.put("mail.store.protocol", "imaps");

		logger.info("Mail properties successfully loaded");
	}

	private static void authenticateGmailUser() {
		authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(GMAIL_USER, GMAIL_PASSWORD);
			}
		};

		logger.info("Mail client successfully authenticated with 'GMail'");
	}

	public static void refreshConfig() {
		mailProperties.clear();
		fetchConfig();
	}

	public void sendEmail(String to, String subject, String body) {
		Session session = Session.getDefaultInstance(mailProperties, authenticator);
		MimeMessage message = new MimeMessage(session);
		try {
			message.addRecipients(RecipientType.TO, to);
			message.setSubject(subject, "UTF-8");
			message.setText(body, "UTF-8");
			Transport.send(message);
			logger.info("Message successfully sent to its destination");
		} catch (MessagingException me) {
			logger.info("Unable to send message " + me);
		}
	}

	public void sendHTMLEmail(String to, String subject, String bodyHTML) {
		Session session = Session.getDefaultInstance(mailProperties, authenticator);
		MimeMessage message = new MimeMessage(session);
		try {
			message.addRecipients(RecipientType.TO, to);
			message.setSubject(subject, "UTF-8");
			message.setContent(bodyHTML, "text/html; charset=utf-8");
			Transport.send(message);
			logger.info("Message successfully sent to its destination");
		} catch (MessagingException me) {
			logger.error("Unable to send message " + me);
		}
	}

}
