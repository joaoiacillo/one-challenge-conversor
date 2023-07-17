package com.oracle.one.conversor.gui;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.oracle.one.conversor.temperature.TemperatureConversor.Temperatures;

public class TemperatureSelector {

	private JPanel panel = new JPanel();

	private ButtonGroup bg = new ButtonGroup();

	private Object[][] buttons = {
			{new JRadioButton("Celsius"), Temperatures.CELSIUS},
			{new JRadioButton("Fahrenheit"), Temperatures.FAHRENHEIT},
			{new JRadioButton("Kelvin"), Temperatures.KELVIN}
	};

	public TemperatureSelector(String title) {
		this.panel.setBorder(BorderFactory.createTitledBorder(title));

		((JRadioButton) buttons[0][0]).setSelected(true);

		for (Object[] btn : this.buttons) {
			bg.add((JRadioButton) btn[0]);
			panel.add((JRadioButton) btn[0]);
		}
	}

	public JPanel getPanel() {
		return panel;
	}

	public Temperatures getSelectedTemperature() {
		for (Object[] btn : this.buttons) {
			if (((JRadioButton) btn[0]).isSelected()) {
				return (Temperatures) btn[1];
			}
		}
		return null;
	}

	public String getSelectedTemperatureMetric() {
		for (Object[] btn : this.buttons) {
			if (((JRadioButton) btn[0]).isSelected()) {
				return ((JRadioButton) btn[0]).getText();
			}
		}
		return null;
	}

}
