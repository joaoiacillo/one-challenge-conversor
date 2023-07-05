package com.john.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.john.currency.Currencies;
import com.john.main.Main;

public class TestCurrencySelector {

	public static void main(String[] args) {
		
		Main.useSystemLookAndFeel();
		Currencies.load();
		JFrame frame = new JFrame("Teste");
		frame.getContentPane().setLayout(new FlowLayout());
		
		CurrencySelector cs = new CurrencySelector(frame, "USD");
		CurrencySelector cs2 = new CurrencySelector(frame, "BRL");
		
		JButton switchBtn = new JButton("Switch");
		switchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				cs.switchWith(cs2);
				
			}
			
		});
		
		frame.getContentPane().add(cs.getButton());
		frame.getContentPane().add(switchBtn);
		frame.getContentPane().add(cs2.getButton());
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}

}
