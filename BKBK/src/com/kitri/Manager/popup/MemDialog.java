package com.kitri.Manager.popup;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MemDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public JTextField adrTF = new JTextField();
	
	public JPanel cardP = new JPanel();
	public CardLayout card = new CardLayout();
	public JPanel addP = new JPanel();
	public JPanel mdfP = new JPanel();
	String[] num = {"010", "02", "031"};
	
	public JButton addB = new JButton("Ãß°¡");
	public JButton mdfB = new JButton("¼öÁ¤");
	public JButton cancelB = new JButton("Ãë¼Ò");
	public JTextField nameTF = new JTextField();
	public JComboBox ph1C = new JComboBox(num);
	public JTextField ph2TF = new JTextField();
	public JTextField ph3TF = new JTextField();
	private final JLabel label_1 = new JLabel("-");
	public JTextField birth1 = new JTextField();
	public  JTextField birth2 = new JTextField();
	private final JLabel label_3 = new JLabel("¿ù");
	public  JTextField birth3 = new JTextField();
	private final JLabel label_4 = new JLabel("ÀÏ");
	public JLabel infoBirth = new JLabel();
	public JLabel infoPhoneNum = new JLabel();

	/**
	 * Create the dialog.
	 */
	public MemDialog() {
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 356, 354);
		contentPanel.setBackground(SystemColor.control);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		JLabel tl1 = new JLabel("ÀÌ¸§");
		tl1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		tl1.setBounds(24, 23, 88, 33);
		
		JLabel tl2 = new JLabel("ÀüÈ­¹øÈ£");
		tl2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		tl2.setBounds(24, 84, 97, 17);
		
		JLabel tl3 = new JLabel("ÁÖ¼Ò");
		tl3.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		tl3.setBounds(24, 173, 97, 15);
		adrTF.setBounds(24, 198, 308, 30);
		adrTF.setColumns(10);
		
		JLabel tl4 = new JLabel("»ý³â¿ùÀÏ");
		tl4.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		tl4.setBounds(24, 260, 74, 21);
				
		contentPanel.add(tl1);
		contentPanel.add(tl2);
		contentPanel.add(tl3);
		contentPanel.add(adrTF);
		contentPanel.add(tl4);
		getContentPane().add(contentPanel);
		nameTF.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		nameTF.setColumns(10);
		nameTF.setBounds(90, 25, 137, 30);
		contentPanel.add(nameTF);
		ph2TF.setHorizontalAlignment(SwingConstants.CENTER);
		ph2TF.setColumns(4);
		ph2TF.setBounds(124, 112, 90, 30);
		
		contentPanel.add(ph2TF);
		ph3TF.setHorizontalAlignment(SwingConstants.CENTER);
		ph3TF.setColumns(4);
		ph3TF.setBounds(244, 112, 90, 30);
		
		contentPanel.add(ph3TF);
		
		
		ph1C.setBounds(24, 112, 70, 30);
		contentPanel.add(ph1C);
		
		JLabel label = new JLabel("-");
		label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		label.setBounds(106, 117, 15, 15);
		contentPanel.add(label);
		label_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		label_1.setBounds(226, 117, 15, 15);
		
		contentPanel.add(label_1);
		birth1.setHorizontalAlignment(SwingConstants.CENTER);
		birth1.setColumns(4);
		
		birth1.setBounds(26, 292, 70, 30);
		contentPanel.add(birth1);
		
		JLabel label_2 = new JLabel("³â");
		label_2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		label_2.setBounds(100, 295, 23, 21);
		contentPanel.add(label_2);
		birth2.setHorizontalAlignment(SwingConstants.CENTER);
		birth2.setColumns(2);
		birth2.setBounds(130, 292, 70, 30);
		
		contentPanel.add(birth2);
		label_3.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		label_3.setBounds(206, 295, 23, 21);
		
		contentPanel.add(label_3);
		birth3.setHorizontalAlignment(SwingConstants.CENTER);
		birth3.setColumns(2);
		birth3.setBounds(235, 292, 70, 30);
		
		contentPanel.add(birth3);
		label_4.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		label_4.setBounds(309, 295, 23, 21);
		
		contentPanel.add(label_4);
		infoBirth.setForeground(new Color(255, 0, 0));
		infoBirth.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		infoBirth.setBounds(91, 261, 241, 20);
		
		contentPanel.add(infoBirth);
		infoPhoneNum.setForeground(Color.RED);
		infoPhoneNum.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		infoPhoneNum.setBounds(91, 84, 241, 20);
		
		contentPanel.add(infoPhoneNum);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 356, 356, 40);
		getContentPane().add(buttonPane);
		buttonPane.setLayout(null);
		
		
		cardP.setLayout(card);
		addP.setLayout(null);
		addB.setBounds(180, 5, 70, 25);
		addP.add(addB);
		mdfP.setLayout(null);
		mdfB.setBounds(180, 5, 70, 25);
		mdfP.add(mdfB);
		cardP.add(addP, "add");
		cardP.add(mdfP, "mdf");
		
		
		cardP.setBounds(12, 0, 260, 40);
		cancelB.setBounds(274, 5, 70, 25);
		cancelB.setActionCommand("Cancel");
		buttonPane.add(cardP);
		buttonPane.add(cancelB);

		setModal(true);
		setBounds(700, 300, 372, 445);
	}
}
