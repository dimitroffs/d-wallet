package com.ddimitroff.projects.dwallet.mail;

/**
 * An utility class for all necessary email template configurations
 * 
 * @author Dimitar Dimitrov
 * 
 */
public class DwalletMailTemplateUtils {

  /** Email success register subject constant */
  public static final String EMAIL_SUCCESS_REGISTER_SUBJECT = "Thank you for registering to D-Wallet service";

  /** Email success register HTML body constant */
  public static final String EMAIL_SUCCESS_REGISTER_HTML_BODY = "<p>Dear %s,<br/><br/>Thank you for registering to D-Wallet service!<br/>Your new account details:<br/>-&nbsp;startup balance:&nbsp;%s<br/>-&nbsp;default account currency:&nbsp;%s<br/><br/>Best Regards,<br/>D-Wallet development team</p>";

}
