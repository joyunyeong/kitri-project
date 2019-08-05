package com.kitri.Main.JoinMember;




import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.kitri.Main.frame.MainFrame;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;


public class JoinMemberFrame extends JFrame {

	public JPanel contentPane;
	public JTextField tfName;
	public JTextField tfBirth;
	public JTextField tfAddress;
	public JTextField tfPhoneNum1;
	public JTextField tfPhoneNum2;
	public JTextField tfPhoneNum3;
	JoinMemberFrameController jmfc;
	MainFrame mf;
	JButton buttonJoinMember;
	
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public JoinMemberFrame(MainFrame mf) {
		this.mf = mf;
		jmfc = new JoinMemberFrameController(this);
		
		setBounds(500, 250, 650, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelName = new JLabel("이름");
		labelName.setBounds(78, 100, 57, 15);
		contentPane.add(labelName);
		
		JLabel labelBirth = new JLabel("생년월일");
		labelBirth.setBounds(78, 156, 57, 15);
		contentPane.add(labelBirth);
		
		JLabel labelAddress = new JLabel("주소");
		labelAddress.setBounds(78, 213, 57, 15);
		contentPane.add(labelAddress);
		
		JLabel labelPhoneNum = new JLabel("전화번호");
		labelPhoneNum.setBounds(78, 270, 57, 15);
		contentPane.add(labelPhoneNum);
		
		tfName = new JTextField();
		tfName.setBounds(183, 97, 116, 21);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		tfBirth = new JTextField();
		tfBirth.setBounds(183, 153, 116, 21);
		contentPane.add(tfBirth);
		tfBirth.setColumns(10);
		
		tfAddress = new JTextField();
		tfAddress.setBounds(183, 210, 286, 21);
		contentPane.add(tfAddress);
		tfAddress.setColumns(10);
		
		tfPhoneNum1 = new JTextField();
		tfPhoneNum1.setBounds(183, 267, 57, 21);
		contentPane.add(tfPhoneNum1);
		tfPhoneNum1.setColumns(10);
		
		tfPhoneNum2 = new JTextField();
		tfPhoneNum2.setColumns(10);
		tfPhoneNum2.setBounds(271, 267, 57, 21);
		contentPane.add(tfPhoneNum2);
		
		tfPhoneNum3 = new JTextField();
		tfPhoneNum3.setColumns(10);
		tfPhoneNum3.setBounds(361, 267, 57, 21);
		contentPane.add(tfPhoneNum3);
		
		buttonJoinMember = new JButton("회원등록");
		buttonJoinMember.setForeground(SystemColor.controlLtHighlight);
		buttonJoinMember.setBackground(SystemColor.textHighlight);
		buttonJoinMember.setBounds(78, 387, 116, 37);
		contentPane.add(buttonJoinMember);
		
		JButton buttonCancel = new JButton("취소");
		buttonCancel.setForeground(SystemColor.controlLtHighlight);
		buttonCancel.setBackground(SystemColor.activeCaptionBorder);
		buttonCancel.setBounds(247, 387, 116, 37);
		contentPane.add(buttonCancel);
		
		JLabel lbBar = new JLabel("-");
		lbBar.setBounds(252, 270, 14, 15);
		contentPane.add(lbBar);
		
		JLabel label = new JLabel("-");
		label.setBounds(340, 270, 14, 15);
		contentPane.add(label);
		
		buttonJoinMember.addActionListener(jmfc);
		buttonCancel.addActionListener(jmfc);
		tfAddress.addActionListener(jmfc);
		tfBirth.addActionListener(jmfc);
		tfName.addActionListener(jmfc);
		tfPhoneNum1.addActionListener(jmfc);
		tfPhoneNum2.addActionListener(jmfc);
		tfPhoneNum3.addActionListener(jmfc);
		
	}
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					JoinMemberFrame frame = new JoinMemberFrame(mf);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
}
