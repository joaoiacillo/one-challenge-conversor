package com.john.temperature;

public class Kelvin implements Temperature {

	@Override
	public double toCelsius(double value) {
		return value - 273.15;
	}

	@Override
	public double toFahrenheit(double value) {
		return (value -	273.15) * 9 / 5 + 32;
	}

	@Override
	public double toKelvin(double value) {
		return value;
	}

}
