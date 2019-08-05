package com.kitri.Manager.page;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BookTM extends DefaultTableModel {
	public Vector list = new Vector(); 
	public Vector header = new Vector();
	public JTable bookT;
	
	public BookTM() {
		header.add("No");
		header.add("도서종류");
		header.add("장르");
		header.add("도서명");
		header.add("작가명");
		header.add("출판사");
		header.add("정가");
		header.add("보유현황");
		header.add("비고");
		
		setDataVector(list, header);
		bookT = new JTable(this);
		bookT.setFillsViewportHeight(true);
		bookT.setSelectionMode(getRowCount());
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
