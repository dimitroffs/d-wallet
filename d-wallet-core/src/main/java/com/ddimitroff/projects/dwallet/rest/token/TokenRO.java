package com.ddimitroff.projects.dwallet.rest.token;

/*
 * {"tokenId":"<id>"}
 */
public class TokenRO {

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
