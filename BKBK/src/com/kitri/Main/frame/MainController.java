package com.kitri.Main.frame;

import java.awt.Color;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

import com.kitri.Main.dto.Basket;
import com.kitri.Main.swing.box.*;
import com.kitri.Main.voucher.VoucherDto;

public class MainController implements ActionListener, MouseListener {

	public MainFrame mf;
	public MainFrameService mfs;
	VoucherDto vou1 = new VoucherDto("1시간 이용권", 2500, 10);
	VoucherDto vou2 = new VoucherDto("2시간 이용권", 5000, 20);
	VoucherDto vou3 = new VoucherDto("3시간 이용권", 7500, 30);
	VoucherDto overTime = new VoucherDto("시간추가", 500, 5);

	public MainController(MainFrame managerFrame) {

		this.mf = managerFrame;
		mfs = new MainFrameService(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		String str = e.getActionCommand();
		String combo = mf.comboBoxVoucher.getSelectedItem().toString();
		if (ob == mf.tfPhoneNum || str.equals("회원로그인")) {
			memberLoginProcess();
		} else if (str.equals("비회원")) {
			guestLoginProcess();
		} else if (str.equals("도서대출")) {// ---------------------------------------------------------------------------------[h]도서대출btn
			bookRentProcess();
//			mf.rentalMain.rc.rs.bookListAppend();
		} else if (str.equals("회원등록")) {
			joinMemberProcess();
		} else if (str.equals("회원정보수정")) {
			nonmemBlock();
		} else if (str.equals("음식주문")) {
			foodOrderProcess();// ------------------------------------------------------------------------[h]음식주문btn
								// >>> FoodPanel 여기 로그인 되어있을 때만 넘어가도록 조건설정
		} else if (str.equals("관리자")) {
			managerProcess();
		} else if (str.equals("결제")) {
			paymentProcess();
		} else if (str.equals("Logout")) {
			mfs.logout();
		} else if (str.equals("-")) {
			buttonMinusProcess();
		} else if (ob == mf.buttonMcancel) {
			mf.tfManagerId.setText("");
			mf.panelManager.setVisible(false);
		} else if (str.equals("LogIn")) {
			managerLogInProcess();
		} else if (ob == mf.tfMemberID) {
			mf.tfPhoneNum.requestFocus();
		} else if (ob == mf.tfManagerId) {
			managerLogInProcess();
		}
		if (combo.equals("1시간 이용권")) {
			mfs.addVoucher(vou1);
		} else if (combo.equals("2시간 이용권")) {
			mfs.addVoucher(vou2);
		} else if (combo.equals("3시간 이용권")) {
			mfs.addVoucher(vou3);
		}
		mf.comboBoxVoucher.setSelectedIndex(0);
		return;
	}

	private void bookRentProcess() {
		StringBuffer sb = new StringBuffer(MainFrame.ID);
		int a = sb.indexOf("비회원");
		if (MainFrame.ID.isEmpty() || a >= 0) {
			return;
		} else {
			mf.rentalMain.setVisible(true);
		}
	}

	private void foodOrderProcess() {
		if (MainFrame.ID.isEmpty()) {
			return;
		} else {
			mf.serCard.show(mf.panelCard, "Food");
		}
	}

	private void nonmemBlock() {
		StringBuffer sb = new StringBuffer(MainFrame.ID);
		int a = sb.indexOf("비회원");
		if (MainFrame.ID.isEmpty() || a >= 0) {
			return;
		} else {
			editMemberProcess();
		}
	}

	private void paymentProcess() { 
		mf.pm.id.setText(MainFrame.ID);
		mf.serCard.show(mf.panelCard, "pay");
		FPanel f = mfs.findFp();
		mfs.appendingPayment(f);
		tatalPrice();
		mf.pm.payR.setText(String.valueOf(Integer.parseInt(mf.pm.totalR.getText()) - Integer.parseInt(mf.pm.discountR.getText())));

	}

	public void tatalPrice() { 
		int total = 0;
		int a = mf.pm.model.getRowCount();
		if (a != 0) {
			for (int j = 0; j < mf.pm.model.getRowCount(); j++) {
				int amont = Integer.parseInt(String.valueOf(mf.pm.model.getValueAt(j, 5)));
				total = total + amont;
			}
		} else { // [08.05 윤영 추가] 메인에서 결제액 0일 경우
			JOptionPane.showMessageDialog(null, "결제 예정인 금액이 없습니다.");
		}
		mf.pm.totalR.setText(String.valueOf(total));
	}

//		//대여목록을 어떻게 넘길 건지 내일 이야기해보기

	private void managerLogInProcess() {
		char[] c = mf.tfManagerId.getPassword();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < c.length; i++) {
			sb.append(c[i]);
		}
		String str = String.valueOf(sb);
		int a;
		try {
			a = mf.dao.managerSelect(str);
			if (a > 0) {
				// 관리자 페이지로 입장
				mf.serCard.show(mf.panelCard, "Manager");
				mf.tfManagerId.setText("");
				mf.panelManager.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(mf, "관리자 번호가 다릅니다. 확인해주세요.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void managerProcess() {
		mfs.logout();
		mf.managerMain.mmc.mms.foodPage();
		mf.panelManager.setVisible(true);
	}

	private void buttonMinusProcess() {
		int a = mf.tablePayment.getSelectedRow();
		if (a == -1) {
			return;
		} else {
			Object ob = mf.tablePayment.getValueAt(a, 1);
			String str = String.valueOf(ob);
			FPanel f = mfs.findFp();
			switch (str) {
			case "이용권":
				mfs.bMProcess(a, f, f.voucherDtoBasket);
				break;
			case "음식":
				return;
			case "책":
				mfs.bMProcess(a, f, f.bookDtoBasket);
				break;
			}
		}
	}

	private void memberLoginProcess() {
		try {
			String str = mf.tfMemberID.getText();
			String str1 = mf.tfPhoneNum.getText();
			mfs.login2(str, str1);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void guestLoginProcess() {
		NumberFormat nf = new DecimalFormat("00");
		int size = mf.vt.size();
		if (size == 0) {
			try {
				mfs.guestLogin("비회원01");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			for (int i = 1; i < 31; i++) {
				String str = "비회원" + (nf.format(i));
				if (mf.vt.indexOf(str) == -1) {
					try {
						mfs.guestLogin(str);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				} else if (i == 31) {
					JOptionPane.showMessageDialog(mf, "더이상 테이블이 없습니다.");
				}
			}
		}
		mf.image.setIcon(mfs.imageConvert("Guest.png"));
	}

	private void joinMemberProcess() {
		mf.jmf.setVisible(true);
	}

	private void editMemberProcess() {
		if (!mf.labelLogInName.getText().isEmpty()) {
			mf.emf.labelBirth.setText(mf.labelLogInBirth.getText());
			mf.emf.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(mf, "로그인을 먼저해주세요.");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		LocalDateTime oldTime = LocalDateTime.now();
		if (e.getSource() instanceof FPanel) {
			FPanel fp = (FPanel) e.getSource();
			int i = Integer.parseInt(fp.getName());
			FPanel f = mf.fp[i];
			if (f.startTime.isEmpty()) {
				f.startTime = oldTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
			}
			if (!MainFrame.ID.isEmpty() && mf.fp[i].mid.isEmpty()) {
				if (mf.vt.indexOf(MainFrame.ID) == -1) {
					f.b = true;
					mf.vt.add(MainFrame.ID);
					f.mid = MainFrame.ID;
					f.setBackground(new Color(51, 153, 255));
					f.labelName.setText(mf.labelLogInName.getText());
					f.labelPrice.setText("0");

					f.th = new Thread(new Runnable() {

						@Override
						public void run() {
							while (f.b) {

								LocalDateTime ldt = LocalDateTime.now();
								f.a = Duration.between(oldTime, ldt).toMillis() / 1000;
								String str = mfs.secToHHMM(f.a);
								f.endTime = ldt.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

								f.labelTime.setText(str);
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					});
					f.th.start();
					mf.card.show(mf.panelMember, "LogIn");
					mfs.logout();

				} else {
					JOptionPane.showMessageDialog(mf, "이미 이용중인 손님입니다.");
					mfs.logout();
				}
			} else if (mf.labelLogInName.getText().isEmpty() && !f.labelName.getText().isEmpty()) {
				String str = f.mid;
				StringBuffer sb = new StringBuffer(str);
				mfs.logout();
				if (sb.indexOf("비회원") == -1) {
					try {
						mfs.login(str);
						mfs.printTable(f);
					} catch (SQLException e1) {

						e1.printStackTrace();
					}

				} else {
					try {
						mfs.guestLogin(str);
						mfs.printTable(f);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			} else {
				System.out.println(MainFrame.ID);
				System.out.println(f.mid);
				return;
			}
		} else {
			return;
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
