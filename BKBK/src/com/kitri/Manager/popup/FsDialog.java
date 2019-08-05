package com.kitri.Manager.popup;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public JTextField menuTF = new JTextField();
	public JTextField amtTF = new JTextField();
	public JTextField priceTF = new JTextField();
	String[] ctg = {"À½·á", "°úÀÚ", "¶ó¸é", "À½½Ä"};
	public JComboBox ctgC = new JComboBox(ctg);
	
	public JPanel mdfP = new JPanel();

	JPanel buttonPane = new JPanel();
	public JButton addB = new JButton("ÀÔ°í");
	public JButton cancelB = new JButton("Ãë¼Ò");

	/**
	 * Create the dialog.
	 */
	public FsDialog() {
		contentPanel.setBounds(0, 0, 255, 283);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		JLabel tl1 = new JLabel("Ä«Å×°í¸®");
		tl1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		tl1.setBounds(16, 20, 137, 33);
		ctgC.setBackground(SystemColor.window);
		ctgC.setBounds(97, 23, 126, 30);

		JLabel tl2 = new JLabel("Ç°¸ñ");
		tl2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		tl2.setBounds(16, 82, 97, 17);
		menuTF.setBounds(16, 108, 206, 30);
		menuTF.setColumns(10);

		JLabel tl3 = new JLabel("ÀÔ°í¼ö·®");
		tl3.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		tl3.setBounds(16, 174, 73, 17);
		amtTF.setBounds(103, 170, 120, 30);
		amtTF.setColumns(10);
		
		
		JLabel tl4 = new JLabel("´Ü°¡");
		tl4.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		tl4.setBounds(16, 241, 57, 17);
		priceTF.setBounds(67, 236, 120, 30);
		priceTF.setColumns(10);

		JLabel tl5 = new JLabel("¿ø");
		tl5.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		tl5.setBounds(196, 241, 27, 17);

		contentPanel.setBackground(SystemColor.control);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		contentPanel.add(tl1);
		contentPanel.add(ctgC);
		contentPanel.add(tl2);
		contentPanel.add(menuTF);
		contentPanel.add(tl3);
		contentPanel.add(amtTF);
		contentPanel.add(tl4);
		contentPanel.add(priceTF);
		contentPanel.add(tl5);
		
		buttonPane.setBounds(0, 304, 255, 33);
		buttonPane.setLayout(null);
		addB.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 11));
		addB.setBounds(92, 2, 70, 25);
		cancelB.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 11));
		cancelB.setBounds(170, 2, 70, 25);
		buttonPane.add(cancelB);
		buttonPane.add(addB);

		getContentPane().setLayout(null);
		getContentPane().add(contentPanel);
		getContentPane().add(buttonPane);

		setTitle("¸Þ´º Ãß°¡");
		setModal(true);
		setBounds(700, 300, 272, 379);
	}
}
