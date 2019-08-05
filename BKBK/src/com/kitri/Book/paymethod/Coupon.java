package com.kitri.Book.paymethod;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class Coupon extends JPanel {
   
   private JPanel panel = new JPanel();
   private JRadioButton coupon1;
   private JRadioButton coupon2;
   private JButton apply;

   /**
    * Create the panel.
    */
   public Coupon() {
      setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      setBackground(SystemColor.textHighlightText);
      setBounds(722, 699, 568, 107);
      setLayout(null);
      
      panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      panel.setBackground(Color.WHITE);
      panel.setBounds(0, 0, 568, 107);
      add(panel);
      panel.setLayout(null);
      
      coupon1 = new JRadioButton("  \uD604\uC7AC \uC0AC\uC6A9\uAC00\uB2A5\uD55C \uCFE0\uD3F0\uC774 \uC5C6\uC2B5\uB2C8\uB2E4.");
      coupon1.setBounds(59, 48, 255, 23);
      panel.add(coupon1);
      coupon1.setBackground(Color.WHITE);
      
      coupon2 = new JRadioButton("  \uD604\uC7AC \uC0AC\uC6A9\uAC00\uB2A5\uD55C \uCFE0\uD3F0\uC774 \uC5C6\uC2B5\uB2C8\uB2E4.");
      coupon2.setBackground(Color.WHITE);
      coupon2.setBounds(46, 71, 255, 23);
//      panel.add(coupon2);
      
      apply = new JButton("\uC801  \uC6A9");
      apply.setBounds(459, 43, 97, 51);
      apply.setBackground(SystemColor.textHighlightText);
//      panel.add(apply); > 쿠폰 사용불가일때는 apply 숨기기
      
      
      JLabel lblNewLabel = new JLabel("[ \uC0AC\uC6A9\uAC00\uB2A5\uD55C \uCFE0\uD3F0 ]");
      lblNewLabel.setBackground(new Color(176, 196, 222));
      lblNewLabel.setFont(new Font("굴림", Font.BOLD, 14));
      lblNewLabel.setBounds(26, 10, 170, 32);
      panel.add(lblNewLabel);
   }
}