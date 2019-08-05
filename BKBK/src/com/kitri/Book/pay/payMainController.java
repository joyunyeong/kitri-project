package com.kitri.Book.pay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.kitri.Main.swing.box.FPanel;

public class payMainController implements ActionListener {

		   payMain p;
		   payMainService pms;
		   
		   public payMainController(payMain payMain) {
		      this.p = payMain;
		      pms = new payMainService(p);
		   }

		   
		   @Override
		   public void actionPerformed(ActionEvent e) {
		      
		      String str = e.getActionCommand(); // �� Object�� �ȸ�����?
		      Object ob = e.getSource();
		      
		      if (ob == p.cardB) {
		         pms.goCard();
		         p.card.setLocation(500, 400);
		         p.card.setVisible(true);
		      } else if (ob == p.cashB) {
		         p.cash.setLocation(450, 300);
		         p.cash.setVisible(true);
		      } else if (ob == p.cardCashB) {
		    	  pms.goComplex();
		    	  p.card.confirm.setText("���հ���");
		          p.card.setLocation(500, 400);
		          p.card.setVisible(true);
		      } else if (ob == p.homeB) {
		    	  allclear();
		    	  p.mf.serCard.show(p.mf.panelCard, "Main");
		   }
	}

	private void allclear() {
		p.model.setNumRows(0);
		p.givenR.setText("");
		p.changeR.setText("");
		p.payR.setText("");
		p.discountR.setText("");
		
	}



}