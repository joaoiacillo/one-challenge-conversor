package com.john.currency;

import java.util.HashMap;
import java.util.Set;

import org.json.JSONObject;

import com.john.api.APIHandler;

/**
 * Stores all available currencies.
 * @author João Iacillo
 */
public class Currencies {

	private static HashMap<String, Currency> currencies = new HashMap<>();

	private static void loadAPIContent() {
		JSONObject currenciesJson = APIHandler.getCurrenciesJson().getJSONObject("data");
		JSONObject latestJson = APIHandler.getLatestJson().getJSONObject("data");

		for (String isoCode : currenciesJson.keySet()) {
			JSONObject currencyJson = currenciesJson.getJSONObject(isoCode);
			double value = latestJson.getJSONObject(isoCode).getDouble("value");

			Currency currency = new Currency(
					isoCode,
					currencyJson.getString("name"),
					currencyJson.getString("symbol_native"),
					Math.round(value * 100.0) / 100.0
			);

			currencies.put(isoCode, currency);
		}
	}

	private static void loadDebugSampleCurrencies() {
		currencies.put("USD", new Currency("USD", "American Dollar", "$", 1));
		currencies.put("BRL", new Currency("BRL", "Brazilian Real", "R$", 4.79));
		currencies.put("EUR", new Currency("EUR", "Euro", "€", 0.92));
		currencies.put("JPY", new Currency("JPY", "Japan Yen", "¥", 144.40));
		currencies.put("RUB", new Currency("RUB", "Russian Ruble", "₽", 87.95));
	}

	/**
	 * Loads API currencies into the currencies map.
	 *
	 * Loads a small sample set of currencies if API is not enabled.
	 */
	public static void load() {
		if (!APIHandler.isEnabled()) {
			loadDebugSampleCurrencies();
			return;
		}

		loadAPIContent();
	}

	/**
	 * Gets a Currency object by it's ISO 4217 code.
	 * For example: USD, BRL, RUB, MXM, ...
	 * @param isoCode The currency ISO 4217 code.
	 * @return Currency
	 */
	public static Currency get(String isoCode) {
		return currencies.get(isoCode);
	}

	/**
	 * Gets a list of all the currencies ISO 4217 codes.
	 * @return Set<String> - Set of currencies ISO 4217 codes.
	 */
	public static Set<String> getCurrenciesISO() {
		return currencies.keySet();
	}

}
