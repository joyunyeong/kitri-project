package com.kitri.Main.frame;

import java.awt.*;
import java.sql.SQLException;
import java.text.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

import com.kitri.Main.dto.Basket;
import com.kitri.Main.memberDto.MemberDto;
import com.kitri.Main.swing.box.FPanel;
import com.kitri.Main.voucher.VoucherDto;

public class MainFrameService {

	MainController mc;

	public MainFrameService(MainController mc) {
		this.mc = mc;
	}

	public void paymentComplete() {
		FPanel f = findFp();
		f.bookDtoBasket.clear();
		f.foodDtoBasket.clear();
		f.voucherDtoBasket.clear();
		f.endTime = "";
		f.startTime = "";
		f.mid = "";
		f.b = false;
		f.labelName.setText("");
		f.labelTime.setText("");
		f.labelPrice.setText("");
		f.setBackground(new Color(245, 245, 245));
		mc.mf.vt.remove(MainFrame.ID);
		logout();
	}

	public ImageIcon imageConvert(String fileName) {
//		ImageIcon icon = new ImageIcon("C:\\Users\\Administrator\\Pictures\\memberIcon\\" + fileName);
		ImageIcon icon = new ImageIcon("C:\\Users\\이세현\\Pictures\\memberIcon" + fileName);
		ImageIcon editIcon = new ImageIcon(icon.getImage().getScaledInstance(85, 79, Image.SCALE_SMOOTH));
		return editIcon;
	}

	public void currentTime() {
		Date d = new Date();
		Format f = new SimpleDateFormat("HH:mm:ss");
		mc.mf.time = f.format(d);
		mc.mf.getTimeL().setText(mc.mf.time);
	}

	public void currentDate() {
		Date d = new Date();
		Format f = new SimpleDateFormat("yyyy/MM/dd");
		mc.mf.date = f.format(d);
		mc.mf.getDateL().setText(mc.mf.date);
	}

	public void login(String str) throws SQLException {// 자리 눌러서 로그인할때
		System.out.println("여기ㅗㄹ 왔니");
		MemberDto dto = mc.mf.dao.selectMemberId(str);
		if (dto != null) {

			MainFrame.ID = dto.getMember_ID();
			mc.mf.labelLogInName.setText(dto.getName());
			mc.mf.labelLogInBirth.setText(dto.getBirth());
			mc.mf.labelCouponNum1.setText(String.valueOf((dto.getCou_Birth())));
			mc.mf.labelCouponNum2.setText(String.valueOf((dto.getCou_Sale())));
			mc.mf.card.show(mc.mf.panelMember, "회원로그인");
			mc.mf.tfMemberID.setText("");
			mc.mf.tablePayment.setVisible(true);
		} else {
			
			JOptionPane.showMessageDialog(null, "회원이 존재하지 않습니다. 회원아이디를 확인해주세요.");
		}

	}

