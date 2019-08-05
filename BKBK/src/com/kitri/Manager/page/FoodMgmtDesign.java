package com.kitri.Manager.page;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

public class FoodMgmtDesign extends JPanel {
	private JPanel panelC = new JPanel();
	private JPanel panelL = new JPanel();
	private JPanel panelL1 = new JPanel();
	public JButton ctgB1 = new JButton("음료");
	public JButton ctgB2 = new JButton("과자");
	public JButton ctgB3 = new JButton("라면");
	public JButton ctgB4 = new JButton("음식");
	
	private JPanel panelL21 = new JPanel();
	
	public JButton[] menuB = new JButton[25]; //음료버튼
	
	private JPanel panelR = new JPanel();
	public JButton selAllStock = new JButton("All");
	public JButton clrB = new JButton("선택취소");
	public JButton findB;
	public JTextField stockName = new JTextField();
	
	public StockTM stockTM = new StockTM();
	
	private JPanel panelR1 = new JPanel();
	private JPanel panelR2 = new JPanel();
	
	private JPanel panelS = new JPanel();
	public JButton homeB = new JButton("관리자 로그아웃");
	public JButton statsB = new JButton("통계");
	public JButton addB = new JButton("입고");
	public JButton delB = new JButton("삭제");
	public JButton mgmtNumB = new JButton("관리번호수정");
	
	
	/**
	 * Create the panel.
	 */
	public FoodMgmtDesign() {
		
		//----------------------------------------------------------왼쪽 패널 상단(메뉴카테고리 버튼) 구성
		panelL1.setBackground(SystemColor.window);
		panelL1.setBounds(0, 0, 675, 43);
		panelL1.setLayout(null);
		basicBD(ctgB1, SystemColor.controlHighlight, 12);
		basicBD(ctgB2, SystemColor.controlHighlight, 12);
		basicBD(ctgB3, SystemColor.controlHighlight, 12);
		basicBD(ctgB4, SystemColor.controlHighlight, 12);
		ctgB1.setBounds(0, 13, 82, 30);
		ctgB2.setBounds(82, 13, 82, 30);
		ctgB3.setBounds(164, 13, 82, 30);
		ctgB4.setBounds(247, 13, 82, 30);
		panelL1.add(ctgB1);
		panelL1.add(ctgB2);
		panelL1.add(ctgB3);
		panelL1.add(ctgB4);
		
//		menu btn
		int len = menuB.length;
		for (int i = 0; i < len; i++) {
			menuB[i] = new JButton("+");
			abBD(menuB[i], new Color(52, 152, 219), 35);
			menuB[i].setForeground(new Color(255, 255, 255));
			menuB[i].setVisible(false);
			panelL21.add(menuB[i]);
		}
		
		panelL.setBackground(SystemColor.window);
		panelL.setLayout(null);
		panelL.add(panelL1);

		panelC.setBackground(SystemColor.window);
		panelC.setBounds(0, 0, 1374, 767);
		panelC.setLayout(new GridLayout(0, 2, 0, 0));
		panelC.add(panelL);
		panelC.add(panelR);
		
		panelL21.setBackground(SystemColor.window);
		panelL21.setBounds(0, 46, 675, 716);
		panelL21.setLayout(new GridLayout(5, 5, 2, 2));
		panelL.add(panelL21);
		
		//-----------------------------------------------------------오른쪽 패널 상단 구성
		panelR1.setBackground(SystemColor.window);
		panelR1.setBounds(12, 0, 675, 43);
		panelR1.setLayout(null);
		
		basicBD(selAllStock, SystemColor.controlHighlight, 12);
		selAllStock.setBounds(0, 13, 82, 30);
		JLabel lblNewLabel = new JLabel("품목으로 검색");
		lblNewLabel.setBounds(390, 4, 82, 30);
		stockName.setBounds(485, 4, 150, 30);
		stockName.setColumns(10);
		findB = new JButton() {
			@Override
			protected void paintComponent(Graphics g) {
				Dimension d = getSize();
				ImageIcon imageIcon = new ImageIcon(".\\src\\com\\kitri\\Manager\\image\\magnifying-glass.png");
				Image image = imageIcon.getImage();
				g.drawImage(image, 0, 0, d.width, d.height, null);
				setOpaque(false);
			}
		};
		findB.setBackground(SystemColor.window);
		findB.setBounds(638, 4, 36, 29);
		
		panelR1.add(selAllStock);
		panelR1.add(lblNewLabel);
		panelR1.add(stockName); 
		panelR1.add(findB);
		
		//-------------------------------------------------------------오른쪽 패널 하단 구성
		panelR2.setBounds(12, 47, 675, 715);
		panelR2.setLayout(new BorderLayout(0, 0));
		
		
		//테이블 부착
		JScrollPane tableP = new JScrollPane(stockTM.stockT); 
		panelR2.add(tableP);

		panelR.setBackground(SystemColor.window);
		panelR.setLayout(null);
		panelR.add(panelR1);
		
		basicBD(clrB, SystemColor.controlHighlight, 12);
		clrB.setBounds(86, 13, 75, 30);
		panelR1.add(clrB);
		
		panelR.add(panelR2);
		
		//---------------------------------------------------------------하단 버튼 부착
		panelS.setBackground(SystemColor.window);
		panelS.setBounds(0, 767, 1470, 49);
		panelS.setLayout(null);
		basicBD(homeB, SystemColor.controlHighlight, 12);
		basicBD(statsB, SystemColor.controlHighlight, 12);
		basicBD(addB, SystemColor.controlHighlight, 12);
		basicBD(delB, SystemColor.controlHighlight, 12);
		basicBD(mgmtNumB, SystemColor.controlHighlight, 12);
		homeB.setBounds(0, 10, 121, 30);
		statsB.setBounds(250, 10, 75, 30);
		addB.setBounds(1212, 10, 75, 30);
		delB.setBounds(1299, 10, 75, 30);
		mgmtNumB.setMargin(new Insets(2, 8, 2, 8));
		mgmtNumB.setBounds(133, 10, 105, 30);
		panelS.add(homeB);
		panelS.add(statsB);
		panelS.add(addB);
		panelS.add(delB);
		panelS.add(mgmtNumB);

		//----------------------------------------------------------------상품관리 메인 패널 구성
		

		//----------------------------------------------------------------
		
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

