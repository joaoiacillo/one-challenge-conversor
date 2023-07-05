package com.john.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import javax.swing.*;

import com.john.api.APIHandler;
import com.john.currency.Currencies;
import com.john.currency.Currency;
import com.john.currency.CurrencyConverter;

// TODO turn this into a dialog
public class ConversorDeMoedasWindow extends JDialog {
	
	private JPanel mainPanel;
	private JPanel borderPanel;
	private JPanel inputsPanel;
	
	private JTextField valueField;
	private CurrencySelector sourceCurrencySelector;
	private CurrencySelector targetCurrencySelector;
	private JButton switchBtn;
	
	private JButton convertBtn;
	
	public ConversorDeMoedasWindow(JFrame owner) {
		super(owner);
		this.setTitle("Conversor de Moedas");
		this.setSize(new Dimension(800, 150));
		this.setResizable(false);
		
		this.getContentPane().setLayout(new FlowLayout());
		this.inputsPanel = new JPanel(new FlowLayout());
		
		this.valueField = new JTextField("0");
		this.valueField.setPreferredSize(new Dimension(100, this.valueField.getPreferredSize().height));
		this.inputsPanel.add(this.valueField);
		
		this.sourceCurrencySelector = new CurrencySelector(owner, APIHandler.BASE_CURRENCY);
		this.inputsPanel.add(this.sourceCurrencySelector.getButton());
		
		this.switchBtn = new JButton();
		
		try {
			this.switchBtn.setIcon(new ImageIcon(new URL("https://cdn-icons-png.flaticon.com/16/466/466154.png")));
		} catch (Exception e) {
			// Fallback to text if image can't be displayed.
			this.switchBtn.setText("<- Trocar ->");
		}
		
		this.switchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				sourceCurrencySelector.switchWith(targetCurrencySelector);
				
			}
			
		});
		
		this.inputsPanel.add(this.switchBtn);
		
		this.targetCurrencySelector = new CurrencySelector(owner, APIHandler.BASE_CURRENCY);
		this.inputsPanel.add(this.targetCurrencySelector.getButton());
		
		
		ConversorDeMoedasWindow _this = this;
		
		this.borderPanel = new JPanel(new BorderLayout());
		
		this.convertBtn = new JButton("Converter");
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
				
				Currency source = sourceCurrencySelector.getSelectedCurrency();
				Currency target = targetCurrencySelector.getSelectedCurrency();
				
				CurrencyConverter.setSourceCurrency(source);
				CurrencyConverter.setTargetCurrency(target);
				
				double convertedValue = CurrencyConverter.convert(value);
				
				String sourceSymbol = source.getSymbol();
				String targetSymbol = target.getSymbol();
				
				String msg = sourceSymbol + value + " = " + targetSymbol + convertedValue;
				String title = "Conversão de " + source.getIsoCode() + " para " + target.getIsoCode() + ":";
				
				JOptionPane.showMessageDialog(
						_this,
						msg,
						title,
						JOptionPane.PLAIN_MESSAGE
				);
				
			}
			
		});
		
		
		this.borderPanel.add(convertBtn, BorderLayout.CENTER);
		this.borderPanel.add(inputsPanel, BorderLayout.NORTH);
		
		this.getContentPane().add(borderPanel);
		this.setLocationRelativeTo(null);
	}
	
//	private class CurrencyComboBox extends JComboBox<String> {
//		// Index 0 -> USD -> "USD - American Dollar" 
//		private Set<String> currenciesISO = Currencies.getCurrenciesISO();
//		
//		private String[] currenciesArray = currenciesISO.toArray(new String[currenciesISO.size()]);
//		
//		public CurrencyComboBox() {
//			for (String option : createComboBoxOptions()) {
//				addItem(option);
//			}
//		}
//		
//		private String createCurrencyString(String iso) {
//			String currencyName = Currencies.get(iso).getName();
//			return iso + " - " + currencyName;
//		}
//		
//		private String[] createComboBoxOptions() {
//			String[] options = new String[currenciesArray.length];
//			for (int i = 0; i < options.length; i++) {
//				options[i] = createCurrencyString(currenciesArray[i]);
//			}
//			return options;
//		}
//		
//		public String getSelectedCurrency() {
//			return currenciesArray[getSelectedIndex()];
//		}
//	}
	
}
