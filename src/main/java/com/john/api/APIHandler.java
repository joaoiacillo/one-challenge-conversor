package com.john.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

/**
 * Handles CurrencyAPI operations. 
 * @author João Iacillo
 */
public class APIHandler {
	
	// TODO Remove public API_KEY
	private final static String API_KEY = new PropsLoader().getProperty("api.key");
	private final static String API_VERSION = "v3";
	private final static String API_PROTOCOL_HOST = "https://api.currencyapi.com";
	
	private final static String BASE_CURRENCY = "USD";
	
	private static JSONObject getJson(String URL) {
		try {
			URL url = new URL(URL);
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("GET");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuilder response = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();
			
			connection.disconnect();
			return new JSONObject(response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static String createAPIURL(String endpoint) {
		return (
				API_PROTOCOL_HOST + "/" + API_VERSION + "/" + endpoint + 
				"?apikey=" + API_KEY
		);
	}
	
	private static String createAPILatestURL(String baseCurrency) {
		return createAPIURL("latest") + "&base_currency=" + baseCurrency;
	}

	public static JSONObject getCurrenciesJson() {
		return getJson(createAPIURL("currencies"));
	}
	
	public static JSONObject getLatestJson() {
		return getJson(createAPILatestURL(BASE_CURRENCY));
	}
	
}