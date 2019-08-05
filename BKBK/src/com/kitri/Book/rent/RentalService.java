package com.kitri.Book.rent;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.kitri.Main.dto.Basket;
import com.kitri.Main.swing.box.FPanel;

public class RentalService {
	public RentalMain rm;
	public RentalController rc;

	public RentalService(RentalMain rentalMain) {
		rm = rentalMain;
	}

	public void makeBasket() {
		FPanel f = rm.mf.mc.mfs.findFp();
		int len = rm.bookL.getItemCount();
		for (int i = 0; i < len; i++) {
			String str = rm.bookL.getItem(i);
			BookDTO bd = rm.dao.selectshbook(str);
			Basket b = new Basket(bd, 1);
			f.bookDtoBasket.add(b);
		}
		rm.mf.mc.mfs.printTable(f);
	}
	
	public void searchData(String searchStr) {
		Vector<BookDTO> list = rm.dao.select(searchStr);

		Vector<Vector<String>> newList = new Vector<Vector<String>>();
		int len = list.size();
		for (int i = 0; i < len; i++) {
			Vector<String> vec = new Vector<String>();
			vec.add(list.get(i).getBookName());
			vec.add(list.get(i).getAuthor());
			vec.add(list.get(i).getPublisher());
			newList.add(vec);
		}

		Vector<String> colVec = new Vector<String>();

		for (int i = 0; i < rm.col.size(); i++) {
			colVec.add(rm.col.elementAt(i));
		}
		
		DefaultTableModel model = new DefaultTableModel(newList, colVec);
		rm.book.setModel(model);
	}

	public void MoveToList() {
   	 int selected = rm.book.getSelectedRow();
   	 System.out.println(selected);
   	 String n = rm.dao.BookList().get(selected).getBookName();
   	 rm.bookL.add(n);
	}
}