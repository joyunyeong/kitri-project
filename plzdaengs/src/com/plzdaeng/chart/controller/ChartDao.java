package com.plzdaeng.chart.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.plzdaeng.util.DBClose;
import com.plzdaeng.util.DBConnection;

public class ChartDao {

	public JSONArray animalBreedRanking(int maxRanking) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray jsonArray = new JSONArray();
		String animalBreedRankingSQL = 
				"select *\r\n" + 
				"from (\r\n" + 
				"        select\r\n" + 
				"            rownum as ranking\r\n" + 
				"            , pet_breed_group_count.*\r\n" + 
				"        from (\r\n" + 
				"                select\r\n" + 
				"                    pet.animal_code\r\n" + 
				"                    , breed.breed_name\r\n" + 
				"                    , pet.breed_code\r\n" + 
				"                    , animal.animal_name\r\n" + 
				"                    , count(*) as count\r\n" + 
				"                from \r\n" + 
				"                    plz_pet pet\r\n" + 
				"                    left outer join plz_breed breed\r\n" + 
				"                        on pet.animal_code = breed.animal_code\r\n" + 
				"                        and pet.breed_code = breed.breed_code\r\n" + 
				"                    left outer join plz_animal animal\r\n" + 
				"                        on breed.animal_code = animal.animal_code\r\n" + 
				"                group by pet.animal_code, pet.breed_code, breed.breed_name, animal.animal_name\r\n" + 
				"                order by count desc\r\n" + 
				"            ) pet_breed_group_count\r\n" + 
				"    )\r\n" + 
				"where ranking <= ?";
		
		try {
			conn = DBConnection.makeConnection();
			pstmt = conn.prepareStatement(animalBreedRankingSQL);
			pstmt.setInt(1, maxRanking);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("ranking", rs.getInt("ranking"));
				jsonObject.put("animal_code", rs.getString("animal_code"));
				jsonObject.put("animal_name", rs.getString("animal_name"));
				jsonObject.put("breed_code", rs.getString("breed_code"));
				jsonObject.put("breed_name", rs.getString("breed_name"));
				jsonObject.put("breed_name", rs.getString("breed_name"));
				jsonObject.put("count", rs.getInt("count"));
				
				jsonArray.put(jsonObject);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		
		return jsonArray;
	}
	
	public JSONArray genderAvgAgeForBreed(String breedName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray jsonArray = new JSONArray();
		String genderAvgAgeForBreedSQL = 
				"select\r\n" + 
				"    decode(pet_gender, 'M' , '남아', 'F', '여아', '') gender\r\n" + 
				"    , ceil(avg(age)) age\r\n" + 
				"    , count(*) count\r\n" + 
				"from (\r\n" + 
				"        select\r\n" + 
				"            to_number(to_char(sysdate, 'yyyy')) - to_number(to_char(pet.birth_date, 'yyyy')) + 1 as age\r\n" + 
				"            , pet.pet_gender\r\n" + 
				"        from \r\n" + 
				"            plz_pet pet\r\n" + 
				"            left outer join plz_breed breed\r\n" + 
				"                on pet.animal_code = breed.animal_code\r\n" + 
				"                and pet.breed_code = breed.breed_code\r\n" + 
				"            left outer join plz_animal animal\r\n" + 
				"                on breed.animal_code = animal.animal_code\r\n" + 
				"        where \r\n" + 
				"            breed.breed_name = ? \r\n" + 
				"    ) breedAge\r\n" + 
				"group by pet_gender";
		
		try {
			conn = DBConnection.makeConnection();
			pstmt = conn.prepareStatement(genderAvgAgeForBreedSQL);
			pstmt.setString(1, breedName);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("gender", rs.getString("gender"));
				jsonObject.put("avgAge", rs.getInt("age"));
				jsonObject.put("count", rs.getInt("count"));
				
				jsonArray.put(jsonObject);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return jsonArray;
	}
	
}
