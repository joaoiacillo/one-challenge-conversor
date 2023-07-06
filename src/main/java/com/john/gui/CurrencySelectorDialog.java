package com.john.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.john.currency.Currencies;
import com.john.currency.Currency;

public class CurrencySelectorDialog {

	private String[] currenciesIso;
	private Window owner;
	
	private String[] tableColumns = { "Moeda", "Código ISO", "Símbolo", "Valor Base" };
	private Object[][] tableData;
	
	private JTextField searchField;
	private JTable table;
	
	private JPanel searchPanel;
	private JScrollPane pane;
	
	private TableRowSorter<TableModel> rowSorter;
	
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
		
		this.rowSorter = new TableRowSorter<>(this.table.getModel());
		this.table.setRowSorter(rowSorter);;
		
		this.pane = new JScrollPane(this.table);
		
		this.searchPanel = new JPanel();
		this.searchField = new JTextField();
		this.searchField.setPreferredSize(new Dimension(150, this.searchField.getPreferredSize().height));
		
		this.searchField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				String text = searchField.getText();
				
				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				String text = searchField.getText();
				
				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				 throw new UnsupportedOperationException("Not supported yet.");
			}
			
		});
		
		this.searchPanel.add(new JLabel("Filtro de pesquisa: "));
		this.searchPanel.add(this.searchField);
		
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
		this.searchField.setText("");
		this.table.setRowSelectionInterval(initialIsoIndex, initialIsoIndex);
		
		while (true) {
			int dialogResult = JOptionPane.showConfirmDialog(
					this.owner,
					new JComponent[] {
							this.searchPanel,
							this.pane
					},
					"Selecione a moeda abaixo:",
					JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE,
					null
			);
			
			if (dialogResult != JOptionPane.OK_OPTION) break;

			int selectedRow = this.table.getSelectedRow();
			
			int modelRow = this.table.convertRowIndexToModel(selectedRow);
			TableModel model = this.table.getModel();
			
			// Selection out of range / Not selected
			if (selectedRow < 0) {
				if (this.showErrorDialog() == JOptionPane.CANCEL_OPTION) break;
				continue;
			}
			
			Currency selectedCurrency = Currencies.get(model.getValueAt(modelRow, 1).toString());
			
			return selectedCurrency;
		}
		
		return null;
		
	}
	
}
