/*
 * create table Member(
    Member_ID	varchar2(50),
    Name	varchar2(30),
    Phone_num1	varchar2(3),
    Phone_num2	varchar2(4),
    Phone_num3	varchar2(4),
    Address	varchar2(100),
    Birth	DATE,
    Pay_sum	number(8),
    Cou_birth	number(3),
    Cou_sale	number(3)
); 
 */

package com.kitri.Main.memberDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.kitri.Main.EditMember.*;
import com.kitri.Main.JoinMember.*;
import com.kitri.Main.frame.MainFrame;

public class MemberDao {

	MainFrame mf;
	Connection con;
	Statement st;
	ResultSet rs;
//	ManagerFrame mf;
	JoinMemberFrameService jmfs;
//	EditMemberFrame emf;
	EditMemberFrameService emfs;
	public String newID;

	public MemberDao(MainFrame mf) {
		this.mf = mf;
	}

	public Connection connect() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String id = "project1";
			String pw = "project1";
			con = DriverManager.getConnection(url, id, pw);
			System.out.println("접속 성공");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	public Statement statem() {
		try {
			st = connect().createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return st;
	}

	public int deleteMember(MemberDto dto) {
		int a = 0;
		String sql = "delete from member where member_id = '" + dto.getMember_ID() + "'";
		try {
			a = statem().executeUpdate(sql);
			System.out.println(a > 0 ? "삭제되었습니다." : "삭제실패");

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return a;
	}

	public int insertMember(JoinMemberFrame jmf ) {
		int a = 0;
		String sql = "insert into member "
				+ "values("
				+ "MEMBER_SEQ.NEXTVAL, '"
				+ jmf.tfName.getText()+"','"
				+ jmf.tfPhoneNum1.getText()+"','"
				+ jmf.tfPhoneNum2.getText()+"','"
				+ jmf.tfPhoneNum3.getText()+"','"
				+ jmf.tfAddress.getText()+"','"
				+ jmf.tfBirth.getText()+"','"
				+ 0+"','"
				+ 0+"','"
				+ 0+"','"
				+ 1+"')";
		try {
			a = statem().executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (con != null) 
					con.close();
				if (st != null) 
					st.close();
				if (rs != null) 
					rs.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return a;
	}
	public MemberDto selectMemberId(String str) throws SQLException {//
		String sql = "select * from member where member_id = '"+ str +"'";
		MemberDto md = null;
		
			rs = statem().executeQuery(sql);
			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String phone_Num1 = rs.getString(3);
				String phone_Num2 = rs.getString(4);
				String phone_Num3 = rs.getString(5);
				String address = rs.getString(6);
				
				String birth = rs.getString(7).substring(0, 10);
				int pay_Sum = Integer.parseInt(rs.getString(8));
				int cou_Birth = Integer.parseInt(rs.getString(9));
				int cou_Sale = Integer.parseInt(rs.getString(10));
				
				md = new MemberDto(name, phone_Num1, phone_Num2, phone_Num3, address, birth, pay_Sum, cou_Birth, cou_Sale);
				md.setMember_ID(id);
			}
		
				try {
					if (con != null) 
						con.close();
					if (st != null) 
						st.close();
					if (rs != null) 
						rs.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			

		return md;
	}
	
	public MemberDto select(String str, String phone_num) throws SQLException {
		String sql = "select * from member where name = '"+ str +"' and phone_num2||phone_num3 = '"+ phone_num + "'";
		MemberDto md = null;
		
			rs = statem().executeQuery(sql);
			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String phone_Num1 = rs.getString(3);
				String phone_Num2 = rs.getString(4);
				String phone_Num3 = rs.getString(5);
				String address = rs.getString(6);
				
				String birth = rs.getString(7).substring(0, 10);
				int pay_Sum = Integer.parseInt(rs.getString(8));
				int cou_Birth = Integer.parseInt(rs.getString(9));
				int cou_Sale = Integer.parseInt(rs.getString(10));
				
				md = new MemberDto(name, phone_Num1, phone_Num2, phone_Num3, address, birth, pay_Sum, cou_Birth, cou_Sale);
				md.setMember_ID(id);
			}
		
				try {
					if (con != null) 
						con.close();
					if (st != null) 
						st.close();
					if (rs != null) 
						rs.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			

		return md;
	}

	public MemberDto guestSelect(String str) throws SQLException {
		String sql = "select * from nonmember where member_id = '" + str + "'";
		MemberDto md = null;

		rs = statem().executeQuery(sql);
		while (rs.next()) {
			String id = rs.getString(1);
			String name = rs.getString(2);
			String phone_Num1 = rs.getString(3);
			String phone_Num2 = rs.getString(4);
			String phone_Num3 = rs.getString(5);
			String address = rs.getString(6);

			String birth = rs.getString(7).substring(0, 10);
			int pay_Sum = Integer.parseInt(rs.getString(8));
			int cou_Birth = Integer.parseInt(rs.getString(9));
			int cou_Sale = Integer.parseInt(rs.getString(10));

			md = new MemberDto(name, phone_Num1, phone_Num2, phone_Num3, address, birth, pay_Sum, cou_Birth, cou_Sale);
			md.setMember_ID(id);
		}

		try {
			if (con != null)
				con.close();
			if (st != null)
				st.close();
			if (rs != null)
				rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return md;
	}

	public int managerSelect(String str) throws SQLException {
		String sql = "select * from manager where manager_id = '" + str + "'";
		MemberDto md = null;
		int a = 0;
		rs = statem().executeQuery(sql);
		rs.next();
		a = rs.getRow();
		try {
			if (con != null)
				con.close();
			if (st != null)
				st.close();
			if (rs != null)
				rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return a;
	}

	public int editMember(EditMemberFrame emf) {
		int a = 0;
		String name = emf.tfName.getText();
		String address = emf.tfEAdd.getText();
		String num1 = emf.tfEP1.getText();
		String num2 = emf.tfEP2.getText();
		String num3 = emf.tfEP3.getText();
		String sql = "update member set name = '"+ name +"', address = '" + address + "', phone_num1 = '" + num1 + "', phone_num2 = '" + num2
				+ "', phone_num3 = '" + num3 + "' where member_id = '" + MainFrame.ID
				+ "'";
		try {
			a = statem().executeUpdate(sql);

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return a;
	}
	
	public void rentBook(String member_id) {
		String sql = "select b.book_name, r.return_date, to_number(to_char(sysdate-r.rent_date,'9999')), (book_price/10)*(to_number(to_char(sysdate-r.rent_date,'9999'))) "
				+ "from rent_hstr r , book b "
				+ "where r.book_num = b.book_num "
				+ "and r.member_id = '" + member_id +"'"
				+ "and r.rent_state = 1";
		
		try {
			rs = statem().executeQuery(sql);
			while (rs.next()) {
				Object[] ob  = {rs.getString(1), rs.getDate(2), rs.getString(4), rs.getString(3)};
				mf.book.addRow(ob);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}