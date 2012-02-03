package com.ddimitroff.projects.dwallet.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.ddimitroff.projects.dwallet.rest.cash.CashBalanceRO;
import com.ddimitroff.projects.dwallet.rest.cash.CashFlowRO;
import com.ddimitroff.projects.dwallet.rest.cash.CashRecordRO;
import com.ddimitroff.projects.dwallet.rest.token.TokenRO;
import com.ddimitroff.projects.dwallet.rest.user.UserRO;

/*
 * {"username":"testName1","hashPassword":"testPassword1"}
 */
public class DWalletJsonParser {

	private static DWalletJsonParser instance;
	private ObjectMapper jsonMapper;

	private DWalletJsonParser() {
		jsonMapper = new ObjectMapper();
	}

	public static synchronized DWalletJsonParser get() {
		if (null == instance) {
			instance = new DWalletJsonParser();
		}

		return instance;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Cloning of DWalletJsonParser instance not supported!");
	}

	public UserRO getUserFromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		UserRO output = jsonMapper.readValue(json, UserRO.class);
		return output;
	}

	public String mapUserToJson(UserRO user) throws JsonGenerationException, JsonMappingException, IOException {
		String output = jsonMapper.writeValueAsString(user);
		return output;
	}

	public TokenRO getTokenFromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		TokenRO output = jsonMapper.readValue(json, TokenRO.class);
		return output;
	}

	public String mapTokenToJson(TokenRO token) throws JsonGenerationException, JsonMappingException, IOException {
		String output = jsonMapper.writeValueAsString(token);
		return output;
	}

	public CashFlowRO getCashFlowFromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		CashFlowRO output = jsonMapper.readValue(json, CashFlowRO.class);
		return output;
	}

	public String mapCashFlowToJson(CashFlowRO cashFlow) throws JsonGenerationException, JsonMappingException, IOException {
		String output = jsonMapper.writeValueAsString(cashFlow);
		return output;
	}

	public CashRecordRO getCashRecordFromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		CashRecordRO output = jsonMapper.readValue(json, CashRecordRO.class);
		return output;
	}

	public String mapCashRecordToJson(CashRecordRO cashRecord) throws JsonGenerationException, JsonMappingException, IOException {
		String output = jsonMapper.writeValueAsString(cashRecord);
		return output;
	}

	public CashBalanceRO getCashBalanceFromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		CashBalanceRO output = jsonMapper.readValue(json, CashBalanceRO.class);
		return output;
	}

	public String mapCashBalanceToJson(CashBalanceRO cashBalance) throws JsonGenerationException, JsonMappingException, IOException {
		String output = jsonMapper.writeValueAsString(cashBalance);
		return output;
	}

}
