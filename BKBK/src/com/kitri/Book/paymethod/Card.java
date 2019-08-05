package com.kitri.Book.paymethod;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;


public class Card extends JFrame {

   private JPanel leftP;
   private JLabel payL;
   private JLabel cardNumL;
   private JLabel payMonthL;
   public JTextField cardNumber;
   public JTextField sum;
   private JTextField payMonth;
   
   
   public JButton buttons[] = new JButton[13];
   private String[] arr = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "00", "C", "¡ç"};
   
   public JButton confirm = new JButton("½ÂÀÎÁ¶È¸");
   public JButton cancle = new JButton("Ãë¼Ò");
   

   public Card() {
      super("Ä«µå°áÁ¦");
      setBackground(SystemColor.text);
      setBounds(500, 400, 622, 437);
      getContentPane().setLayout(null);
      
      for (int i = 0; i <= 8; i++) { 
         buttons[i] = new JButton(arr[i]);
         buttons[i].setFont(new Font("±¼¸²", Font.PLAIN, 12));
         buttons[i].setBackground(SystemColor.inactiveCaptionBorder);
      }
      
      for (int i = 9; i <= 12; i++) {
         buttons[i] = new JButton(arr[i]);
         buttons[i].setFont(new Font("±¼¸²", Font.PLAIN, 12));
         buttons[i].setBackground(SystemColor.inactiveCaptionBorder);
      }
      
//      buttons[9] = new JButton("00");
//      buttons[10] = new JButton("000");
//      buttons[11] = new JButton("C");
//      buttons[12] = new JButton("¡ç");
      
      buttons[0].setBounds(347, 136, 57, 56); // 1
      buttons[1].setBounds(404, 136, 57, 56);
      buttons[2].setBounds(461, 136, 57, 56);
      buttons[3].setBounds(347, 80, 57, 56);
      buttons[4].setBounds(404, 80, 57, 56);
      buttons[5].setBounds(461, 80, 57, 56);
      buttons[6].setBounds(347, 24, 57, 56);
      buttons[7].setBounds(404, 24, 57, 56);
      buttons[8].setBounds(461, 24, 57, 56); // 9
      buttons[9].setBounds(347, 192, 57, 56); // 0
      buttons[10].setBounds(404, 192, 57, 56); // 00
      buttons[11].setBounds(461, 192, 57, 56); // 000
      buttons[12].setBounds(520, 192, 63, 56); // 
      
      for (int i = 0; i < buttons.length; i++) 
         getContentPane().add(buttons[i]);
      
      confirm.setBackground(new Color(30, 144, 255));
      confirm.setBounds(346, 258, 115, 56);
      getContentPane().add(confirm);
      
      cancle.setBackground(new Color(255, 69, 0));
      cancle.setBounds(468, 258, 115, 56);
      getContentPane().add(cancle);
      
      // ÆÐ³Î
      leftP = new JPanel();
      leftP.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      leftP.setBounds(25, 26, 270, 288);
      getContentPane().add(leftP);
      leftP.setLayout(null);
      
      payL = new JLabel("\uACB0\uC81C\uAE08\uC561");
      payL.setFont(new Font("±¼¸²", Font.BOLD, 12));
      payL.setBackground(SystemColor.inactiveCaptionBorder);
      payL.setBounds(22, 31, 57, 15);
      leftP.add(payL);
      
      cardNumL = new JLabel("\uCE74\uB4DC\uBC88\uD638");
      cardNumL.setFont(new Font("±¼¸²", Font.BOLD, 12));
      cardNumL.setBackground(SystemColor.inactiveCaptionBorder);
      cardNumL.setBounds(22, 100, 57, 15);
      leftP.add(cardNumL);
      
      cardNumber = new JTextField();
      cardNumber.setFont(new Font("±¼¸²", Font.BOLD, 15));
      cardNumber.setHorizontalAlignment(SwingConstants.CENTER);
      cardNumber.setEnabled(false);
      cardNumber.setBounds(22, 124, 213, 34);
      leftP.add(cardNumber);
      cardNumber.setColumns(10);
      
      sum = new JTextField();
      sum.setFont(new Font("±¼¸²", Font.BOLD, 12));
      sum.setHorizontalAlignment(SwingConstants.CENTER);
      sum.setColumns(10);
      sum.setBounds(22, 56, 213, 34);
      leftP.add(sum);
      
      payMonth = new JTextField();
      payMonth.setFont(new Font("±¼¸²", Font.BOLD, 12));
      payMonth.setHorizontalAlignment(SwingConstants.CENTER);
      payMonth.setText("00");
      payMonth.setColumns(10);
      payMonth.setBounds(22, 200, 213, 34);
      leftP.add(payMonth);
      
      payMonthL = new JLabel("\uD560\uBD80\uAC1C\uC6D4");
      payMonthL.setFont(new Font("±¼¸²", Font.BOLD, 12));
      payMonthL.setBackground(SystemColor.inactiveCaptionBorder);
      payMonthL.setBounds(22, 175, 57, 15);
      leftP.add(payMonthL);
      
      JCheckBox chckbxNewCheckBox = new JCheckBox("\uC601\uC218\uC99D \uAC04\uB2E8 \uCD9C\uB825");
      chckbxNewCheckBox.setBounds(63, 240, 188, 23);
      leftP.add(chckbxNewCheckBox);
      
      JLabel info = new JLabel("      \uCE74\uB4DC\uB97C \uBA3C\uC800 \uC77D\uD600 \uC8FC\uC138\uC694. >> \uAE5C\uBE61\uAC70\uB9AC\uAC8C \uC124\uC815");
      info.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      info.setForeground(new Color(255, 0, 0));
      info.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
      info.setBounds(25, 337, 558, 43);
      getContentPane().add(info);
   }
      
      
      // eventLister´Â payMainService¿¡ ÀÖÀ½
   
}