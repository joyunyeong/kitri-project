package com.kitri.Main.EditMember;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.kitri.Main.frame.MainFrame;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;

public class EditMemberFrame extends JFrame {
	MainFrame mf;
	EditMemberFrameController emfc;
	private JPanel contentPane;
	public JTextField tfEAdd;
	public JTextField tfEP1;
	public JTextField tfEP2;
	public JTextField tfEP3;
	public JLabel labelBirth;
	public JTextField tfName;
	public JButton buttonEM;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EditMemberFrame frame = new EditMemberFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public EditMemberFrame(MainFrame mf) {
		
		this.mf = mf;
		emfc = new EditMemberFrameController(this);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 464, 414);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelN = new JLabel("이름");
		labelN.setBounds(24, 46, 57, 15);
		contentPane.add(labelN);
		
		JLabel labelB = new JLabel("생년월일");
		labelB.setBounds(24, 102, 57, 15);
		contentPane.add(labelB);
		
		JLabel labelAdd = new JLabel("주소");
		labelAdd.setBounds(24, 159, 57, 15);
		contentPane.add(labelAdd);
		
		JLabel labelPN = new JLabel("전화번호");
		labelPN.setBounds(24, 216, 57, 15);
		contentPane.add(labelPN);
		
		tfEAdd = new JTextField();
		tfEAdd.setBackground(SystemColor.activeCaption);
		tfEAdd.setColumns(10);
		tfEAdd.setBounds(129, 156, 286, 21);
		contentPane.add(tfEAdd);
		
		tfEP1 = new JTextField();
		tfEP1.setBackground(SystemColor.activeCaption);
		tfEP1.setColumns(10);
		tfEP1.setBounds(129, 213, 57, 21);
		contentPane.add(tfEP1);
		
		tfEP2 = new JTextField();
		tfEP2.setBackground(SystemColor.activeCaption);
		tfEP2.setColumns(10);
		tfEP2.setBounds(217, 213, 57, 21);
		contentPane.add(tfEP2);
		
		tfEP3 = new JTextField();
		tfEP3.setBackground(SystemColor.activeCaption);
		tfEP3.setColumns(10);
		tfEP3.setBounds(307, 213, 57, 21);
		contentPane.add(tfEP3);
		
		buttonEM = new JButton("회원정보수정");
		buttonEM.setForeground(Color.WHITE);
		buttonEM.setBackground(SystemColor.textHighlight);
		buttonEM.setBounds(67, 296, 116, 37);
		contentPane.add(buttonEM);
		
		JButton buttonCancel = new JButton("취소");
		buttonCancel.setForeground(Color.WHITE);
		buttonCancel.setBackground(SystemColor.controlShadow);
		buttonCancel.setBounds(236, 296, 116, 37);
		contentPane.add(buttonCancel);
		
		JLabel label_4 = new JLabel("-");
		label_4.setBounds(198, 216, 14, 15);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("-");
		label_5.setBounds(286, 216, 14, 15);
		contentPane.add(label_5);
		
		labelBirth = new JLabel("");
		labelBirth.setBounds(129, 102, 171, 15);
		contentPane.add(labelBirth);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBackground(SystemColor.activeCaption);
		tfName.setBounds(129, 41, 144, 21);
		contentPane.add(tfName);
		
		buttonCancel.addActionListener(emfc);
		buttonEM.addActionListener(emfc);
		tfEAdd.addActionListener(emfc);
		tfEP1.addActionListener(emfc);
		tfEP2.addActionListener(emfc);
		tfEP3.addActionListener(emfc);
		tfName.addActionListener(emfc);
		
		
	}
}
