package com.ddimitroff.projects.dwallet.xml.exchange;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Elements;
import nu.xom.Nodes;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

public class ExchangeRatesParser {

	private static final Logger logger = Logger.getLogger(ExchangeRatesParser.class);
	private static final String FIXED_RATES_FILEPATH = System.getenv("CATALINA_HOME") + "/webapps/d-wallet-web/exchange/Fixed_Rates.xml";
	private static final String EXCHANGE_RATES_FILEPATH = System.getenv("CATALINA_HOME") + "/webapps/d-wallet-web/exchange/Exchange_Rates.xml";
	private static final String EXCHANGE_RATES_DOWNLOAD_URL = "http://www.bnb.bg/Statistics/StExternalSector/StExchangeRates/StERForeignCurrencies/index.htm?download=xml&search=&lang=BG";

	private static Builder builder;
	private static Elements rates;

	public ExchangeRatesParser() {
		builder = new Builder();
	}

	public Double getExchangeRateByCurrency(String currency) {
		for (int i = 0; i < rates.size(); i++) {
			if (currency.equalsIgnoreCase(rates.get(i).getChildElements("CODE").get(0).getValue())) {
				return Double.parseDouble(rates.get(i).getChildElements("RATE").get(0).getValue());
			} else {
				continue;
			}
		}

		return null;
	}

	public void refreshExchangeRates() {
		try {
			File fixedRatesFile = new File(FIXED_RATES_FILEPATH);
			File exchangeRatesFile = new File(EXCHANGE_RATES_FILEPATH);

			Document allRates = builder.build(fixedRatesFile);
			Document exchangeRates = builder.build(exchangeRatesFile);

			Nodes exchangeRateNodes = exchangeRates.getRootElement().query("ROW");
			for (int i = 0; i < exchangeRateNodes.size(); i++) {
				exchangeRateNodes.get(i).detach();
				allRates.getRootElement().appendChild(exchangeRateNodes.get(i));
			}

			rates = allRates.getRootElement().getChildElements();
		} catch (ValidityException ve) {
			logger.error("Invalid XML document. ", ve);
		} catch (ParsingException pe) {
			logger.error("Error parsing XML document. ", pe);
		} catch (IOException ioe) {
			logger.error("Error opening XML document file. ", ioe);
		}
	}

	@Scheduled(cron = "0 30 16 * * MON-FRI")
	public void downloadExchangeRates() {
		logger.info("Downloading exchange rates from BNB server...");
		BufferedInputStream in;
		try {
			in = new BufferedInputStream(new URL(EXCHANGE_RATES_DOWNLOAD_URL).openStream());
			FileOutputStream fos = new FileOutputStream(EXCHANGE_RATES_FILEPATH);
			int i;
			while ((i = in.read()) != -1) {
				fos.write(i);
			}
			fos.flush();
			fos.close();
			in.close();
		} catch (MalformedURLException mue) {
			logger.error("Unable to download exchange rates from specified URL. ", mue);
		} catch (IOException ioe) {
			logger.error("Unable to create download file. ", ioe);
		}

		refreshExchangeRates();
	}

}
