package com.oracle.one.conversor.main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.oracle.one.conversor.currency.Currencies;
import com.oracle.one.conversor.gui.SelectorFrame;

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
