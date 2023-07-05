package com.john.gui;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.john.currency.Currencies;
import com.john.currency.Currency;

public class CurrencySelector {

	private CurrencySelectorDialog dialog;
	private JButton button;
	private Currency selectedCurrency;
	
	public CurrencySelector(Window dialogOwner, String initialCurrencyIso) {
		
		this.button = new JButton();
		this.button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				Currency currency = openDialog();
				if (currency == null) return;
				
				setSelectedCurrency(currency);
				
			}
			
		});
		
		this.dialog = new CurrencySelectorDialog(dialogOwner);
		
		this.setSelectedCurrency(Currencies.get(initialCurrencyIso));
	}
	
	private Currency openDialog() {
		return this.dialog.showDialog(this.selectedCurrency.getIsoCode());
	}
	
	public JButton getButton() {
		return button;
	}
	
	public void switchWith(CurrencySelector cs) {
		String iso = this.selectedCurrency.getIsoCode();
		setSelectedCurrency(cs.getSelectedCurrency());
		cs.setSelectedCurrency(Currencies.get(iso));
	}
	
	public void setSelectedCurrency(Currency currency) {
		String btnText = currency.getIsoCode() + " - " + currency.getName();
		this.button.setText(btnText);
		
		this.selectedCurrency = currency;
	}
	
	public Currency getSelectedCurrency() {
		return selectedCurrency;
	}
	
}
