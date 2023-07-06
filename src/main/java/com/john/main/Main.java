package com.john.main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.john.currency.Currencies;
import com.john.gui.SelectorFrame;

public class Main {

	public static void main(String[ ] args ) {
		Currencies.load();
		useSystemLookAndFeel();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				SelectorFrame window = new SelectorFrame();
				window.setVisible(true);
			}
		});
	}

	public static void useSystemLookAndFeel() {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
