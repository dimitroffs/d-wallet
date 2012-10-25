package com.ddimitroff.projects.dwallet.json;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.ddimitroff.projects.dwallet.rest.cash.CashBalanceRO;
import com.ddimitroff.projects.dwallet.rest.cash.CashFlowRO;
import com.ddimitroff.projects.dwallet.rest.cash.CashRecordRO;
import com.ddimitroff.projects.dwallet.rest.exception.DWalletCoreException;
import com.ddimitroff.projects.dwallet.rest.token.TokenRO;
import com.ddimitroff.projects.dwallet.rest.user.UserRO;

/**
 * A singleton class representing D-Wallet JSON parser object. Used for mapping
 * JSON to object and opposite.
 * 
 * @author Dimitar Dimitrov
 * 
 */
public class DWalletJsonParser {

  /** Singleton {@link DWalletJsonParser} instance field */
  private static DWalletJsonParser instance;

  /** Jackson mapper object */
  private ObjectMapper jsonMapper;

  /**
   * Private constructor for creating new {@link DWalletJsonParser} object
   */
  private DWalletJsonParser() {
    jsonMapper = new ObjectMapper();
  }

  /**
   * A static method for returning {@link DWalletJsonParser} object instance
   * 
   * @return {@link DWalletJsonParser} object instance
   */
  public static synchronized DWalletJsonParser get() {
    if (null == instance) {
      instance = new DWalletJsonParser();
    }

    return instance;
  }

