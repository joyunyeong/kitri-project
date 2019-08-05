package com.kitri.Book.paymethod;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class Cash extends JFrame implements ActionListener, KeyListener{
   
   public JButton buttons[] = new JButton[14];
   
   public JLabel moneyL;
   
   boolean clearOnNextDigit;
   int displayMode;
   int INPUT_MODE = 0;
   String lastOperator;
   double lastNumber;
      
   public Cash() {
      super("현금결제");
      setBackground(Color.WHITE);
      setBounds(898, 85, 576, 240);
      getContentPane().setLayout(null);
      
      for (int i = 0; i <= 8; i++) { // 1~9
         buttons[i] = new JButton(String.valueOf(i));
         buttons[i].setFont(new Font("굴림", Font.BOLD, 14));
         buttons[i].setBackground(SystemColor.inactiveCaptionBorder);
      }
      
      buttons[9] = new JButton("백");
      buttons[10] = new JButton("천");
      buttons[11] = new JButton("만");
      buttons[12] = new JButton("C");
      buttons[13] = new JButton("확  인");
      
      
      buttons[0].setBounds(24, 93, 47, 42);
      buttons[1].setBounds(83, 93, 47, 42);
      buttons[2].setBounds(142, 93, 47, 42);
      buttons[3].setBounds(201, 93, 47, 42);
      buttons[4].setBounds(260, 93, 47, 42);
      buttons[5].setBounds(321, 93, 47, 42);
      buttons[6].setBounds(380, 93, 47, 42);
      buttons[7].setBounds(439, 93, 47, 42);
      buttons[8].setBounds(498, 93, 47, 42); // nun : 9
      buttons[9].setBounds(321, 145, 47, 42);
      buttons[10].setBounds(260, 145, 47, 42);
      buttons[11].setBounds(201, 145, 47, 42);
      buttons[12].setBounds(380, 145, 47, 42);
      buttons[13].setBounds(439, 145, 106, 42);
      
      for (int i = 9; i <= 13; i++) {
         buttons[i].setBackground(SystemColor.inactiveCaptionBorder);
      }
      
      buttons[9].setFont(new Font("굴림", Font.BOLD, 12));
      buttons[10].setFont(new Font("굴림", Font.BOLD, 12));
      buttons[11].setFont(new Font("굴림", Font.BOLD, 12));
      buttons[12].setFont(new Font("굴림", Font.BOLD, 14));
      buttons[13].setFont(new Font("굴림", Font.BOLD, 14));
      
      for (int i = 0; i < buttons.length; i++) {
         getContentPane().add(buttons[i]);
         
      }

      
      JPanel panel = new JPanel();
      panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      panel.setBounds(22, 10, 520, 69);
      getContentPane().add(panel);
      panel.setLayout(null);
      
      JLabel requestL = new JLabel("[  \uD604\uAE08 \uACB0\uC81C  ]    \uBC1B\uC740 \uAE08\uC561\uC744 \uC785\uB825\uD558\uC2DC\uC624.");
      requestL.setBounds(12, 10, 508, 15);
      panel.add(requestL);
      requestL.setFont(new Font("HY그래픽M", Font.BOLD, 15));
      
      JLabel wonL = new JLabel(" \uC6D0");
      wonL.setBounds(474, 35, 22, 23);
      panel.add(wonL);
      wonL.setHorizontalAlignment(SwingConstants.RIGHT);
      wonL.setFont(new Font("HY그래픽M", Font.BOLD, 15));
      
      moneyL = new JLabel("");
      moneyL.setHorizontalAlignment(SwingConstants.RIGHT);
      moneyL.setFont(new Font("HY그래픽M", Font.BOLD, 15));
      moneyL.setBounds(268, 39, 205, 15);
      panel.add(moneyL);
      
      // eventListener 추가
      for (int i = 0; i < buttons.length; i++) {
         buttons[i].addActionListener(this);
         buttons[i].addKeyListener(this);
      }
         

   }
   
   public static void main(String[] args) {
      try {
         Cash cash = new Cash();
         cash.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
         cash.setVisible(true);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      Object ob = e.getSource();

         int len = buttons.length;
         for (int i = 0; i < len; i++) {
            if (ob == buttons[i]) {
               if (i <= 8) {
                  number(i);
                  break;
               } else {
                  switch (i) {
                  case 9: // 100
                     String a = moneyL.getText() + "00";
                     moneyL.setText(a);
                  break;
                  case 10: // 1000
                     String b = moneyL.getText() + "000";
                     moneyL.setText(b);
                  break;
                  case 11: // 10000
                     String c = moneyL.getText() + "0000";
                     moneyL.setText(c);
                  break;
                  case 12:
                	 
                     clearAll();
                  case 13 :
                	  JOptionPane.showMessageDialog(null, "[승인완료] 결제되었습니다.");
                	  setVisible(false);
         }}}}}
               
            
      
   

   @Override
   public void keyTyped(KeyEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void keyPressed(KeyEvent e) {
      int keycode = e.getKeyChar();
      // System.out.println(e.getKeyCode()+ " "+ keycode ); 요걸로 특수기호 녀석들 코드를 알아냈습니다...
      // ^^;;부끄
      switch (keycode) {
      case KeyEvent.VK_0:
         number(0);
         break;
      case KeyEvent.VK_1:
         number(1);
         break;
      case KeyEvent.VK_2:
         number(2);
         break;
      case KeyEvent.VK_3:
         number(3);
         break;
      case KeyEvent.VK_4:
         number(4);
         break;
      case KeyEvent.VK_5:
         number(5);
         break;
      case KeyEvent.VK_6:
         number(6);
         break;
      case KeyEvent.VK_7:
         number(7);
         break;
      case KeyEvent.VK_8:
         number(8);
         break;
      case KeyEvent.VK_9:
         number(9);
         break;
      case 27: // 다 지우기
    	  
         clearAll(); 
         break;
      }
   }

   private void clearAll() {
        moneyL.setText("0");
         lastOperator = "0";
         lastNumber = 0;
         displayMode = INPUT_MODE;
         clearOnNextDigit = true;
   }

   private void number(int i) {
      String inputString = moneyL.getText();

         // null
         if (clearOnNextDigit)
            moneyL.setText("");

         if (inputString.indexOf("0") == 0)
            inputString = inputString.substring(1);

         if ((!inputString.equals("0") || i > 0)) {
            moneyL.setText(inputString + i);
         }

         displayMode = INPUT_MODE;
         clearOnNextDigit = false;
      
   }

   @Override
   public void keyReleased(KeyEvent e) {
      // TODO Auto-generated method stub
      
   }
}