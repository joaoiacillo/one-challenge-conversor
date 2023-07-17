package com.oracle.one.conversor.temperature;

public class Celsius implements Temperature {

	@Override
	public double toCelsius(double value) {
		return value;
	}

	@Override
	public double toFahrenheit(double value) {
		return value * 9 / 5 + 32;
	}

	@Override
	public double toKelvin(double value) {
		return value + 273.15;
	}

}
