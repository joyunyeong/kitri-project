package com.kitri.Main.JoinMember;

import java.sql.*;

public class JoinMemberFrameService {
	JoinMemberFrame jmf;
	JoinMemberFrameController jmfc;
	Connection con;
	Statement st;
	ResultSet rs;
	
	public JoinMemberFrameService(JoinMemberFrameController jmfc) {
		this.jmfc = jmfc;
	}
	
	public void clear() {
		jmfc.jmf.tfAddress.setText("");
		jmfc.jmf.tfBirth.setText("");
		jmfc.jmf.tfName.setText("");
		jmfc.jmf.tfPhoneNum1.setText("");
		jmfc.jmf.tfPhoneNum2.setText("");
		jmfc.jmf.tfPhoneNum3.setText("");
	}
	
//	public int insertMember() {
//		int a = 0;
//		String sql = "insert into member "
//				+ "values('"
//				+ (jmfc.jmf.tfName.getText()+jmfc.jmf.tfPhoneNum2.getText()+jmfc.jmf.tfPhoneNum3.getText()) + "','"
//				+ jmfc.jmf.tfName.getText()+"','"
//				+ jmfc.jmf.tfPhoneNum1.getText()+"','"
//				+ jmfc.jmf.tfPhoneNum2.getText()+"','"
//				+ jmfc.jmf.tfPhoneNum3.getText()+"','"
//				+ jmfc.jmf.tfAddress.getText()+"','"
//				+ jmfc.jmf.tfBirth.getText()+"','"
//				+ 0+"','"
//				+ 0+"','"
//				+ 0+"')";
//		try {
//			int a = statem().executeUpdate(sql);
//			if (a>0) {
//				JOptionPane.showMessageDialog(null, "회원 등록 성공!");
//				jmfc.jmf.dispose();
//			} else {
//				JOptionPane.showMessageDialog(null, "회원등록 실패");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				if (con != null) 
//					con.close();
//				if (st != null) 
//					st.close();
//				if (rs != null) 
//					rs.close();
//				
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//	
	public boolean isNumber(String a) {
		boolean flag=true;
		for (int i = 1; i < a.length(); i++) {
			int num = a.charAt(i)-48;
			if (num<0||num>9) {
				flag = false;
				break;
			}
		}
		return flag;
	}
	
}
