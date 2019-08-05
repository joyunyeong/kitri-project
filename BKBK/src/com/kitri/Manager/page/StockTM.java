package com.kitri.Manager.page;

import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.kitri.Manager.data.StockDto;

public class StockTM extends DefaultTableModel{

	public Vector list = new Vector(); 
	public Vector header = new Vector();
	public JTable stockT;
	
	
	public StockTM() {
	
		header.add("No");
		header.add("카테고리");
		header.add("품목");
		header.add("남은 수량");
		header.add("단가");
		header.add("비고");
		
		setDataVector(list, header);
		stockT = new JTable(this);
		stockT.setFillsViewportHeight(true);
		stockT.setSelectionMode(getRowCount());
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
}