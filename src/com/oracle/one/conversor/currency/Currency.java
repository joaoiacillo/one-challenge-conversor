package com.oracle.one.conversor.currency;

/**
 * Stores information of a country's currency.
 * @author João Iacillo
 */
public class Currency {

	private final String isoCode;
	private final String name;
	private final String symbol;

	private final double baseCurrencyAmount;

	public Currency(String isoCode, String name, String symbol, double baseCurrencyAmount) {
		this.isoCode = isoCode;
		this.name = name;
		this.symbol = symbol;
		this.baseCurrencyAmount = baseCurrencyAmount;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}

	public double getBaseCurrencyAmount() {
		return baseCurrencyAmount;
	}

}
