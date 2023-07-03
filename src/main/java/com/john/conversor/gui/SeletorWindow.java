package com.john.conversor.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SeletorWindow extends JFrame {

	public SeletorWindow() {
		super("Seletor de Conversor");
//		setSize(500, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
		
		contentPane.setLayout(new BorderLayout());
		
		contentPane.add(new JLabel("Escolha um conversor abaixo:", SwingConstants.CENTER), BorderLayout.NORTH);
		
		JPanel buttonsPanel = new JPanel(new GridLayout(2, 1));
		
		SeletorWindow _this = this;
		
		// Conversor de Moeda
		JButton currencyBtn = new JButton(" Conversor de Moedas");
		currencyBtn.setIcon(getResizedIcon("https://cdn-icons-png.flaticon.com/128/8887/8887786.png"));
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
		temperatureBtn.setIcon(getResizedIcon("https://cdn-icons-png.flaticon.com/128/2387/2387835.png"));
		buttonsPanel.add(temperatureBtn);
		
		contentPane.add(buttonsPanel, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(null);
	}
	
	private ImageIcon getResizedIcon(String url) {
		URL iconURL;
		try {
			iconURL = new URL(url);
			ImageIcon originalIcon = new ImageIcon(iconURL);
			Image originalImage = originalIcon.getImage();
			Image resizedImage = originalImage.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
			return new ImageIcon(resizedImage);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
