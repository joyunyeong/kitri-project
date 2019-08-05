package com.kitri.Main.EditMember;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.kitri.Main.frame.*;
import com.kitri.Main.memberDto.*;
import com.kitri.Main.swing.box.FPanel;

public class EditMemberFrameController implements ActionListener {
	EditMemberFrame emf;
	EditMemberFrameService emfs;

	public EditMemberFrameController(EditMemberFrame emf) {
		this.emf = emf;
		emfs = new EditMemberFrameService(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		String str = e.getActionCommand();
		if (ob == emf.buttonEM) {
			emProcess();
		} else if (str.equals("취소")) {
			emf.mf.mc.mfs.logout();
			emf.emfc.emfs.clear();
			emf.setVisible(false);

		} else if (ob == emf.tfName) {
			if (emf.mf.mc.mfs.isNotNumber(emf.tfName.getText()) && !emf.mf.mc.mfs.isNumber(emf.tfName.getText())) {
				emf.tfEAdd.requestFocus();
			} else {
				JOptionPane.showMessageDialog(emf, "이름엔 한글이나 영문으로 써주세요. 빈칸안된다구요.(한숨)");
				emf.tfName.setText("");
			}
		} else if (ob == emf.tfEAdd) {
			emf.tfEP1.requestFocus();
		} else if (ob == emf.tfEP1) {
			if (emf.mf.mc.mfs.isNumber(emf.tfEP1.getText()) == true) {
				emf.tfEP2.requestFocus();
			} else {
				JOptionPane.showMessageDialog(emf, "전화번호는 숫자만 입력가능합니다.");
				emf.tfEP1.setText("");
			}
		} else if (ob == emf.tfEP2) {
			if (emf.mf.mc.mfs.isNumber(emf.tfEP2.getText()) == true) {
				emf.tfEP3.requestFocus();
			} else {
				JOptionPane.showMessageDialog(emf, "전화번호는 숫자만 입력가능합니다.");
				emf.tfEP2.setText("");
			}
		} else if (ob == emf.tfEP3) {
			if (emf.mf.mc.mfs.isNumber(emf.tfEP3.getText()) == true) {
				emProcess();
			} else {
				JOptionPane.showMessageDialog(emf, "전화번호는 숫자만 입력가능합니다.");
				emf.tfEP3.setText("");

			}
		}

	}

	private void emProcess() {
		String address = emf.tfEAdd.getText();
		String p1 = emf.tfEP1.getText();
		String p2 = emf.tfEP2.getText();
		String p3 = emf.tfEP3.getText();
		String name = emf.tfName.getText();
		if (emf.mf.mc.mfs.isNotNumber(name) && !emf.mf.mc.mfs.isNumber(name)) {
			if (emf.mf.mc.mfs.isNumber(p1) == true) {
				if (emf.mf.mc.mfs.isNumber(p2) == true) {
					if (emf.mf.mc.mfs.isNumber(p3) == true) {
						int a = emf.mf.dao.editMember(emf);
						if (a > 0) {
							JOptionPane.showMessageDialog(emf, "회원정보수정 성공!!!");
							try {
								MemberDto md;
								md = emf.mf.dao.selectMemberId(MainFrame.ID);
								FPanel f = emf.mf.mc.mfs.findFp();
								if (f!=null) {
									f.labelName.setText(md.getName());
									emf.mf.mc.mfs.logout();
									emf.emfc.emfs.clear();
									emf.setVisible(false);
								} else {
									emf.mf.mc.mfs.logout();
									emf.emfc.emfs.clear();
									emf.setVisible(false);
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(emf, "회원정보수정 실패...");
						}
					} else {
						JOptionPane.showMessageDialog(emf, "전화번호는 숫자만 입력가능합니다.");
						emf.tfEP3.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(emf, "전화번호는 숫자만 입력가능합니다.");
					emf.tfEP2.setText("");
				}
			} else {
				JOptionPane.showMessageDialog(emf, "전화번호는 숫자만 입력가능합니다.");
				emf.tfEP1.setText("");
			}
		} else {
			JOptionPane.showMessageDialog(emf, "이름엔 한글이나 영문으로 써주세요. 빈칸안된다구요(한숨)");
			emf.tfName.setText("");
		}

		
	}

}
