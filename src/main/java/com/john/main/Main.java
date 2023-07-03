package com.john.main;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.john.conversor.gui.*;
import com.john.currency.Currencies;

public class Main {

	public static void main(String[ ] args ) {
		Currencies.load();
		useSystemLookAndFeel();
		SeletorWindow window = new SeletorWindow();
		window.setVisible(true);
	}
	
	private static void useSystemLookAndFeel() {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}
