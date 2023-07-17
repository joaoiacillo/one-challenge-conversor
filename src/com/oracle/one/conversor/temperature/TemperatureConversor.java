package com.oracle.one.conversor.temperature;

public class TemperatureConversor {

	public static enum Temperatures { CELSIUS, FAHRENHEIT, KELVIN }

	private static double convertCelsius(double value, Temperatures to) {
		switch (to) {
		case CELSIUS:
			return new Celsius().toCelsius(value);

		case FAHRENHEIT:
			return new Celsius().toFahrenheit(value);

		case KELVIN:
			return new Celsius().toKelvin(value);

		}

		return 0.0;
	}

	private static double convertFahrenheit(double value, Temperatures to) {
		switch (to) {
		case CELSIUS:
			return new Fahrenheit().toCelsius(value);

		case FAHRENHEIT:
			return new Fahrenheit().toFahrenheit(value);

		case KELVIN:
			return new Fahrenheit().toKelvin(value);

		}

		return 0.0;
	}

	private static double convertKelvin(double value, Temperatures to) {
		switch (to) {
		case CELSIUS:
			return new Kelvin().toCelsius(value);

		case FAHRENHEIT:
			return new Kelvin().toFahrenheit(value);

		case KELVIN:
			return new Kelvin().toKelvin(value);

		}

		return 0.0;
	}

	public static double convert(double value, Temperatures from, Temperatures to) {
		double result = 0.0;

		switch (from) {
		case CELSIUS:
			result = convertCelsius(value, to);
			break;

		case FAHRENHEIT:
			result = convertFahrenheit(value, to);
			break;

		case KELVIN:
			result = convertKelvin(value, to);
			break;

		}

		return Math.round(result * 100.0) / 100.0;
	}

}
