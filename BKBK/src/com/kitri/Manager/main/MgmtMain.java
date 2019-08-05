package com.kitri.Manager.main;

// Manager Main Frame


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.border.*;

import com.kitri.Main.frame.MainFrame;
import com.kitri.Manager.page.*;
import com.kitri.Manager.popup.*;


public class MgmtMain extends JPanel {
	
//-------------------------------------------------------------------------------------------------------------------------------------------�����
	
	public JPanel cardP = new JPanel(); //�� ���������� ������ CardLayout �г�
	
	public JPanel panelR = new JPanel();
	public JButton foodMgmtB = new JButton("��ǰ����");
	public JButton bookMgmtB = new JButton("��������");
	public JButton membrMgmtB = new JButton("ȸ������");
	
	public CardLayout cards = new CardLayout();
	public FoodMgmtDesign fm = new FoodMgmtDesign();
	public BookMgmtDesign bm = new BookMgmtDesign();
	public MemberMgmtDesign mm = new MemberMgmtDesign();
	public FctgDialog fcAdd = new FctgDialog();
	public FsDialog fsAdd = new FsDialog();
	public BookDialog bAdd = new BookDialog();
	public MemDialog mAdd = new MemDialog();
	
	public MainFrame mainFrame;
	
//---------------------------------------------------------------
	
	public MgmtController mmc;
	
	
	
	/**
	 * Create the panel.
	 */
	public MgmtMain(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		mmc = new MgmtController(this);
		
//-------------------------------------------------------------------------------------------------------------------------------------------��ġ��
		
		
		//��ǰ����, �������� �г� ī�巹�̾ƿ��� ����
		cardP.setBounds(12, 10, 1374, 815);
		cardP.setLayout(cards);
		cardP.add(fm, "��ǰ����P");
		cardP.add(bm, "��������P");
		cardP.add(mm, "ȸ������P");
		cards.show(cardP, "��ǰ����P");
		
		//�� ���������� �̵���ư ����
		panelR.setBackground(SystemColor.window);
		panelR.setBounds(1386, 10, 96, 177);
		panelR.setLayout(new GridLayout(4, 0, 0, 0));
		btnD(foodMgmtB, SystemColor.controlHighlight, 16);
		btnD(bookMgmtB, SystemColor.controlHighlight, 16);
		btnD(membrMgmtB, SystemColor.controlHighlight, 16);
		panelR.add(new Label());
		panelR.add(foodMgmtB);
		panelR.add(bookMgmtB);
		panelR.add(membrMgmtB);
		
		//�� �г� ���� �гο� ����
		setBounds(0, 36, 1494, 835);
		setBackground(SystemColor.window);
		setLayout(null);
		add(cardP);
		add(panelR);
		
//-------------------------------------------------------------------------------------------------------------------------------------------��Ϻ�

		
		//�� ���� ������ �̵�
		foodMgmtB.addActionListener(mmc);
		bookMgmtB.addActionListener(mmc);
		membrMgmtB.addActionListener(mmc);
		
		//������ �α׾ƿ�
		fm.homeB.addActionListener(mmc);
		bm.homeB.addActionListener(mmc);
		mm.homeB.addActionListener(mmc);
		
		//���
		fm.statsB.addActionListener(mmc);
		bm.statsB.addActionListener(mmc);
		mm.statsB.addActionListener(mmc);
		
		//�����ڹ�ȣ����
		fm.mgmtNumB.addActionListener(mmc);
		bm.mgmtNumB.addActionListener(mmc);
		mm.mgmtNumB.addActionListener(mmc);
		
//-------------------------------------------------------------------------------------------------Food
		
		
		//�޴� ī�װ� card ��ȯ
		fm.ctgB1.addActionListener(mmc);
		fm.ctgB2.addActionListener(mmc);
		fm.ctgB3.addActionListener(mmc);
		fm.ctgB4.addActionListener(mmc);
		
		
		//�޴���ư
		for(JButton m : fm.menuB)
			m.addActionListener(mmc);
		
		
		//�޴� �߰�, ����, ����
		fcAdd.addB.addActionListener(mmc);
		fcAdd.mdfB.addActionListener(mmc);
		fcAdd.delB.addActionListener(mmc);
		fcAdd.cancelB.addActionListener(mmc);
		fcAdd.ctgC.addActionListener(mmc);
		
		//��� �˻�
		fm.selAllStock.addActionListener(mmc);
		fm.stockName.addActionListener(mmc);
		fm.findB.addActionListener(mmc);
		
		
		//��� �߰�, ����, ����
		fm.clrB.addActionListener(mmc);
		fm.addB.addActionListener(mmc);
		fsAdd.addB.addActionListener(mmc);
		fsAdd.cancelB.addActionListener(mmc);
		fm.delB.addActionListener(mmc);
		fsAdd.ctgC.addActionListener(mmc);
		
//-------------------------------------------------------------------------------------------------Book
		
		
		bm.selAllMenu.addActionListener(mmc);
		bm.findB.addActionListener(mmc);
		bm.findTF.addActionListener(mmc);
		
		
		//�߰�, ����, ���� btn
		bm.replace.addActionListener(mmc);
		bm.addB.addActionListener(mmc);
		bm.mdfyB.addActionListener(mmc);
		bm.delB.addActionListener(mmc);
		bAdd.addB.addActionListener(mmc);
		bAdd.mdfB.addActionListener(mmc);
		bAdd.cancelB.addActionListener(mmc);
		
		
//-----------------------------------------------------------------------------------------------Member	
		
		
		mm.selAllMem.addActionListener(mmc);
		mm.findB.addActionListener(mmc);
		mm.findC.addActionListener(mmc);
		mm.findTF.addActionListener(mmc);
		
		//�߰�, ����, ����
		mm.addB.addActionListener(mmc);
		mm.mdfyB.addActionListener(mmc);
		mm.delB.addActionListener(mmc);
		mAdd.addB.addActionListener(mmc);
		mAdd.mdfB.addActionListener(mmc);
		mAdd.cancelB.addActionListener(mmc);

		mAdd.ph2TF.addFocusListener(mmc);
		mAdd.ph3TF.addFocusListener(mmc);

		mAdd.birth1.addFocusListener(mmc);
		mAdd.birth2.addFocusListener(mmc);
		mAdd.birth3.addFocusListener(mmc);
		
		
//--------------------------------------------------------------
		
	}
	
	private void btnD(JButton b, Color c, int size) {	//��ư ������ method
		b.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		b.setFont(new Font("���� ���", Font.BOLD, size));
		if(c != null)
			b.setBackground(c);
	}
}