  /**
   * Overriding clone() method as not supported
   */
  @Override
  protected Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException("Cloning of DWalletJsonParser instance not supported!");
  }

  /**
   * A method for getting {@link UserRO} object from JSON string.
   * 
   * @param json
   *          - JSON string
   * 
   * @return {@link UserRO} object
   * 
   * @throws DWalletCoreException
   *           if errors occur while trying to get {@link UserRO} object from
   *           JSON string
   */
  public UserRO getUserFromJson(String json) throws DWalletCoreException {
    UserRO output = null;

    try {
      output = jsonMapper.readValue(json, UserRO.class);
    } catch (JsonParseException e) {
      throw new DWalletCoreException(e);
    } catch (JsonMappingException e) {
      throw new DWalletCoreException(e);
    } catch (IOException e) {
      throw new DWalletCoreException(e);
    }

    return output;
  }

  /**
   * A method for mapping {@link UserRO} object to JSON string
   * 
   * @param user
   *          - {@link UserRO} object to map
   * 
   * @return JSON string representation of {@link UserRO} object
   * 
   * @throws DWalletCoreException
   *           if errors occur while trying to map {@link UserRO} object to JSON
   *           string
   */
  public String mapUserToJson(UserRO user) throws DWalletCoreException {
    String output = null;

    try {
      output = jsonMapper.writeValueAsString(user);
    } catch (JsonParseException e) {
      throw new DWalletCoreException(e);
    } catch (JsonMappingException e) {
      throw new DWalletCoreException(e);
    } catch (IOException e) {
      throw new DWalletCoreException(e);
    }

    return output;
  }

  /**
   * A method for getting {@link TokenRO} object from JSON string.
   * 
   * @param json
   *          - JSON string
   * 
   * @return {@link TokenRO} object
   * 
   * @throws DWalletCoreException
   *           if errors occur while trying to get {@link TokenRO} object from
   *           JSON string
   */
  public TokenRO getTokenFromJson(String json) throws DWalletCoreException {
    TokenRO output = null;

    try {
      output = jsonMapper.readValue(json, TokenRO.class);
    } catch (JsonParseException e) {
      throw new DWalletCoreException(e);
    } catch (JsonMappingException e) {
      throw new DWalletCoreException(e);
    } catch (IOException e) {
      throw new DWalletCoreException(e);
    }

    return output;
  }

  /**
   * A method for mapping {@link TokenRO} object to JSON string
   * 
   * @param token
   *          - {@link TokenRO} object to map
   * 
   * @return JSON string representation of {@link TokenRO} object
   * 
   * @throws DWalletCoreException
   *           if errors occur while trying to map {@link TokenRO} object to
   *           JSON string
   */
  public String mapTokenToJson(TokenRO token) throws DWalletCoreException {
    String output = null;

    try {
      output = jsonMapper.writeValueAsString(token);
    } catch (JsonParseException e) {
      throw new DWalletCoreException(e);
    } catch (JsonMappingException e) {
      throw new DWalletCoreException(e);
    } catch (IOException e) {
      throw new DWalletCoreException(e);
    }

    return output;
  }

  /**
   * A method for getting {@link CashFlowRO} object from JSON string.
   * 
   * @param json
   *          - JSON string
   * 
   * @return {@link CashFlowRO} object
   * 
   * @throws DWalletCoreException
   *           if errors occur while trying to get {@link CashFlowRO} object
   *           from JSON string
   */
  public CashFlowRO getCashFlowFromJson(String json) throws DWalletCoreException {
    CashFlowRO output = null;

    try {
      output = jsonMapper.readValue(json, CashFlowRO.class);
    } catch (JsonParseException e) {
      throw new DWalletCoreException(e);
    } catch (JsonMappingException e) {
      throw new DWalletCoreException(e);
    } catch (IOException e) {
      throw new DWalletCoreException(e);
    }

    return output;
  }

  /**
   * A method for mapping {@link CashFlowRO} object to JSON string
   * 
   * @param cashFlow
   *          - {@link CashFlowRO} object to map
   * 
   * @return JSON string representation of {@link CashFlowRO} object
   * 
   * @throws DWalletCoreException
   *           if errors occur while trying to map {@link CashFlowRO} object to
   *           JSON string
   */
  public String mapCashFlowToJson(CashFlowRO cashFlow) throws DWalletCoreException {
    String output = null;

    try {
      output = jsonMapper.writeValueAsString(cashFlow);
    } catch (JsonParseException e) {
      throw new DWalletCoreException(e);
    } catch (JsonMappingException e) {
      throw new DWalletCoreException(e);
    } catch (IOException e) {
      throw new DWalletCoreException(e);
    }

    return output;
  }

  /**
   * A method for getting {@link CashRecordRO} object from JSON string.
   * 
   * @param json
   *          - JSON string
   * 
   * @return {@link CashRecordRO} object
   * 
   * @throws DWalletCoreException
   *           if errors occur while trying to get {@link CashRecordRO} object
   *           from JSON string
   */
  public CashRecordRO getCashRecordFromJson(String json) throws DWalletCoreException {
    CashRecordRO output = null;

    try {
      output = jsonMapper.readValue(json, CashRecordRO.class);
    } catch (JsonParseException e) {
      throw new DWalletCoreException(e);
    } catch (JsonMappingException e) {
      throw new DWalletCoreException(e);
    } catch (IOException e) {
      throw new DWalletCoreException(e);
    }

    return output;
  }

  /**
   * A method for mapping {@link CashRecordRO} object to JSON string
   * 
   * @param cashRecord
   *          - {@link CashRecordRO} object to map
   * 
   * @return JSON string representation of {@link CashRecordRO} object
   * 
   * @throws DWalletCoreException
   *           if errors occur while trying to map {@link CashRecordRO} object
   *           to JSON string
   */
  public String mapCashRecordToJson(CashRecordRO cashRecord) throws DWalletCoreException {
    String output = null;

    try {
      output = jsonMapper.writeValueAsString(cashRecord);
    } catch (JsonParseException e) {
      throw new DWalletCoreException(e);
    } catch (JsonMappingException e) {
      throw new DWalletCoreException(e);
    } catch (IOException e) {
      throw new DWalletCoreException(e);
    }

    return output;
  }

  /**
   * A method for getting {@link CashBalanceRO} object from JSON string.
   * 
   * @param json
   *          - JSON string
   * 
   * @return {@link CashBalanceRO} object
   * 
   * @throws DWalletCoreException
   *           if errors occur while trying to get {@link CashBalanceRO} object
   *           from JSON string
   */
  public CashBalanceRO getCashBalanceFromJson(String json) throws DWalletCoreException {
    CashBalanceRO output = null;

    try {
      output = jsonMapper.readValue(json, CashBalanceRO.class);
    } catch (JsonParseException e) {
      throw new DWalletCoreException(e);
    } catch (JsonMappingException e) {
      throw new DWalletCoreException(e);
    } catch (IOException e) {
      throw new DWalletCoreException(e);
    }

    return output;
  }

  /**
   * A method for mapping {@link CashBalanceRO} object to JSON string
   * 
   * @param cashBalance
   *          - {@link CashBalanceRO} object to map
   * 
   * @return JSON string representation of {@link CashBalanceRO} object
   * 
   * @throws DWalletCoreException
   *           if errors occur while trying to map {@link CashBalanceRO} object
   *           to JSON string
   */
  public String mapCashBalanceToJson(CashBalanceRO cashBalance) throws DWalletCoreException {
    String output = null;

    try {
      output = jsonMapper.writeValueAsString(cashBalance);
    } catch (JsonParseException e) {
      throw new DWalletCoreException(e);
    } catch (JsonMappingException e) {
      throw new DWalletCoreException(e);
    } catch (IOException e) {
      throw new DWalletCoreException(e);
    }

    return output;
  }

}
