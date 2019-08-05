package com.kitri.Manager.popup;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class BookDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public JTextField nameTF = new JTextField();
	public JComboBox genreC = new JComboBox();
	public JTextField authorTF = new JTextField();
	public JComboBox ctgC = new JComboBox();
	public JTextField publisherTF = new JTextField();
	public JTextField priceTF = new JTextField();
	
	public JPanel cardP = new JPanel();
	public CardLayout card = new CardLayout();
	public JPanel addP = new JPanel();
	public JPanel mdfP = new JPanel();
	
	public JButton addB = new JButton("Ãß°¡");
	public JButton mdfB = new JButton("¼öÁ¤");
	public JButton cancelB = new JButton("Ãë¼Ò");

	/**
	 * Create the dialog.
	 */
	public BookDialog() {
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 288, 458);
		contentPanel.setBackground(SystemColor.control);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		JLabel tl1 = new JLabel("µµ¼­Á¾·ù");
		tl1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		tl1.setBounds(24, 23, 137, 33);
		ctgC.setBackground(SystemColor.window);
		ctgC.setBounds(124, 26, 126, 30);
		
		JLabel tl2 = new JLabel("Àå¸£");
		tl2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		tl2.setBounds(24, 86, 97, 17);
		genreC.setBackground(Color.WHITE);
		genreC.setBounds(124, 81, 126, 30);
		
		JLabel tl3 = new JLabel("µµ¼­¸í");
		tl3.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		tl3.setBounds(24, 137, 97, 17);
		nameTF.setBounds(24, 164, 226, 30);
		nameTF.setColumns(10);
		
		JLabel tl4 = new JLabel("ÀÛ°¡");
		tl4.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		tl4.setBounds(24, 225, 57, 15);
		authorTF.setBounds(24, 250, 226, 30);
		authorTF.setColumns(10);
		
		JLabel tl5 = new JLabel("ÃâÆÇ»ç");
		tl5.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		tl5.setBounds(25, 314, 57, 17);
		publisherTF.setColumns(10);
		publisherTF.setBounds(24, 340, 226, 30);
		
		JLabel tl6 = new JLabel("Á¤°¡");
		tl6.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		tl6.setBounds(24, 410, 57, 17);
		priceTF.setColumns(10);
		priceTF.setBounds(71, 405, 145, 30);
		JLabel tl7 = new JLabel("¿ø");
		tl7.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		tl7.setBounds(228, 410, 25, 17);
				
		contentPanel.add(tl1);
		contentPanel.add(ctgC);
		contentPanel.add(tl2);
		contentPanel.add(genreC);
		contentPanel.add(tl3);
		contentPanel.add(nameTF);
		contentPanel.add(tl4);
		contentPanel.add(authorTF);
		contentPanel.add(tl5);
		contentPanel.add(publisherTF);
		contentPanel.add(tl6);
		contentPanel.add(priceTF);
		contentPanel.add(tl7);
		getContentPane().add(contentPanel);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 470, 288, 40);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setLayout(null);
		
		
		cardP.setLayout(card);
		addP.setLayout(null);
		addB.setBounds(120, 5, 70, 25);
		addP.add(addB);
		mdfP.setLayout(null);
		mdfB.setBounds(120, 5, 70, 25);
		mdfP.add(mdfB);
		cardP.add(addP, "add");
		cardP.add(mdfP, "mdf");
		
		
		cardP.setBounds(12, 0, 195, 40);
		cancelB.setBounds(210, 5, 70, 25);
		cancelB.setActionCommand("Cancel");
		buttonPane.add(cardP);
		buttonPane.add(cancelB);

		setModal(true);
		setBounds(700, 300, 304, 549);
		getContentPane().add(buttonPane);
	}
}
