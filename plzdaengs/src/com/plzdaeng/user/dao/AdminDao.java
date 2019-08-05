package com.plzdaeng.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.plzdaeng.dto.BreedRegisterDto;
import com.plzdaeng.util.DBClose;
import com.plzdaeng.util.DBConnection;

public class AdminDao {

	public int insertPetKinds(List<BreedRegisterDto> list, String animalCode) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String insertPetKindsSQL = 
				"merge into plz_breed breed\r\n" + 
				"    using dual\r\n" + 
				"        on (breed.breed_code = ? \r\n" + 
				"            and breed.animal_code = ? )\r\n" + 
				"when matched then\r\n" + 
				"    update set\r\n" + 
				"        breed_name = ? \r\n" + 
				"when not matched then\r\n" + 
				"    insert(\r\n" + 
				"    animal_code\r\n" + 
				"    , breed_code\r\n" + 
				"    , breed_name\r\n" + 
				"    )values(\r\n" + 
				"        ?	\r\n" + 
				"        , ?	\r\n" + 
				"        , ?	\r\n" + 
				"    )";
		int result = 0;
		
		try {
			conn = DBConnection.makeConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(insertPetKindsSQL);
			
			for(BreedRegisterDto breed : list) {
				int index = 0;
				pstmt.setString(++index, breed.getKindCd());
				pstmt.setString(++index, animalCode);
				pstmt.setString(++index, breed.getKnm());
				pstmt.setString(++index, animalCode);
				pstmt.setString(++index, breed.getKindCd());
				pstmt.setString(++index, breed.getKnm());
				result += pstmt.executeUpdate();
			}
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return result;
	}

}
