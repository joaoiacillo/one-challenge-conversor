package com.john.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.john.currency.Currency;
import com.john.currency.CurrencyConverter;
import com.john.temperature.TemperatureConversor;
import com.john.temperature.TemperatureConversor.Temperatures;

public class ConversorDeTemperaturaWindow extends JDialog {
	
	private TemperatureSelector fromSelector = new TemperatureSelector("De");
	private TemperatureSelector toSelector = new TemperatureSelector("Para");
	
	private JTextField valueField = new JTextField("0");
	
	private JButton convertBtn = new JButton("Converter");

	public ConversorDeTemperaturaWindow(JFrame owner) {
		
		super(owner);
		this.setTitle("Conversor de Temperatura");
		this.setResizable(false);
		
		this.getContentPane().setLayout(new BorderLayout());
		
		JPanel inputsPanel = new JPanel();
		
		inputsPanel.add(fromSelector.getPanel());
		inputsPanel.add(valueField);
		inputsPanel.add(toSelector.getPanel());
		
		valueField.setPreferredSize(new Dimension(100, valueField.getPreferredSize().height));
		
		JPanel btnPanel = new JPanel();
		btnPanel.add(convertBtn);
		
		ConversorDeTemperaturaWindow _this = this;
		
		this.convertBtn.setPreferredSize(new Dimension(300, 50));
		
		this.convertBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				double value;
				
				try {
					value = Double.parseDouble(valueField.getText());
				} catch (NumberFormatException err) {
					JOptionPane.showMessageDialog(
							_this,
							"Por favor, insira um número na caixa.",
							"Entrada Inválida",
							JOptionPane.ERROR_MESSAGE
					);
					return;
				}
				
				Temperatures from = fromSelector.getSelectedTemperature();
				Temperatures to = toSelector.getSelectedTemperature();
				
				double convertedValue = TemperatureConversor.convert(value, from, to);
				
				String fromMetric = fromSelector.getSelectedTemperatureMetric();
				String toMetric = toSelector.getSelectedTemperatureMetric();
				
				String msg = "" + value + fromMetric.substring(0, 1) + " = " + convertedValue + toMetric.substring(0, 1);
				String title = "Conversão de " + fromMetric + " para " + toMetric + ":";
				
				JOptionPane.showMessageDialog(
						_this,
						msg,
						title,
						JOptionPane.PLAIN_MESSAGE
				);
				
			}
			
		});
		
		this.getContentPane().add(inputsPanel, BorderLayout.NORTH);
		this.getContentPane().add(btnPanel, BorderLayout.CENTER);
		
		this.pack();
		this.setLocationRelativeTo(null);
		
	}
	
}
