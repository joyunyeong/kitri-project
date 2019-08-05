package com.kitri.Book.rent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RentalController implements ActionListener {
   
   public RentalMain rm;
   public RentalService rs;

   public RentalController(RentalMain r) {
      this.rm = r;
      rs = new RentalService(rm);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      Object ob = e.getSource();
      
      if(ob == rm.search) { 
         if (rm.searchTF.getText().trim().equals("")) 
            JOptionPane.showMessageDialog(null, "[알림] 검색어를 입력하세요.");
         else if(ob == rm.search) {// 검색어를 입력했을경우
            String searchStr = rm.searchTF.getText().trim();// 찾는 내용
             rs.searchData(searchStr);
             rm.searchTF.setText("");
              } 
         
         } else if(ob == rm.change) { // db의 교체요망 0에서 1로 change
            JOptionPane.showMessageDialog(null, "[알림] 해당 책은 교체요망 처리되었습니다.");
            
         } else if(ob == rm.move) {
        	 rs.MoveToList();
         } else if(ob == rm.cancle) {
        	 String tmp = rm.bookL.getSelectedItem();
        	 rm.bookL.remove(tmp);
         } else if(ob == rm.basket) {
        	 rs.makeBasket();
        	 rm.setVisible(false);
		}
   	}
}