package com.kitri.Manager.main;

// Manager Main Frame


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.border.*;

import com.kitri.Main.frame.MainFrame;
import com.kitri.Manager.page.*;
import com.kitri.Manager.popup.*;
import com.kitri.Statistics.chart.StatisticsPanel;


public class MgmtMain extends JPanel {
	
//-------------------------------------------------------------------------------------------------------------------------------------------선언부
	
	public JPanel cardP = new JPanel(); //각 관리페이지 부착한 CardLayout 패널
	
	public JPanel panelR = new JPanel();
	public JButton foodMgmtB = new JButton("상품관리");
	public JButton bookMgmtB = new JButton("도서관리");
	public JButton membrMgmtB = new JButton("회원관리");
	
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
		
//-------------------------------------------------------------------------------------------------------------------------------------------배치부
		
		
		//상품관리, 도서관리 패널 카드레이아웃에 부착
		cardP.setBounds(12, 10, 1374, 815);
		cardP.setLayout(cards);
		cardP.add(fm, "상품관리P");
		cardP.add(bm, "도서관리P");
		cardP.add(mm, "회원관리P");
		cards.show(cardP, "상품관리P");
		
		//각 관리페이지 이동버튼 부착
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
		
		//각 패널 메인 패널에 부착
		setBounds(0, 36, 1494, 835);
		setBackground(SystemColor.window);
		setLayout(null);
		add(cardP);
		add(panelR);
		
//-------------------------------------------------------------------------------------------------------------------------------------------등록부

		
		//각 관리 페이지 이동
		foodMgmtB.addActionListener(mmc);
		bookMgmtB.addActionListener(mmc);
		membrMgmtB.addActionListener(mmc);
		
		//관리자 로그아웃
		fm.homeB.addActionListener(mmc);
		bm.homeB.addActionListener(mmc);
		mm.homeB.addActionListener(mmc);
		
		//통계
		fm.statsB.addActionListener(mmc);
		bm.statsB.addActionListener(mmc);
		mm.statsB.addActionListener(mmc);
		
		//관리자번호수정
		fm.mgmtNumB.addActionListener(mmc);
		bm.mgmtNumB.addActionListener(mmc);
		mm.mgmtNumB.addActionListener(mmc);
		
//-------------------------------------------------------------------------------------------------Food
		
		
		//메뉴 카테고리 card 전환
		fm.ctgB1.addActionListener(mmc);
		fm.ctgB2.addActionListener(mmc);
		fm.ctgB3.addActionListener(mmc);
		fm.ctgB4.addActionListener(mmc);
		
		
		//메뉴버튼
		for(JButton m : fm.menuB)
			m.addActionListener(mmc);
		
		
		//메뉴 추가, 수정, 삭제
		fcAdd.addB.addActionListener(mmc);
		fcAdd.mdfB.addActionListener(mmc);
		fcAdd.delB.addActionListener(mmc);
		fcAdd.cancelB.addActionListener(mmc);
		fcAdd.ctgC.addActionListener(mmc);
		
		//재고 검색
		fm.selAllStock.addActionListener(mmc);
		fm.stockName.addActionListener(mmc);
		fm.findB.addActionListener(mmc);
		
		
		//재고 추가, 수정, 삭제
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
		
		
		//추가, 수정, 삭제 btn
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
		
		//추가, 수정, 삭제
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
	
	private void btnD(JButton b, Color c, int size) {	//버튼 디자인 method
		b.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		b.setFont(new Font("맑은 고딕", Font.BOLD, size));
		if(c != null)
			b.setBackground(c);
	}
}
