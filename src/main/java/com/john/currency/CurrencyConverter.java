package com.john.currency;

public class CurrencyConverter {

	private static Currency sourceCurrency;
	private static Currency targetCurrency;
	
	public static double convert(double value) {
		// X(EUR) = V(BRL) / 1$(BRL) * 1$(EUR)
		
		if (sourceCurrency == null)
			throw new NullPointerException("Please provide a source currency");
		if (targetCurrency == null)
			throw new NullPointerException("Please provide a target currency");
		
		double sourceBaseCurrencyAmount = sourceCurrency.getBaseCurrencyAmount();
		double targetBaseCurrencyAmount = targetCurrency.getBaseCurrencyAmount();
		double result = value / sourceBaseCurrencyAmount * targetBaseCurrencyAmount;
		return Math.round(result * 100.0) / 100.0;
	}
	
	public static void setSourceCurrency(String newSourceCurrencyIso) {
		sourceCurrency = Currencies.get(newSourceCurrencyIso);
	}
	
	public static void setTargetCurrency(String newTargetCurrencyIso) {
		targetCurrency = Currencies.get(newTargetCurrencyIso);
	}
	
}
