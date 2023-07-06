package com.john.temperature;

public class Fahrenheit implements Temperature {

	@Override
	public double toCelsius(double value) {
		return (value - 32) * 5 / 9;
	}

	@Override
	public double toFahrenheit(double value) {
		return value;
	}

	@Override
	public double toKelvin(double value) {
		return (value - 32) * 5 / 9 + 273.15;
	}

}
