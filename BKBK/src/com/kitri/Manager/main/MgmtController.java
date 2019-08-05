package com.kitri.Manager.main;

import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;


public class MgmtController implements ActionListener, FocusListener  {

	// field
	public MgmtMain mm;
	public MgmtService mms;
	
	// constructor
	public MgmtController(MgmtMain m) {
		 mm = m;
		 mms = new MgmtService(this);
	}

	
	// listener Oberride
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		String btnStr = e.getActionCommand();
		
//--------------------------------------------------------------------------------------------------menu btn 
		
		int len = mm.fm.menuB.length;
		for (int i = 0; i < len; i++) {
			if(ob == mm.fm.menuB[i]) { 
				mms.setM(e.getActionCommand());
				return;
			}
		}
		
//-------------------------------------------------------------------------------------------------�� ���� ������		
		
		if(ob == mm.foodMgmtB) {
			mms.foodPage();
		} else if(ob == mm.bookMgmtB) {
			mms.bookPage();
		} else if(ob == mm.membrMgmtB) {
			mms.memPage();
		} else if(btnStr.equals("������ �α׾ƿ�")) {
			mm.mainFrame.serCard.show(mm.mainFrame.panelCard, "Main");
		} else if(btnStr.equals("������ȣ����")) {
			mms.mgmtNum();
		} else if(btnStr.equals("���")) {
			mm.mainFrame.serCard.show(mm.mainFrame.panelCard, "Statistics");
//---------------------------------------------------------------------------------------------------------------------------------------Food �̺�Ʈ			

			
//-----------------------------------------------------------------------------------------------------menu
			
		} else if(ob == mm.fm.ctgB1 || ob == mm.fm.ctgB2 || ob == mm.fm.ctgB3 || ob == mm.fm.ctgB4) {
			mms.setMenuB(e.getActionCommand());
			mms.setStockT(e.getActionCommand());
			
//----------------------------------------------------------------------------------------------menu dialog
		
			
		} else if(ob == mm.fcAdd.ctgC) {
			mms.comboItem(mm.fcAdd.ctgC.getSelectedItem().toString());
		} else if(ob == mm.fcAdd.addB || ob == mm.fcAdd.mdfB) {
			mms.amMenu(e.getActionCommand());
		} else if(ob == mm.fcAdd.delB) {
			mms.delMenu();
		} else if(ob == mm.fcAdd.cancelB) {
			mms.cancleMenu();
		} else if(ob == mm.fm.selAllStock) {//��� �˻�
			mms.setStockT("all");
		} else if(ob == mm.fm.findB || ob == mm.fm.stockName) {
			mms.selStock();
		} else if(ob == mm.fm.clrB)  {//���̺� ���� ���
			mm.fm.stockTM.stockT.clearSelection();
		} else if(ob == mm.fm.addB)  {// ��� �԰�
			mms.setS();
		} else if(ob == mm.fsAdd.addB) {
			mms.addStock();
		} else if(ob == mm.fsAdd.cancelB) {
			mms.cancleStock();
		} else if(ob == mm.fm.delB) {
			mms.delStock();
			
//---------------------------------------------------------------------------------------------------------------------------------------Book �̺�Ʈ
		} else if(ob == mm.bm.selAllMenu) {
			mms.setBT();
		} else if(ob == mm.bm.findB || ob == mm.bm.findTF) {
			mms.serchB();
		} else if(ob == mm.bm.addB) {
			mms.addB();
		} else if(ob == mm.bm.mdfyB) {
			mms.mdfB();
		} else if(ob == mm.bm.delB) {
			mms.delBook();
		} else if(ob  == mm.bm.replace) {
			mms.replaceBook();
		} else if(ob == mm.bAdd.addB || ob == mm.bAdd.mdfB) {//-----------------------------------book dialog
			mms.amBook(e.getActionCommand());
		} else if(ob == mm.bAdd.cancelB) {
			mms.cancleBook();
		} else if(ob == mm.mm.selAllMem) {//------------------------------------------------------------------Member �̺�Ʈ
			mms.selMem();
		} else if(ob == mm.mm.findB || ob == mm.mm.findTF) {
			mms.serchM();
		} else if(ob == mm.mm.addB) {
			mms.addM();
		} else if(ob == mm.mm.mdfyB) {
			mms.mdfM();
		} else if(ob == mm.mm.delB) {
			mms.delM();
		} else if(ob == mm.mAdd.addB || ob == mm.mAdd.mdfB) {
			mms.amMem(e.getActionCommand());
		} else if(ob == mm.mAdd.cancelB) {
			mms.cancleMem();
		}

	}

	@Override
	public void focusGained(FocusEvent e) {
		Object ob = e.getSource();
		if(ob == mm.mAdd.ph2TF || ob == mm.mAdd.ph3TF)
			mm.mAdd.infoPhoneNum.setText("ex) 010 - 1234 - 5678"); 
		if(ob == mm.mAdd.birth1 || ob == mm.mAdd.birth2 || ob == mm.mAdd.birth3 )
			mm.mAdd.infoBirth.setText("ex) 2000�� 01�� 01��"); 
	}

	@Override
	public void focusLost(FocusEvent e) {
		Object ob = e.getSource();
		if(ob == mm.mAdd.ph2TF || ob == mm.mAdd.ph3TF)
			mm.mAdd.infoPhoneNum.setText(""); 
		if(ob == mm.mAdd.birth1 || ob == mm.mAdd.birth2 || ob == mm.mAdd.birth3 )
			mm.mAdd.infoBirth.setText("");		
	}


}