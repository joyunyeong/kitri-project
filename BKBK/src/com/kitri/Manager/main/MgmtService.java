package com.kitri.Manager.main;

import java.awt.Color;
import java.awt.Dimension;
import java.text.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import com.kitri.Manager.dao.*;
import com.kitri.Manager.data.*;
import com.kitri.Manager.page.BookMgmtDesign;
import com.kitri.Manager.page.FoodMgmtDesign;
import com.kitri.Manager.page.MemberMgmtDesign;
import com.kitri.Manager.popup.*;

public class MgmtService {

	// field
	MgmtController mmc;
	FoodMgmtDesign fP;
	BookMgmtDesign bP;
	MemberMgmtDesign mP;
	
	FctgDialog fcA;
	FsDialog fsA;
	BookDialog bAdd;
	MemDialog mAdd;
	
	FoodDto foodDto;
	StockDto stockDto;
	BookDto bookDto;
	MemberDto memDto;
	List<String> list;
	
	
	String ctg;
	int amtS;

	
	// constructor
	public MgmtService(MgmtController mmc) {
		this.mmc = mmc;
		fP = mmc.mm.fm;
		bP = mmc.mm.bm;
		mP = mmc.mm.mm;
		fcA = mmc.mm.fcAdd;
		fsA = mmc.mm.fsAdd;
		bAdd = mmc.mm.bAdd;
		mAdd = mmc.mm.mAdd;
		
	}
	

//-------------------------------------------------------------------------------------------------------------------------------------------------[h] 관리번호수정
	
	public boolean isNum(String str) { //숫자면 true 반환
		boolean flag = true;
		int len = str.length();
		for (int i = 0; i < len; i++) {
			int num = str.charAt(i) - 48;
			if(num < 0 || num > 9) {
				flag = false;
				continue;
			}
		}
		return flag;
	}
	
