package com.kitri.Statistics.chart;


import javax.swing.JPanel;

import com.kitri.Main.frame.MainFrame;
import com.kitri.Manager.main.MgmtController;

import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StatisticsPanel extends JPanel implements ActionListener{

//	private JPanel contentPane;
	
	JPanel panel = new JPanel();
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;

	CardLayout card = new CardLayout();
	JPanel panel2 = new JPanel();
	public ChartMember c1 = new ChartMember();
	ChartFood c2 = new ChartFood();
	ChartBook c3 = new ChartBook();

//---------------------------------------------------------------------------------------------------------------------[h]
	
	public MgmtController mgmtController;
	
	/**
	 * Create the panel.
	 */
	public StatisticsPanel(MgmtController mgmtController) {
		
		this.mgmtController = mgmtController;
		
//----------------------------------------------------------------------------------------------------------------------[h]
		
		
		panel.setBackground(Color.WHITE);
		panel.setBounds(17, 15, 1419, 767);
		panel.setLayout(null);
		
		b1 = new JButton("\uD68C\uC6D0\uD1B5\uACC4");
		b1.setFont(new Font("굴림", Font.BOLD, 20));
		b1.setBackground(SystemColor.activeCaption);
		b1.setBounds(48, 53, 125, 50);
		panel.add(b1);
		
		b2 = new JButton("\uC0C1\uD488\uD1B5\uACC4");
		b2.setBackground(SystemColor.activeCaption);
		b2.setFont(new Font("굴림", Font.BOLD, 20));
		b2.setBounds(48, 113, 125, 50);
		panel.add(b2);
		
		b3 = new JButton("\uB3C4\uC11C\uD1B5\uACC4");
		b3.setBackground(SystemColor.activeCaption);
		b3.setAutoscrolls(true);
		b3.setInheritsPopupMenu(true);
		b3.setFont(new Font("굴림", Font.BOLD, 20));
		b3.setBounds(48, 173, 125, 50);
		panel.add(b3);
		
		b4 = new JButton("관리자 홈");
		b4.setBackground(SystemColor.activeCaption);
		b4.setFont(new Font("굴림", Font.BOLD, 17));
		b4.setBounds(25, 700, 125, 40);
		panel.add(b4);

		panel2.setLayout(card);
		c1.setBackground(Color.WHITE);
		panel2.add("Chart1", c1);
		panel2.add("Chart2", c2);
		panel2.add("Chart3", c3);
		card.show(panel2, "Chart1");     //시작하자마자 보여라 	
		
//		contentPane.add(panel);
		panel2.setBounds(173, 39, 1240, 713);

		panel.add(panel2);
		
		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(17, 15, 1419, 767);
		add(panel);
		
		//이벤트등록
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);

		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob == b1) {
			card.show(panel2, "Chart1");
		}else if(ob == b2) {
			card.show(panel2, "Chart2");
		}else if(ob == b3) {
			card.show(panel2, "Chart3");
//---------------------------------------------------------------------------------------------------------------------------------------[h]			
		}else if(ob == b4) {
			mgmtController.mm.mainFrame.serCard.show(mgmtController.mm.mainFrame.panelCard, "Manager");
//---------------------------------------------------------------------------------------------------------------------------------------[h]			
		}
	
	}

}
