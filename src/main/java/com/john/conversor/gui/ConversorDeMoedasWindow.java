package com.john.conversor.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.*;

import com.john.currency.Currencies;
import com.john.currency.CurrencyConverter;

// TODO turn this into a dialog
public class ConversorDeMoedasWindow extends JDialog {
	
	private JPanel mainPanel;
	private JPanel borderPanel;
	private JPanel inputsPanel;
	
	private JTextField valueField;
	private CurrencyComboBox sourceCurrencyCombo;
	private CurrencyComboBox targetCurrencyCombo;
	
	private JButton convertBtn;
	
	public ConversorDeMoedasWindow(JFrame owner) {
		super(owner);
		setTitle("Conversor de Moedas");
		setResizable(false);
		
		mainPanel = new JPanel(new FlowLayout());
		borderPanel = new JPanel(new BorderLayout());
		inputsPanel = new JPanel(new FlowLayout());
		
		sourceCurrencyCombo = new CurrencyComboBox();
		targetCurrencyCombo = new CurrencyComboBox();
		valueField = new JTextField("0");
		valueField.setPreferredSize(new Dimension(100, valueField.getPreferredSize().height));
		
		convertBtn = new JButton("Converter");
		convertBtn.setPreferredSize(new Dimension(300, 50));
		
		ConversorDeMoedasWindow _this = this;
		
		convertBtn.addActionListener(new ActionListener() {
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
				
				String source = sourceCurrencyCombo.getSelectedCurrency();
				String target = targetCurrencyCombo.getSelectedCurrency();
				
				CurrencyConverter.setSourceCurrency(source);
				CurrencyConverter.setTargetCurrency(target);
				
				double convertedValue = CurrencyConverter.convert(value);
				
				String sourceSymbol = Currencies.get(source).getSymbol();
				String targetSymbol = Currencies.get(target).getSymbol();
				
				String msg = sourceSymbol + value + " = " + targetSymbol + convertedValue;
				String title = "Conversão de " + source + " para " + target + ":";
				
				JOptionPane.showMessageDialog(
						_this,
						msg,
						title,
						JOptionPane.PLAIN_MESSAGE
				);
			}
		});
		
		inputsPanel.add(sourceCurrencyCombo);
		inputsPanel.add(valueField);
		inputsPanel.add(targetCurrencyCombo);
		
		borderPanel.add(inputsPanel, BorderLayout.NORTH);
		borderPanel.add(convertBtn, BorderLayout.CENTER);
		mainPanel.add(borderPanel);
		
		getContentPane().add(mainPanel);
		pack();
		setLocationRelativeTo(null);
	}
	
	private class CurrencyComboBox extends JComboBox<String> {
		// Index 0 -> USD -> "USD - American Dollar" 
		private Set<String> currenciesISO = Currencies.getCurrenciesISO();
		
		private String[] currenciesArray = currenciesISO.toArray(new String[currenciesISO.size()]);
		
		public CurrencyComboBox() {
			for (String option : createComboBoxOptions()) {
				addItem(option);
			}
		}
		
		private String createCurrencyString(String iso) {
			String currencyName = Currencies.get(iso).getName();
			return iso + " - " + currencyName;
		}
		
		private String[] createComboBoxOptions() {
			String[] options = new String[currenciesArray.length];
			for (int i = 0; i < options.length; i++) {
				options[i] = createCurrencyString(currenciesArray[i]);
			}
			return options;
		}
		
		public String getSelectedCurrency() {
			return currenciesArray[getSelectedIndex()];
		}
	}
	
}
