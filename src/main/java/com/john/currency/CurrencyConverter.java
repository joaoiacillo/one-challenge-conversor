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
	
	public static void setSourceCurrency(Currency sourceCurrency) {
		CurrencyConverter.sourceCurrency = sourceCurrency;
	}
	
	public static void setTargetCurrency(Currency targetCurrency) {
		CurrencyConverter.targetCurrency = targetCurrency;
	}
	
}
