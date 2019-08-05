package com.kitri.Manager.page;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import com.kitri.Manager.main.MgmtMain;
import com.kitri.Manager.popup.MemDialog;

public class MemberMgmtDesign extends JPanel {

	private JPanel panelC = new JPanel();
	private JPanel panelR = new JPanel();
	private JPanel panelR1 = new JPanel();
	public JButton selAllMem = new JButton("All");
	String[] str = {"이름", "전화번호", "생년월일"};
	public JComboBox findC = new JComboBox(str);
	public JTextField findTF = new JTextField();
	public JButton findB;

	private JPanel panelR2 = new JPanel();
	public MemTM memTM = new MemTM();
	
	private JPanel panelS = new JPanel();
	public JButton homeB = new JButton("관리자 로그아웃");
	public JButton statsB = new JButton("통계");
	public JButton addB = new JButton("추가");
	public JButton mdfyB = new JButton("수정");
	public JButton delB = new JButton("삭제");
	public JButton mgmtNumB = new JButton("관리번호수정");
	
	
	/**
	 * Create the panel.
	 */
	public MemberMgmtDesign() {

		//-----------------------------------------------------------상단 구성
		panelR1.setBackground(SystemColor.window);
		panelR1.setBounds(0, 0, 1374, 43);
		panelR1.setLayout(null);
		
		selAllMem.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		selAllMem.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		selAllMem.setBackground(SystemColor.controlHighlight);
		
		selAllMem.setBounds(0, 13, 82, 30);
		findC.setBounds(1069, 4, 103, 30);
		
		findTF.setBounds(1184, 4, 150, 30);
		findTF.setColumns(10);
		findB = new JButton() {
			@Override
			protected void paintComponent(Graphics g) {
				Dimension d = getSize();
				ImageIcon imageIcon = new ImageIcon(".\\src\\com\\kitri\\Manager\\image\\magnifying-glass.png");
				Image image = imageIcon.getImage();
				g.drawImage(image, 0, 0, d.width, d.height, null);
				setOpaque(true);
			}
		};
		findB.setBackground(SystemColor.window);
		findB.setBounds(1337, 4, 36, 29);
		
		panelR1.add(selAllMem);
		panelR1.add(findC);
		panelR1.add(findTF);
		panelR1.add(findB);
		
		//-------------------------------------------------------------하단 구성
		panelR2.setBounds(0, 47, 1374, 715);
		panelR2.setLayout(new BorderLayout(0, 0));

		//테이블 생성
//		String[] header = {"  ", "No", "이름", "전화번호", "주소", "생년월일", "천원할인쿠폰", "마지막 이용일", "대출현황", "비고"};
//		String[][] contents = { {"  ", "No","이징징", "010-1234-5678", "서울시 서울구 서울동 서울아파트 1234호", "생년월일", "천원할인쿠폰", "2019-04-07", "대출중", null},
//								{"  ", "No","조질라", "010-1234-5678", "서울시 서울구 서울동 서울아파트 1234호", "생년월일", "천원할인쿠폰", "2019-04-07", "대출중", null},
//								{"  ", "No","박쏘가리", "010-1234-5678", "서울시 서울구 서울동 서울아파트 1234호", "생년월일", "천원할인쿠폰", "2019-04-07", "대출중", null},
//								{"  ", "No","고질라", "010-1234-5678", "서울시 서울구 서울동 서울아파트 1234호", "생년월일", "천원할인쿠폰", "2019-04-07", "대출중", null}};
//		DefaultTableModel tM = new DefaultTableModel(contents, header);
//		JTable stockT = new JTable(memTM.memT);
//		stockT.setFillsViewportHeight(true);
		
		//테이블 부착
		JScrollPane tableP = new JScrollPane(memTM.memT); 
		panelR2.add(tableP);
		
		//패널부착
		panelR.setBounds(0, 0, 1374, 767);
		panelR.setBackground(SystemColor.window);
		panelR.setLayout(null);
		panelR.add(panelR1);
		panelR.add(panelR2);
		
		//---------------------------------------------------------------하단 버튼 부착
		panelS.setBackground(SystemColor.window);
		panelS.setBounds(0, 767, 1470, 49);
		panelS.setLayout(null);
		basicBD(homeB, SystemColor.controlHighlight, 12);
		basicBD(statsB, SystemColor.controlHighlight, 12);
		basicBD(addB, SystemColor.controlHighlight, 12);
		basicBD(mdfyB, SystemColor.controlHighlight, 12);
		basicBD(delB, SystemColor.controlHighlight, 12);
		basicBD(mgmtNumB, SystemColor.controlHighlight, 12);
		homeB.setBounds(0, 10, 121, 30);
		statsB.setBounds(250, 10, 75, 30);
		addB.setBounds(1125, 10, 75, 30);
		mdfyB.setBounds(1212, 10, 75, 30);
		delB.setBounds(1299, 10, 75, 30);
		mgmtNumB.setMargin(new Insets(2, 8, 2, 8));
		mgmtNumB.setBounds(133, 10, 105, 30);
		panelS.add(homeB);
		panelS.add(statsB);
		panelS.add(addB);
		panelS.add(mdfyB);
		panelS.add(delB);
		panelS.add(mgmtNumB);
		
		//----------------------------------------------------------------회원관리 메인 패널 구성
		panelC.setBackground(SystemColor.controlHighlight);
		panelC.setBounds(0, 0, 1374, 767);
		panelC.setLayout(null);
		panelC.add(panelR);
		
		setBounds(12, 10, 1374, 815);
		setLayout(null);
		add(panelC);
		add(panelS);

	}
	
	public void abBD(JButton b, Color c, int size) {
		b.setFont(new Font("맑은 고딕", Font.BOLD, size));
		if(c != null)
			b.setBackground(c);
	}
	
	private void basicBD(JButton b, Color c, int size) {	//----------------------------------버튼 디자인 method
		b.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		b.setFont(new Font("맑은 고딕", Font.BOLD, size));
		if(c != null)
			b.setBackground(c);
	}
}
