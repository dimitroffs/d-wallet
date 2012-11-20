package com.ddimitroff.projects.dwallet.rest.token;

import java.io.Serializable;

import com.ddimitroff.projects.dwallet.rest.response.Responsable;

/**
 * A DTO class for object/JSON mapping tokens. It has the following structure:<br>
 * {"tokenId":"<token-id>"}
 * 
 * @author Dimitar Dimitrov
 * 
 */
public class TokenRO implements Serializable, Responsable {

  /** Serial version UID constant */
  private static final long serialVersionUID = 1L;

  /** Id of token field */
  private String tokenId;

  /**
   * Default no argument constructor for creating new {@link TokenRO} object
   */
  public TokenRO() {
  }

  /**
   * A constructor for creating new {@link TokenRO} object
   * 
   * @param tokenId
   *          - id of token to set
   */
  public TokenRO(String tokenId) {
    this.tokenId = tokenId;
  }

  /**
   * @return the tokenId
   */
  public String getTokenId() {
    return tokenId;
  }

  /**
   * @param tokenId
   *          the tokenId to set
   */
  public void setTokenId(String tokenId) {
    this.tokenId = tokenId;
  }

}
