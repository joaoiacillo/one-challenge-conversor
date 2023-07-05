package com.john.gui;

import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.john.currency.Currencies;
import com.john.currency.Currency;

public class CurrencySelectorDialog {

	private String[] currenciesIso;
	private Window owner;
	
	private String[] tableColumns = { "Moeda", "Código ISO", "Símbolo", "Valor Base" };
	private Object[][] tableData;
	
	private JTable table;
	private JScrollPane pane;
	
	public CurrencySelectorDialog(Window owner) {
		
		this.owner = owner;
		this.loadTableData();
		
		this.table = new JTable(tableData, tableColumns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.pane = new JScrollPane(this.table);
		
	}
	
	private void loadTableData() {
		
		Set<String> currenciesIsoSet = Currencies.getCurrenciesISO(); 
		this.currenciesIso = currenciesIsoSet.toArray(new String[currenciesIsoSet.size()]);
		this.tableData = new Object[currenciesIso.length][tableColumns.length];
		
		for (int i = 0; i < currenciesIso.length; i++) {
			Currency currency = Currencies.get(currenciesIso[i]);
			
			String name = currency.getName();
			String iso = currency.getIsoCode();
			String symbol = currency.getSymbol();
			String baseValue = symbol + currency.getBaseCurrencyAmount();
			
			this.tableData[i] = new Object[] {name, iso, symbol, baseValue};
		}
		
	}
	
	private int showErrorDialog() {
		return JOptionPane.showConfirmDialog(
				this.owner,
				"Escolha uma moeda da tabela ou pressione em CANCELAR.",
				"Operação inválida!",
				JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.ERROR_MESSAGE
		);
	}
	
	private int getIsoRow(String iso) {
		for (int i = 0; i < tableData.length; i++) {
			Object[] currencyData = this.tableData[i];
			if (currencyData[1] == iso) return i;
		}
		return 0;
	}
	
	public Currency showDialog(String initialIso) {
		int initialIsoIndex = this.getIsoRow(initialIso);
		this.table.setRowSelectionInterval(initialIsoIndex, initialIsoIndex);
		
		while (true) {
			int dialogResult = JOptionPane.showConfirmDialog(
					this.owner,
					this.pane,
					"Selecione a moeda abaixo:",
					JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE,
					null
			);
			
			if (dialogResult != JOptionPane.OK_OPTION) break;
			
			int selectedRow = this.table.getSelectedRow();
			
			// Selection out of range / Not selected
			if (selectedRow < 0) {
				if (this.showErrorDialog() == JOptionPane.CANCEL_OPTION) break;
				continue;
			}
			
			String selectedIso = this.currenciesIso[selectedRow];
			
			return Currencies.get(selectedIso);
		}
		
		return null;
		
	}
	
}
