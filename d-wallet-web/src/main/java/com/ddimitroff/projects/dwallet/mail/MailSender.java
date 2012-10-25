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

import com.ddimitroff.projects.dwallet.rest.exception.DWalletCoreException;

/**
 * A class for sending email messages to users. It is used as Spring component,
 * defined in XML context file.
 * 
 * @author Dimitar Dimitrov
 * 
 */
public class MailSender {

  /** Mail properties constant */
  private static final Properties mailProperties = new Properties();

  /** Logger constant */
  private static final Logger logger = Logger.getLogger(MailSender.class);

  /** Google mail username constant */
  private static final String GMAIL_USER = "dwallet.service@gmail.com";

  /** Google mail password constant */
  private static final String GMAIL_PASSWORD = "dwallet123!";

  /** UTF-8 constant */
  private static final String UTF_8 = "UTF-8";

  /** Content type for email html body constant */
  private static final String EMAIL_HTML_BODY_CONTENT_TYPE = "text/html; charset=utf-8";

  /** {@link Authenticator} object used for Google mail */
  private static Authenticator authenticator = null;

  /**
   * {@link MailSender} default constructor
   */
  public MailSender() {
    fetchConfig();
    authenticateGmailUser();
  }

  /**
   * A static method for configuring {@link MailSender} object using Google mail
   * configuration
   */
  private static void fetchConfig() {
    mailProperties.put("mail.smtp.host", "smtp.gmail.com");
    mailProperties.put("mail.smtp.socketFactory.port", "465");
    mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    mailProperties.put("mail.smtp.auth", "true");
    mailProperties.put("mail.smtp.port", "465");
    mailProperties.put("mail.store.protocol", "imaps");

    logger.info("Mail properties successfully loaded");
  }

  /**
   * A static method for authenticating with Google mail
   */
  private static void authenticateGmailUser() {
    authenticator = new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(GMAIL_USER, GMAIL_PASSWORD);
      }
    };

    logger.info("Mail client successfully authenticated with 'GMail'");
  }

  /**
   * A helper static method for refreshing mail configurations
   */
  public static void refreshConfig() {
    mailProperties.clear();
    fetchConfig();
  }

  /**
   * A method for sending simple email
   * 
   * @param to
   *          - comma separated values of email addresses defining the
   *          recipients of the email
   * @param subject
   *          - subject of the email
   * @param body
   *          - body of the email
   * @throws DWalletCoreException
   *           if errors occur while trying to send simple email
   */
  public void sendEmail(String to, String subject, String body) throws DWalletCoreException {
    Session session = Session.getDefaultInstance(mailProperties, authenticator);
    MimeMessage message = new MimeMessage(session);
    try {
      message.addRecipients(RecipientType.TO, to);
      message.setSubject(subject, UTF_8);
      message.setText(body, UTF_8);
      Transport.send(message);
      logger.info("Message successfully sent to its destination");
    } catch (MessagingException me) {
      logger.info("Unable to send message " + me);
      throw new DWalletCoreException("Unable to send simple email. ", me);
    }
  }

  /**
   * A method for sending HTML body email
   * 
   * @param to
   *          - comma separated values of email addresses defining the
   *          recipients of the email
   * @param subject
   *          - subject of the email
   * @param bodyHTML
   *          - HTML body of the email
   * @throws DWalletCoreException
   *           if errors occur while trying to send HTML body email
   */
  public void sendHTMLEmail(String to, String subject, String bodyHTML) throws DWalletCoreException {
    Session session = Session.getDefaultInstance(mailProperties, authenticator);
    MimeMessage message = new MimeMessage(session);
    try {
      message.addRecipients(RecipientType.TO, to);
      message.setSubject(subject, UTF_8);
      message.setContent(bodyHTML, EMAIL_HTML_BODY_CONTENT_TYPE);
      Transport.send(message);
      logger.info("Message successfully sent to its destination");
    } catch (MessagingException me) {
      logger.error("Unable to send message " + me);
      throw new DWalletCoreException("Unable to send HTML body email. ", me);
    }
  }

}
