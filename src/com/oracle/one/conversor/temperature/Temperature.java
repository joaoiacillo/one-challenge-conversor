package com.oracle.one.conversor.temperature;

interface Temperature {
	public double toCelsius(double value);
	public double toFahrenheit(double value);
	public double toKelvin(double value);
}