	public void mgmtNum() {
		while(true) {
			String r = JOptionPane.showInputDialog(mmc.mm, "새로운 번호를 입력하세요", "관리번호수정", JOptionPane.OK_CANCEL_OPTION).trim();
			
			if(r.isEmpty()) {
				JOptionPane.showMessageDialog(mmc.mm, "번호를 입력하세요.", "관리번호수정에러", JOptionPane.ERROR_MESSAGE);
				continue;
			}
			if(r.length() != 5) {
				JOptionPane.showMessageDialog(mmc.mm, "5자리 숫자를 입력하세요.", "관리번호수정에러", JOptionPane.ERROR_MESSAGE);
				continue;
			}
			
			
			boolean rf = isNum(r);
			if(rf) {
				int result = FoodDao.getInstance().upMgmtNum(Integer.parseInt(r));
				if(result != 0) {
					JOptionPane.showMessageDialog(mmc.mm, "관리번호가 수정되었습니다.", "수정완료", JOptionPane.INFORMATION_MESSAGE);
					break;
				}
				else {
					JOptionPane.showMessageDialog(mmc.mm, "알 수 없는 에러가 발생했습니다.", "관리번호수정에러", JOptionPane.ERROR_MESSAGE);
					break;
				}
			} else {
				JOptionPane.showMessageDialog(mmc.mm, "5자리 숫자를 입력하세요.", "관리번호수정에러", JOptionPane.ERROR_MESSAGE);
				continue;
			}
		}
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------------------[h]

//--------------------------------------------------------------------------------------------------------------------------------------FOOD
	
	
	public void foodPage() {//---------------------------------------상품관리 페이지로 이동, 메뉴버튼&테이블 초기화
		mmc.mm.cards.show(mmc.mm.cardP, "상품관리P");
		setMenuB("음료");
		setStockT("음료");
	}
	
	
//-----------------------------------------------------------------------------------------------------------menu 기능
	
	
	// 메뉴버튼 셋팅
	public void setMenuB(String ctg) {
		this.ctg = ctg;
		list = FoodDao.getInstance().selCtgMenu(ctg);//카테고리별 메뉴이름 select
		int size = list.size();
		for(int i=0;i<size;i++) {//메뉴 개수만큼 for
			String btnL = list.get(i);
			if(btnL.length() > 5) {//메뉴이름이 긴 경우 개행
				StringBuffer str = new StringBuffer(list.get(i));
				str.insert(3, "<br>");
				str.insert(0, "<html>");
				str.append("</html>");
				if(btnL.length() > 9) {
					str.insert(16, "<br>");
				}
				btnL = str.toString();
			}
			fP.menuB[i].setText(btnL);
			fP.menuB[i].setHorizontalAlignment(SwingConstants.CENTER);
			fP.abBD(fP.menuB[i],null, 15);
			fP.menuB[i].setVisible(true);
		}
		if(size < 25) {
			fP.menuB[size].setText("+");//마지막 메뉴 다음 버튼에 '+' 띄우고 글자 크기 조정 & 보이게
			fP.abBD(fP.menuB[size], new Color(52, 152, 219), 35);
			fP.menuB[size].setVisible(true);
			for(int i = 24; i > size ; i--) {
				fP.menuB[i].setVisible(false);
			}
		}
	}
	
	
	// menu버튼 dialog
	public void setM(String btnL) {
		
		// 버튼의 라벨에 개행 있는 경우 초기화
		if(btnL.indexOf("<br>") != -1) {
			btnL = btnL.replace("<br>", "");
			btnL = btnL.replace("<html>", "");
			btnL = btnL.replace("</html>", "");
		}
		
		// dialog 버튼 초기화
		fcA.ctgC.setSelectedItem(ctg);
		fcA.ctgC.setEnabled(true);
		fcA.menuTF.setEnabled(true);
		fcA.menuTF.setText(null);
		fcA.priceTF.setText(null);

		if(!btnL.equals("+")) {// 기존 메뉴 수정&삭제 dialog
			foodDto = FoodDao.getInstance().findMenu(btnL, ctg);
			fcA.menuTF.setText(foodDto.getFoodName());
			fcA.menuTF.setEnabled(false);
			fcA.priceTF.setText(""+foodDto.getFoodPrice());
			fcA.ctgC.setSelectedItem(foodDto.getFoodCtg());
			fcA.ctgC.setEnabled(false);
			fcA.st1C.setSelectedItem(foodDto.getStock1Name());
			fcA.st1A.setText(""+foodDto.getStock1Num());
			if(foodDto.getStock2Name() != null) {
				fcA.st2C.setSelectedItem(foodDto.getStock2Name());
				fcA.st2A.setText(""+foodDto.getStock2Num());
			}
			if(foodDto.getStock3Name() !=null) {
				fcA.st3C.setSelectedItem(foodDto.getStock3Name());
				fcA.st3A.setText(""+foodDto.getStock3Num());
			}
			fcA.setTitle("메뉴 수정");
			fcA.card.show(fcA.cardP, "mdf");
			fcA.setVisible(true);
		} else {// 메뉴 추가 dialog
			fcA.setTitle("메뉴 추가");
			fcA.card.show(fcA.cardP, "add");
			fcA.setVisible(true);
		}
	}
	
	
	// ctg별 재고 품목 combobox에 추가하고 선택 초기화
	public void comboItem(String category) {
		ctg = category;
		List<String> list = FoodDao.getInstance().selStockName(ctg);
		fcA.st1C.removeAllItems();
		fcA.st2C.removeAllItems();
		fcA.st3C.removeAllItems();
		
		for(String l : list) {
			fcA.st1C.addItem(l);
			fcA.st2C.addItem(l);
			fcA.st3C.addItem(l);
		}
		
		fcA.st1C.setSelectedItem(null);
		fcA.st1A.setText(null);
		fcA.st2C.setSelectedItem(null);
		fcA.st2A.setText(null);
		fcA.st3C.setSelectedItem(null);
		fcA.st3A.setText(null);
		setMenuB(ctg);
	}

	
	// 메뉴추가&수정
	public void amMenu(String btn) {
		
		// 유효성 검사
		String foodName = fcA.menuTF.getText().trim();
		if(foodName.isEmpty()) {
			JOptionPane.showMessageDialog(fcA, "메뉴 이름을 입력하세요.", "필수입력항목에러", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(btn.equals("추가")) {
			for(String l : list) {
				if(foodName.equals(l)) {
					JOptionPane.showMessageDialog(fcA, "이미 존재하는 메뉴입니다.", "필수입력항목에러", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		}
		
		if(fcA.priceTF.getText().isEmpty()) {
			JOptionPane.showMessageDialog(fcA, "가격을 입력하세요.", "필수입력항목에러", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		if(fcA.st1C.getSelectedItem()==null || fcA.st1A.getText().isEmpty()) {
			JOptionPane.showMessageDialog(fcA, "재료는 1개 이상 선택해야합니다.", "필수입력항목에러", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// Dto에 담아서 Dao를 통해 DB에 insert|update
		foodDto = new FoodDto(); 
		foodDto.setFoodCtg((String)fcA.ctgC.getSelectedItem());
		foodDto.setFoodName(fcA.menuTF.getText());
		foodDto.setFoodPrice(Integer.parseInt(fcA.priceTF.getText()));
		foodDto.setStock1Name(fcA.st1C.getSelectedItem().toString());
		foodDto.setStock1Num(Integer.parseInt(fcA.st1A.getText()));
		
		if(fcA.st2C.getSelectedItem()!=null) {
			foodDto.setStock2Name(fcA.st2C.getSelectedItem().toString());
			foodDto.setStock2Num(Integer.parseInt(fcA.st2A.getText()));
		}
		if(fcA.st3C.getSelectedItem()!=null) {
			foodDto.setStock3Name(fcA.st3C.getSelectedItem().toString());
			foodDto.setStock3Num(Integer.parseInt(fcA.st3A.getText()));
		} 
		int i = FoodDao.getInstance().mergeMenu(foodDto);

		// 결과메세지 출력
		if(i != 0) {
			if(btn.equals("수정")) {
				JOptionPane.showMessageDialog(fcA, "메뉴가 수정되었습니다.", "메뉴수정완료", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(btn.equals("추가")) {
				JOptionPane.showMessageDialog(fcA, "메뉴가 추가되었습니다.", "메뉴추가완료", JOptionPane.INFORMATION_MESSAGE);
			}
			fcA.setVisible(false);
			setMenuB(ctg);
		} else
			JOptionPane.showMessageDialog(fcA, "알 수 없는 오류로 인하여 추가/수정에 실패하였습니다.", "에러", JOptionPane.ERROR_MESSAGE);
	}

	
	// 메뉴삭제
	public void delMenu() {
		
		// 유효성 검사
		String foodName = fcA.menuTF.getText().trim();
		String ctg = fcA.ctgC.getSelectedItem().toString();
		if(foodName.isEmpty()) {
			JOptionPane.showMessageDialog(fcA, "메뉴 이름을 입력하세요.", "필수입력항목에러", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// 삭제여부 확인하고 삭제|취소
		int result = JOptionPane.showConfirmDialog(fcA, "삭제하시겠습니까?", "삭제확인", JOptionPane.YES_NO_OPTION);
		if(result == 0) {
			result = FoodDao.getInstance().delMenu(foodName, ctg);
			if(result != 0) {
				JOptionPane.showMessageDialog(fcA, foodName + ": 삭제 완료되었습니다.", "메뉴삭제완료", JOptionPane.INFORMATION_MESSAGE);
				fcA.setVisible(false);
			} else if(result == 0) {
				JOptionPane.showMessageDialog(fcA, foodName + ": 등록되지 않은 메뉴입니다.", "삭제항목에러", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}else if(result == 1) {
			return;
		}
		
		// 메뉴버튼 초기화
		setMenuB(ctg);
	}

	public void cancleMenu() {//-------------------------------------메뉴 추가/수정 취소
		JOptionPane.showMessageDialog(fcA, "작업을 취소하시겠습니까?", "취소확인", JOptionPane.INFORMATION_MESSAGE);
		fcA.setVisible(false);
		
	}

	
//-----------------------------------------------------------------------------------------------------재고 기능
	
	
	// table 정렬
	public void sTableD() {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(JLabel.CENTER);
		fP.stockTM.stockT.getColumn("No").setCellRenderer(dtcr);
		fP.stockTM.stockT.getColumn("카테고리").setCellRenderer(dtcr);
		fP.stockTM.stockT.getColumn("품목").setCellRenderer(dtcr);
		fP.stockTM.stockT.getColumn("남은 수량").setCellRenderer(dtcr);
		fP.stockTM.stockT.getColumn("단가").setCellRenderer(dtcr);
		fP.stockTM.stockT.getColumn("비고").setCellRenderer(dtcr);
		fP.stockTM.stockT.getColumn("No").setPreferredWidth(10);
		fP.stockTM.stockT.getColumn("품목").setPreferredWidth(100);
		fP.stockTM.stockT.setRowHeight(30);
	}
	
	//table 초기화
	public void setStockT(String ctg) {
		fP.stockTM.list = FoodDao.getInstance().selStock(ctg);
		fP.stockTM.setDataVector(fP.stockTM.list, fP.stockTM.header);
		sTableD();
	}
	
	
	//품목 검색
	public void selStock() {
		String sName = fP.stockName.getText().trim();
		if(sName.isEmpty()) {
			JOptionPane.showMessageDialog(fP, "검색할 품목을 입력하세요", "품목입력에러", JOptionPane.ERROR_MESSAGE);
			return;
		}
		fP.stockTM.list = FoodDao.getInstance().findStockV(sName);
		if(fP.stockTM.list!=null) {
			fP.stockTM.setDataVector(fP.stockTM.list, fP.stockTM.header);
			sTableD();
		}
		else
			JOptionPane.showMessageDialog(fcA, sName + ": 등록되지 않은 품목입니다.", "품목검색에러", JOptionPane.ERROR_MESSAGE);
	}

	
	//dialog
	public void setS() {
		fsA.ctgC.setSelectedItem(null);
		fsA.menuTF.setText("");
		fsA.menuTF.setEnabled(true);
		fsA.amtTF.setText("");
		fsA.priceTF.setText("");
		amtS = 0;
		int x = fP.stockTM.stockT.getSelectedRow();
		String sName;
		if(x > -1)
			sName = fP.stockTM.stockT.getValueAt(x, 2).toString();
		else
			sName = null;
		if(sName != null) {
			stockDto = FoodDao.getInstance().findStockD(sName);
			fsA.ctgC.setSelectedItem(stockDto.getFoodCtg());
			fsA.menuTF.setText(stockDto.getStockName());
			fsA.menuTF.setEnabled(false);
			fsA.priceTF.setText(""+stockDto.getUnitCost());
			fP.stockTM.stockT.clearSelection();
			amtS = stockDto.getRestAmt();
			fsA.setVisible(true);
		} else {
			fsA.setVisible(true);
		}
		
	}

	
	//입고
	public void addStock() {
		if(fsA.ctgC.getSelectedItem()==null) {
			JOptionPane.showMessageDialog(fcA, "카테고리를 선택하세요.", "필수입력항목에러", JOptionPane.ERROR_MESSAGE);
			return;
		}
		ctg = fsA.ctgC.getSelectedItem().toString();
		if(fsA.menuTF.getText().isEmpty()) {
			JOptionPane.showMessageDialog(fcA, "품목을 입력하세요.", "필수입력항목에러", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		if(fsA.amtTF.getText().isEmpty()) {
			JOptionPane.showMessageDialog(fcA, "수량을 입력하세요.", "필수입력항목에러", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		if(fsA.priceTF.getText().isEmpty()) {
			JOptionPane.showMessageDialog(fcA, "단가를 입력하세요.", "필수입력항목에러", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		stockDto = new StockDto(); 
		stockDto.setFoodCtg(ctg);
		stockDto.setStockName(fsA.menuTF.getText());
		stockDto.setRestAmt(Integer.parseInt(fsA.amtTF.getText()));
		stockDto.setUnitCost(Integer.parseInt(fsA.priceTF.getText()));

		int result = FoodDao.getInstance().mergeStock(stockDto);
		if(result != 0) {
			JOptionPane.showMessageDialog(fcA, "입고 처리가 완료되었습니다.", "입고완료", JOptionPane.INFORMATION_MESSAGE);
			fsA.setVisible(false);
			setStockT(ctg);
			setMenuB(ctg);
		}
	}

	
	//입고 취소
	public void cancleStock() {
		JOptionPane.showMessageDialog(fcA, "작업을 취소하시겠습니까?", "취소확인", JOptionPane.INFORMATION_MESSAGE);
		fsA.setVisible(false);
	}
	
	
	//재고 삭제
	public void delStock() {
		int x = fP.stockTM.stockT.getSelectedRow();
		String sName = null;
		String ctg = null;
		if(x > -1) {
			sName = fP.stockTM.stockT.getValueAt(x, 2).toString();
			ctg = fP.stockTM.stockT.getValueAt(x, 1).toString();
		}
		else {
			JOptionPane.showMessageDialog(fP, "삭제할 품목을 선택하세요.", "품목선택에러", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int result = JOptionPane.showConfirmDialog(fP, "삭제하시겠습니까?", "삭제확인", JOptionPane.YES_NO_OPTION);
		if(result == 0) {
			FoodDao.getInstance().delStock(sName);
			JOptionPane.showMessageDialog(fP, sName + ": 삭제 완료되었습니다.", "메뉴삭제완료", JOptionPane.INFORMATION_MESSAGE);
		}else if(result == 1) {
			return;
		}
		setStockT(ctg);
		setMenuB(ctg);
		
	}
	
	
	
//--------------------------------------------------------------------------------------------------------------------------------------------BOOK
	
	public void bTableD() {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(JLabel.CENTER);
		bP.bookTM.bookT.getColumn("No").setCellRenderer(dtcr);
		bP.bookTM.bookT.getColumn("도서종류").setCellRenderer(dtcr);
		bP.bookTM.bookT.getColumn("장르").setCellRenderer(dtcr);
		bP.bookTM.bookT.getColumn("작가명").setCellRenderer(dtcr);
		bP.bookTM.bookT.getColumn("출판사").setCellRenderer(dtcr);
		bP.bookTM.bookT.getColumn("정가").setCellRenderer(dtcr);
		bP.bookTM.bookT.getColumn("보유현황").setCellRenderer(dtcr);
		bP.bookTM.bookT.getColumn("도서명").setCellRenderer(dtcr);
		bP.bookTM.bookT.getColumn("비고").setCellRenderer(dtcr);
		bP.bookTM.bookT.getColumn("No").setPreferredWidth(20);
		bP.bookTM.bookT.getColumn("도서종류").setPreferredWidth(20);
		bP.bookTM.bookT.getColumn("장르").setPreferredWidth(20);
		bP.bookTM.bookT.getColumn("도서명").setPreferredWidth(200);
		bP.bookTM.bookT.setRowHeight(30);
	}
	
	public void bookPage() {
		bP.serchC.setSelectedItem(null);
		mmc.mm.cards.show(mmc.mm.cardP, "도서관리P");
		setBT();
	}


	public void setBT() {//전체 sel
		//db에서 전체 sel (vector)
		//테이블 모델에 지정
		bP.bookTM.list = BookDao.getInstance().serchB(null, null);
		bP.bookTM.setDataVector(bP.bookTM.list, bP.bookTM.header);
		bP.serchC.setSelectedItem(null);
		bP.findTF.setText(null);
		bTableD();
	}

	public void serchB() {//책 검색
		int x = bP.serchC.getSelectedIndex();
		String str = bP.findTF.getText().trim();
		if(x < 0) {
			JOptionPane.showMessageDialog(bP, "검색항목을 선택하세요.", "필수입력항목에러", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(str.isEmpty()) {
			JOptionPane.showMessageDialog(bP, "검색어를 입력하세요.", "필수입력항목에러", JOptionPane.ERROR_MESSAGE);
			return;
		}
		bP.serchC.setSelectedItem(null);
		bP.findTF.setText(null);
		switch (x) {
		case 0 : bP.bookTM.list = BookDao.getInstance().serchB("book_ctg_name", str); break;
		case 1 : bP.bookTM.list = BookDao.getInstance().serchB("genre_name", str); break;
		case 2 : bP.bookTM.list = BookDao.getInstance().serchB("book_name", str); break;
		case 3 : bP.bookTM.list = BookDao.getInstance().serchB("author", str); break;
		default : return;
		}
		if(bP.bookTM.list.size() == 0) {
			JOptionPane.showMessageDialog(bP, "해당하는 도서가 없습니다.", "검색에러", JOptionPane.ERROR_MESSAGE);
			return;
		}
			
		bP.bookTM.setDataVector(bP.bookTM.list, bP.bookTM.header);
		bTableD();
	}

	public void addB() {
		initB();
		bAdd.card.show(bAdd.cardP, "add");
		bAdd.ctgC.setSelectedItem(null);
		bAdd.genreC.setSelectedItem(null);
		bAdd.setVisible(true);
	}
	
	public void mdfB() {
		initB();
		bAdd.card.show(bAdd.cardP, "mdf");
		int x = bP.bookTM.bookT.getSelectedRow();
		if(x < 0) {
			JOptionPane.showMessageDialog(bP, "수정할 도서를 선택하세요.", "도서미선택에러", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String bName = bP.bookTM.bookT.getValueAt(x, 3).toString();
		bookDto = BookDao.getInstance().findB(bName);
		bAdd.ctgC.setSelectedItem(bookDto.ctgDto.getBookCtgName());
		bAdd.genreC.setSelectedItem(bookDto.genDto.getGenreName());
		bAdd.nameTF.setText(bookDto.getBookName());
		bAdd.nameTF.setEnabled(false);
		bAdd.authorTF.setText(bookDto.getAuthor());
		bAdd.publisherTF.setText(bookDto.getPublisher());
		bAdd.priceTF.setText(""+bookDto.getBookPrice());
		bAdd.setVisible(true);
	}

	public void replaceBook() {
		int x = bP.bookTM.bookT.getSelectedRow();
		if(x < 0) {
			JOptionPane.showMessageDialog(bP, "교체할 도서를 선택하세요.", "도서미선택에러", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String bName = bP.bookTM.bookT.getValueAt(x, 3).toString();
		String replace = bP.bookTM.bookT.getValueAt(x, 8).toString();
		if(!replace.equals("교체요망")) {
			JOptionPane.showMessageDialog(bP, "교체 대상 도서가 아닙니다.", "도서선택에러", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int rr = JOptionPane.showConfirmDialog(bP, bName +" : 교체하시겠습니까?", "교체확인", JOptionPane.YES_NO_OPTION);
		if(rr == 0) {
			int result = BookDao.getInstance().replace(bName);
			if(result != 0) {
				JOptionPane.showMessageDialog(bP, bName + " : 교체 완료되었습니다.", "도서교체완료", JOptionPane.INFORMATION_MESSAGE);
			}
			setBT();
		}else if(rr == 1) {
			return;
		}
	}

	public void delBook() {
		int x = bP.bookTM.bookT.getSelectedRow();
		if(x < 0) {
			JOptionPane.showMessageDialog(bP, "삭제할 도서를 선택하세요.", "도서미선택에러", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String bName = bP.bookTM.bookT.getValueAt(x, 3).toString();
		int result = JOptionPane.showConfirmDialog(fcA, "삭제하시겠습니까?", "삭제확인", JOptionPane.YES_NO_OPTION);
		if(result == 0) {
			BookDao.getInstance().delBook(bName);
			JOptionPane.showMessageDialog(bP, bName + ": 삭제 완료되었습니다.", "메뉴삭제완료", JOptionPane.INFORMATION_MESSAGE);
			fcA.setVisible(false);
		}else if(result == 1) {
			bP.bookTM.bookT.clearSelection();
			return;
		}
		setBT();
		
	}


	public void amBook(String btn) {
		if(bAdd.ctgC.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(bAdd, "도서종류를 선택하세요.", "필수입력항목에러", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		if(bAdd.genreC.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(bAdd, "장르를 선택하세요.", "필수입력항목에러", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		if(bAdd.nameTF.getText().trim() == null) {
			JOptionPane.showMessageDialog(bAdd, "도서명을 입력하세요.", "필수입력항목에러", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(bAdd.authorTF.getText().trim() == null) {
			JOptionPane.showMessageDialog(bAdd, "작가명을 입력하세요.", "필수입력항목에러", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		if(bAdd.publisherTF.getText().trim() == null) {
			JOptionPane.showMessageDialog(bAdd, "출판사를 입력하세요.", "필수입력항목에러", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		if(bAdd.priceTF.getText().trim() == null) {
			JOptionPane.showMessageDialog(bAdd, "정가를 입력하세요.", "필수입력항목에러", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		bookDto = new BookDto(); 
		bookDto.setBookCtg(bAdd.ctgC.getSelectedIndex()+1);
		bookDto.setGenreNum(bAdd.genreC.getSelectedIndex()+1);
		bookDto.setBookName(bAdd.nameTF.getText().trim());
		bookDto.setAuthor(bAdd.authorTF.getText().trim());
		bookDto.setPublisher(bAdd.publisherTF.getText().trim());
		bookDto.setBookPrice(Integer.parseInt(bAdd.priceTF.getText().trim()));
		BookDao.getInstance().mergeB(bookDto);		
		if(btn.equals("수정")) {
			JOptionPane.showMessageDialog(bAdd, "도서가 수정되었습니다.", "도서수정완료", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(btn.equals("추가")) {
			JOptionPane.showMessageDialog(bAdd, "도서가 추가되었습니다.", "도서추가완료", JOptionPane.INFORMATION_MESSAGE);
		}
		bAdd.setVisible(false);
		setBT();
	}

	private void initB() {
		bAdd.nameTF.setEnabled(true);
		bAdd.ctgC.removeAllItems();
		bAdd.genreC.removeAllItems();
		List<String> list = BookDao.getInstance().selCol("book_ctg_name", "book_ctg", "category");
		for(String l : list) {
			bAdd.ctgC.addItem(l);
		}
		list = BookDao.getInstance().selCol("genre_name", "genre_num", "genre");
		for(String l : list) {
			bAdd.genreC.addItem(l);
		}
		bAdd.nameTF.setText(null);
		bAdd.authorTF.setText(null);
		bAdd.publisherTF.setText(null);
		bAdd.priceTF.setText(null);
	}


	public void cancleBook() {
		JOptionPane.showMessageDialog(bAdd, "작업을 취소하시겠습니까?", "취소확인", JOptionPane.INFORMATION_MESSAGE);
		bP.bookTM.bookT.clearSelection();
		bAdd.setVisible(false);
	}

//----------------------------------------------------------------------------------------------------------------------------------MEMBER
	
	public void mTableD() {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(JLabel.CENTER);
		mP.memTM.memT.getColumn("No").setCellRenderer(dtcr);
		mP.memTM.memT.getColumn("ID").setCellRenderer(dtcr);
		mP.memTM.memT.getColumn("이름").setCellRenderer(dtcr);
		mP.memTM.memT.getColumn("전화번호").setCellRenderer(dtcr);
		mP.memTM.memT.getColumn("주소").setCellRenderer(dtcr);
		mP.memTM.memT.getColumn("생년월일").setCellRenderer(dtcr);
		mP.memTM.memT.getColumn("천원할인쿠폰").setCellRenderer(dtcr);
		mP.memTM.memT.getColumn("마지막 이용일").setCellRenderer(dtcr);
		mP.memTM.memT.getColumn("대출현황").setCellRenderer(dtcr);
		mP.memTM.memT.getColumn("No").setPreferredWidth(20);
		mP.memTM.memT.getColumn("ID").setPreferredWidth(20);
		mP.memTM.memT.getColumn("이름").setPreferredWidth(30);
		mP.memTM.memT.getColumn("전화번호").setPreferredWidth(50);
		mP.memTM.memT.getColumn("주소").setPreferredWidth(200);
		mP.memTM.memT.setRowHeight(30);
	}
	
	public void memPage() {
		mmc.mm.cards.show(mmc.mm.cardP, "회원관리P");
		mP.findC.setSelectedItem(null);
		selMem();
		
	}
	
	public void selMem() {
		mP.memTM.list = MemDao.getInstance().serchM(null, null);
		mP.findC.setSelectedItem(null);
		mP.memTM.setDataVector(mP.memTM.list, mP.memTM.header);
		mTableD();
	}

	public void serchM() {
		int colN = mP.findC.getSelectedIndex();
		String str = mP.findTF.getText().trim();
		if(colN < 0) {
			JOptionPane.showMessageDialog(bAdd, "검색조건을 선택하세요.", "필수입력항목에러", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(str.isEmpty()) {
			JOptionPane.showMessageDialog(bAdd, "검색어를 입력하세요.", "필수입력항목에러", JOptionPane.ERROR_MESSAGE);
			return;
		}
		switch (colN) {
		case 0 : mP.memTM.list = MemDao.getInstance().serchM("name", str); break;
		case 1 : mP.memTM.list = MemDao.getInstance().serchM("phone_num1 ||phone_num2 ||phone_num3", str); break;
		case 2 : mP.memTM.list = MemDao.getInstance().serchM("to_char(birth, 'yyyymmdd')", str); break;
		}
		mP.memTM.setDataVector(mP.memTM.list, mP.memTM.header);
		
	}


	public void addM() {
		mAdd.nameTF.setText(null);
		mAdd.nameTF.setEnabled(true);
		mAdd.birth1.setEnabled(true);
		mAdd.birth2.setEnabled(true);
		mAdd.birth3.setEnabled(true);
		mAdd.ph1C.setSelectedItem(null);
		mAdd.ph2TF.setText(null);
		mAdd.ph3TF.setText(null);
		mAdd.adrTF.setText(null);
		mAdd.birth1.setText(null);
		mAdd.birth2.setText(null);
		mAdd.birth3.setText(null);
		mAdd.card.show(mAdd.cardP, "add");
		mAdd.setVisible(true);
	}


	public void mdfM() {
		int x = mP.memTM.memT.getSelectedRow();
		String id = null;
		if(x > -1) {
			id = mP.memTM.memT.getValueAt(x, 1).toString();
		}
		else {
			JOptionPane.showMessageDialog(mP, "수정할 사람을 선택하세요.", "선택에러", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		memDto = MemDao.getInstance().findM(id);
		mAdd.nameTF.setText(memDto.getName());
//		mAdd.nameTF.setEnabled(false);
		mAdd.ph1C.setSelectedItem(memDto.getPhoneNum1());
		mAdd.ph2TF.setText(memDto.getPhoneNum2());
		mAdd.ph3TF.setText(memDto.getPhoneNum3());
		mAdd.adrTF.setText(memDto.getAddress());
		Format f = new SimpleDateFormat("yyyy-mm-dd");
		StringTokenizer birth = new StringTokenizer(f.format(memDto.getBirth()), "-");
		mAdd.birth1.setText(birth.nextToken());
		mAdd.birth2.setText(birth.nextToken());
		mAdd.birth3.setText(birth.nextToken());
		mAdd.birth1.setEnabled(false);
		mAdd.birth2.setEnabled(false);
		mAdd.birth3.setEnabled(false);
		mAdd.card.show(mAdd.cardP, "mdf");
		mAdd.setVisible(true);
	}


	public void delM() {
		int x = mP.memTM.memT.getSelectedRow();
		String name = null;
		String id = null;
		if(x > -1) {
			id = mP.memTM.memT.getValueAt(x, 1).toString();
			name = mP.memTM.memT.getValueAt(x, 2).toString();
		}
		else {
			JOptionPane.showMessageDialog(mP, "삭제할 사람을 선택하세요.", "선택에러", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int result = JOptionPane.showConfirmDialog(fcA, "삭제하시겠습니까?", "삭제확인", JOptionPane.YES_NO_OPTION);
		if(result == 0) {
			MemDao.getInstance().delM(id);
			JOptionPane.showMessageDialog(mP, name + ": 삭제 완료되었습니다.", "메뉴삭제완료", JOptionPane.INFORMATION_MESSAGE);
		}else if(result == 1) {
			mP.memTM.memT.clearSelection();
			return;
		}
		selMem();
	}

	public void amMem(String btn) {
		String name = mAdd.nameTF.getText().trim();
		if(name.equals("")) {
			JOptionPane.showMessageDialog(mAdd, "이름을 입력하세요.", "필수입력항목에러", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		
		String ph2 = mAdd.ph2TF.getText().trim();
		String ph3 = mAdd.ph3TF.getText().trim();
		if(mAdd.ph1C.getSelectedItem() == null || ph2.equals("") || ph3.equals("")) {
			JOptionPane.showMessageDialog(mAdd, "전화번호를 입력하세요.", "필수입력항목에러", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(ph2.length() > 4 || ph3.length() > 4) {
			JOptionPane.showMessageDialog(mAdd, "전화번호는 4자리 이하로 입력하세요.", "필수입력항목에러", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String adr = mAdd.adrTF.getText().trim();
		if(adr.equals("")) {
			JOptionPane.showMessageDialog(mAdd, "주소를 입력하세요.", "필수입력항목에러", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String birth1 = mAdd.birth1.getText().trim();
		String birth2 = mAdd.birth2.getText().trim();
		String birth3 = mAdd.birth3.getText().trim();
		if(birth1.equals("") || birth2.equals("") || birth3.equals("")) {
			JOptionPane.showMessageDialog(mAdd, "생년월일을 입력하세요.", "필수입력항목에러", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		if(birth1.length() != 4 || birth2.length() != 2 || birth3.length() != 2) {
			JOptionPane.showMessageDialog(mAdd, "생년월일을 형식에 맞게 입력하세요.", "필수입력항목에러", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		memDto = new MemberDto();
		if(btn.equals("수정")) {
			memDto.setMemberId(mP.memTM.memT.getValueAt(mP.memTM.memT.getSelectedRow(), 1).toString());
		}
		else
			memDto.setMemberId("0");
		memDto.setName(name);
		memDto.setPhoneNum1(mAdd.ph1C.getSelectedItem().toString());
		memDto.setPhoneNum2(ph2);
		memDto.setPhoneNum3(ph3);
		memDto.setAddress(adr);
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		try {
			String birth = birth1 + "-" + birth2 + "-" + birth3;
			memDto.setBirth(df.parse(birth));//String을 util.data로 변환해서 set
		} catch (ParseException e) {
			e.printStackTrace();
		}
		MemDao.getInstance().amM(memDto);		
		if(btn.equals("수정")) {
			JOptionPane.showMessageDialog(bAdd, "회원정보가 수정되었습니다.", "회원수정완료", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(btn.equals("추가")) {
			JOptionPane.showMessageDialog(bAdd, "회원이 추가되었습니다.", "회원추가완료", JOptionPane.INFORMATION_MESSAGE);
		}
		mAdd.setVisible(false);
		selMem();
	}

	public void cancleMem() {
		JOptionPane.showMessageDialog(mAdd, "작업을 취소하시겠습니까?", "취소확인", JOptionPane.INFORMATION_MESSAGE);
		mP.memTM.memT.clearSelection();
		mAdd.setVisible(false);
	}


	


	


}


