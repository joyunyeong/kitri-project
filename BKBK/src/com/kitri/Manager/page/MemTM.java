package com.kitri.Manager.page;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MemTM extends DefaultTableModel{
	public Vector list = new Vector(); 
	public Vector header = new Vector();
	public JTable memT;
	
	public MemTM() {
		header.add("No");
		header.add("ID");
		header.add("이름");
		header.add("전화번호");
		header.add("주소");
		header.add("생년월일");
		header.add("천원할인쿠폰");
		header.add("마지막 이용일");
		header.add("대출현황");
		
		setDataVector(list, header);
		memT = new JTable(this);
		memT.setFillsViewportHeight(true);
	
		memT.setSelectionMode(getRowCount());
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
