package com.oracle.one.conversor.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SelectorFrame extends JFrame {

	public SelectorFrame() {
		super("Conversores");
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new FlowLayout());
		JLabel label = new JLabel("Escolha um conversor abaixo:", SwingConstants.CENTER);
		labelPanel.add(label);
		contentPane.add(labelPanel, BorderLayout.NORTH);

		JPanel buttonsPanel = new JPanel(new GridLayout(2, 1));

		SelectorFrame _this = this;

		// Conversor de Moeda
		JButton currencyBtn = new JButton(" Conversor de Moedas");
		try {
			currencyBtn.setIcon(new ImageIcon(new URL("https://cdn-icons-png.flaticon.com/64/8887/8887786.png")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buttonsPanel.add(currencyBtn);

		currencyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ConversorDeMoedasWindow dialog = new ConversorDeMoedasWindow(_this);
				dialog.setVisible(true);
			}
		});

		// Conversor de Temperatura
		JButton temperatureBtn = new JButton(" Conversor de Temperatura");
		try {
			temperatureBtn.setIcon(new ImageIcon(new URL("https://cdn-icons-png.flaticon.com/64/2387/2387835.png")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buttonsPanel.add(temperatureBtn);

		temperatureBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ConversorDeTemperaturaWindow dialog = new ConversorDeTemperaturaWindow(_this);
				dialog.setVisible(true);
			}
		});

		contentPane.add(buttonsPanel, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(null);
	}

}
