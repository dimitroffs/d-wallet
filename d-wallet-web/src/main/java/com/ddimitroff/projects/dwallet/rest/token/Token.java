package com.ddimitroff.projects.dwallet.rest.token;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.jsecurity.crypto.hash.Md5Hash;

import com.ddimitroff.projects.dwallet.db.entities.User;

/**
 * A class representing token for using the D-Wallet API
 * 
 * @author Dimitar Dimitrov
 * 
 */
public class Token implements Comparable<Token> {

  /** Token timeout in minutes */
  static final int TOKEN_TIMEOUT = 30;

  /** Simple date format for representing dates for token */
  private static final SimpleDateFormat TOKEN_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd-hhmm");

  /** Owner of token */
  private User owner;

  /** Creation date of token */
  private Date createdOn;

  /** Id of token */
  private String id;

  /** Valid to date of token */
  private String validTo;

  /**
   * Constructor for new {@link Token} object
   * 
   * @param owner
   *          - owner of token
   */
  public Token(User owner) {
    this.owner = owner;
    this.createdOn = new Date();
    this.id = generateTokenId(owner.getEmail());
    this.validTo = getValidToDateAsString();
  }

  /**
   * A method for generating new token id
   * 
   * @param username
   *          - username {@link String} object of owner
   * 
   * @return generated id of new token
   */
  private String generateTokenId(String username) {
    Md5Hash hash = new Md5Hash(username, String.valueOf(createdOn.getTime()));

    return hash.toHex();
  }

  /**
   * A method for representing token's valid to date as {@link String}
   * 
   * @return {@link String} of valid to date of token
   */
  private String getValidToDateAsString() {
    Calendar cal = Calendar.getInstance();
    cal.setTime(createdOn);
    cal.add(Calendar.MINUTE, TOKEN_TIMEOUT);

    return TOKEN_DATE_FORMAT.format(cal.getTime());
  }

  /**
   * @return the owner
   */
  public User getOwner() {
    return owner;
  }

  /**
   * @param owner
   *          the owner to set
   */
  public void setOwner(User owner) {
    this.owner = owner;
  }

  /**
   * @return the createdOn
   */
  public Date getCreatedOn() {
    return createdOn;
  }

  /**
   * @param createdOn
   *          the createdOn to set
   */
  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
  }

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return the validTo
   */
  public String getValidTo() {
    return validTo;
  }

  /**
   * @param validTo
   *          the validTo to set
   */
  public void setValidTo(String validTo) {
    this.validTo = validTo;
  }

  @Override
  public String toString() {
    return "Token [owner=" + owner.getEmail() + ", createdOn=" + createdOn + ", id=" + id + ", validTo=" + validTo
        + "]";
  }

  @Override
  public int compareTo(Token token) {
    return this.getOwner().compareTo(token.getOwner());
  }

}
