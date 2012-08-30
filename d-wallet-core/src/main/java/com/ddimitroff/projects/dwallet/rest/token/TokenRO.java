package com.ddimitroff.projects.dwallet.rest.token;

import java.io.Serializable;

/*
 * {"tokenId":"<id>"}
 */
public class TokenRO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String tokenId;

  public TokenRO() {
  }

  public TokenRO(String tokenId) {
    this.tokenId = tokenId;
  }

  public String getTokenId() {
    return tokenId;
  }

  public void setTokenId(String tokenId) {
    this.tokenId = tokenId;
  }

}