	public void login2(String name, String phoneNum) throws SQLException {// 이름 전화번호 쳐서 로그인할때
		System.out.println("> 회원전용 로그인 Service");
		MemberDto dto = mc.mf.dao.select(name, phoneNum);
		if (dto != null) {
			MainFrame.ID = dto.getMember_ID();
			mc.mf.labelLogInName.setText(dto.getName());
			mc.mf.labelLogInBirth.setText(dto.getBirth());
			mc.mf.labelCouponNum1.setText(String.valueOf((dto.getCou_Birth())));
			mc.mf.labelCouponNum2.setText(String.valueOf((dto.getCou_Sale())));
			mc.mf.card.show(mc.mf.panelMember, "회원로그인");
			mc.mf.tfMemberID.setText("");
			mc.mf.tablePayment.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "회원이 존재하지 않습니다. 회원 ID를 확인해주세요.");
		}

	}

	public void guestLogin(String str) throws SQLException {

		MemberDto dto = mc.mf.dao.guestSelect(str);
		if (dto != null) {
			MainFrame.ID = dto.getMember_ID();
			mc.mf.labelLogInName.setText(dto.getName());
			mc.mf.labelLogInBirth.setText(dto.getBirth());
			mc.mf.labelCouponNum1.setText(String.valueOf((dto.getCou_Birth())));
			mc.mf.labelCouponNum2.setText(String.valueOf((dto.getCou_Sale())));
			mc.mf.card.show(mc.mf.panelMember, "회원로그인");
			mc.mf.tfMemberID.setText("");
			mc.mf.tablePayment.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "회원이 존재하지 않습니다. 회원 ID를 확인해주세요.");
		}

	}

	public void logout() {
		mc.mf.labelLogInName.setText("");
		mc.mf.labelLogInBirth.setText("");
		mc.mf.labelCouponNum1.setText("");
		mc.mf.labelCouponNum2.setText("");
		MainFrame.ID = "";
		mc.mf.tfPhoneNum.setText("");
		mc.mf.fullPrice.setText("");
		mc.mf.pay.setNumRows(0);
		mc.mf.book.setNumRows(0);
		mc.mf.card.show(mc.mf.panelMember, "LogIn");
	}

	public boolean isNumber(String a) {
		boolean flag = true;
		for (int i = 1; i < a.length(); i++) {
			int num = a.charAt(i) - 48;
			if (num < 0 || num > 9) {
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	public boolean isNotNumber(String a) {
		boolean flag = true;
		for (int i = 1; i < a.length(); i++) {
			int num = a.charAt(i) - 48;
			if (num >= 0 && num <= 9) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	public String secToHHMM(long secs) {
		int hour, min, sec;
		sec = ((int) secs % 60) % 60;
		min = (int) secs / 60 % 60;
		hour = (int) secs / 3600;
		String timerBuffer = String.format("%02d:%02d:%02d", hour, min, sec);
		return timerBuffer;
	}

	public FPanel findFp() {// 자리 찾는 용도
		FPanel f = null;
		int len = mc.mf.fp.length;
		for (int i = 0; i < len; i++) {
			if (mc.mf.fp[i].mid.equals(MainFrame.ID)) {
				f = mc.mf.fp[i];
				break;
			}
		}
		return f;
	}
	public void appendingPayment(FPanel f) {
		int k;
		int size=0;
		size = f.bookDtoBasket.size();
		if (size!=0) {
			for (k = 0; k < size; k++) {
				Basket bd = f.bookDtoBasket.get(k);
				Object[] ob = { k + 1,"도서", bd.getBookDto().getBookName(),((bd.getBookDto()).getBookPrice()/10),
						bd.getCount(), ((bd.getBookDto()).getBookPrice()/10) * (bd.getCount()), ""};
				mc.mf.pm.model.addRow(ob);
			}
				
		}
		
		int size2 = f.foodDtoBasket.size();
		if (size2!=0) {
			for (k = 0; k < size2; k++) {
				Basket fd = f.foodDtoBasket.get(k);
				Object[] ob = { size+k+ 1,"음식", fd.getFoodDto().getFoodName(),fd.getFoodDto().getFoodPrice(),
						fd.getCount(),
						fd.getFoodDto().getFoodPrice() * fd.getCount(),"" };
				mc.mf.pm.model.addRow(ob);
	
			}
		}
		overTime();
		int size3 = f.voucherDtoBasket.size();
		if (size3!=0) {
			for (k = 0; k < size3; k++) {
				Basket bvd = f.voucherDtoBasket.get(k);
				Object[] ob = { size+size2+k + 1,"이용권", bvd.getVoucherDto().getName(), (bvd.getVoucherDto()).getPrice(), bvd.getCount(),
						(bvd.getVoucherDto()).getPrice() * (bvd.getCount()),"" };
				mc.mf.pm.model.addRow(ob);
			}
		}
	
	}
	
	public void printTable(FPanel f) {
		mc.mf.pay.setNumRows(0);
		mc.mf.book.setNumRows(0);
		int k;
		int size;
		size = f.bookDtoBasket.size();
		if (size!=0) {
			for (k = 0; k < size; k++) {
				Basket bd = f.bookDtoBasket.get(k);
				Object[] ob = { k + 1, "책", bd.getBookDto().getBookName(),
						bd.getCount(),
						((bd.getBookDto()).getBookPrice()/10) * (bd.getCount()) };
				mc.mf.pay.addRow(ob);
			}
				
		}
		
		int size2 = f.foodDtoBasket.size();
		if (size2!=0) {
			for (k = 0; k < size2; k++) {
				Basket fd = f.foodDtoBasket.get(k);
				Object[] ob = { size +k+ 1, "음식", fd.getFoodDto().getFoodName(),
						fd.getCount(),
						fd.getFoodDto().getFoodPrice() * fd.getCount() };
				mc.mf.pay.addRow(ob);
	
			}
		}
		overTime();
		int size3 = f.voucherDtoBasket.size();
		if (size3!=0) {
			for (k = 0; k < size3; k++) {
				Basket bvd = f.voucherDtoBasket.get(k);
				Object[] ob = { size+size2+k + 1, "이용권", bvd.getVoucherDto().getName(), bvd.getCount(),
						(bvd.getVoucherDto()).getPrice() * (bvd.getCount()) };
				mc.mf.pay.addRow(ob);
			}
		}
		
		
		int total = 0;
		int a = mc.mf.tablePayment.getRowCount();
		if (a!=0) {
			for (int j = 0; j <mc.mf.tablePayment.getRowCount() ; j++) {
				int amont = Integer.parseInt(String.valueOf(mc.mf.tablePayment.getValueAt(j, 4)));
				total = total + amont;
			}
		}
		
		f.labelPrice.setText(String.valueOf(total));
		mc.mf.fullPrice.setText(String.valueOf(total));
		mc.mf.dao.rentBook(MainFrame.ID);
		
	}

	public void overTime() {
		FPanel f = findFp();
		int totalpaymentTime = 0;
		int paymentTime = 0;
		int len = f.voucherDtoBasket.size();
		for (int i = 0; i < len; i++) {
			if (f.voucherDtoBasket.get(i).getVoucherDto().getName().equals("시간추가")) {
				f.voucherDtoBasket.remove(i);
				break;
			}
			Basket b = f.voucherDtoBasket.get(i);
			paymentTime = (b.getVoucherDto().getSec() * b.count);
			totalpaymentTime = totalpaymentTime + paymentTime;
		}
		long i = f.a - totalpaymentTime;
		if (i > 0) {
			Basket b = new Basket(mc.overTime, (int)(i / (mc.overTime.getSec())));
			if (b.getCount()>=1) {
				f.voucherDtoBasket.add(b);
			}
			
		} else {
			return;
		}

	}

	public void addVoucher(VoucherDto vd) {
		mc.mf.pay.setNumRows(0);
		FPanel f = findFp();
		if (MainFrame.ID.isEmpty()||f==null) {
			JOptionPane.showMessageDialog(mc.mf, "로그인 먼저 해주세요");
		} else {
			int len2 = f.voucherDtoBasket.size();
			if (len2 == 0) {
				Basket basket = new Basket(vd, 1);
				f.voucherDtoBasket.add(basket);
				System.out.println("1");
				printTable(f);

			} else {
				for (int j = 0; j < len2; j++) {
					Basket b = f.voucherDtoBasket.get(j);
					if (b.getVoucherDto().getName().equals(vd.getName())) {
						b.setCount(b.getCount() + 1);
						printTable(f);

						break;
					} else if (j == len2 - 1) {
						Basket basket = new Basket(vd, 1);
						f.voucherDtoBasket.add(basket);
						System.out.println("2");
						printTable(f);
					}
				}
			}
		}
		mc.mf.comboBoxVoucher.setSelectedIndex(0);
	
	}

	public void addBook(Basket bd) {
		FPanel f = findFp();
		int len2 = f.bookDtoBasket.size();
		if (len2 == 0) {
			f.bookDtoBasket.add(bd);
		} else {
			for (int j = 0; j < len2; j++) {
				Basket b = f.bookDtoBasket.get(j);
				if (b.getBookDto().getBookName().equals(bd.getBookDto())) {
					b.setCount(b.getCount() + 1);
					mc.mf.pay.setNumRows(0);
					break;
				} else if (j == len2 - 1) {
					f.foodDtoBasket.add(bd);
				}
			}
		}
		printTable(f);
	}

	public void bMProcess(int a, FPanel f, List<Basket> b) {
		Object ob1 = mc.mf.tablePayment.getValueAt(a, 2);
		String str1 = String.valueOf(ob1);
		Object ob2 = mc.mf.tablePayment.getValueAt(a, 3);
		int t = Integer.parseInt(String.valueOf(ob2));
		int len = b.size();
		if (t == 1) {
			for (int i = 0; i < len; i++) {
				if (b.get(i).toString().equals(str1)) {
					b.remove(i);
					printTable(f);
					break;
				}
			}

		} else {
			for (int i = 0; i < len; i++) {
				if (b.get(i).toString().equals(str1)) {
					Basket basket = b.get(i);
					int c = basket.getCount();
					basket.setCount(c - 1);
					printTable(f);
					break;

				}
			}
		}
	}

}
