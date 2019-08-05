package com.plzdaeng.user.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.plzdaeng.dto.AnimalDto;
import com.plzdaeng.dto.BreedDto;
import com.plzdaeng.dto.PetDto;
import com.plzdaeng.dto.TakeVaccinDto;
import com.plzdaeng.dto.UserDto;
import com.plzdaeng.dto.VaccinationDto;
import com.plzdaeng.util.DBClose;
import com.plzdaeng.util.DBConnection;

public class PetDao {

	public int countByPetName(String petName, String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;
		String selectByPetNameSQL = 
				"select count(pet_name) count\r\n" + 
				"from plz_pet\r\n" + 
				"where \r\n" + 
				"    user_id = ? 	\r\n" + 
				"    and pet_name = ?	";
		
		try {
			conn = DBConnection.makeConnection();
			pstmt = conn.prepareStatement(selectByPetNameSQL);
			int index = 0;
			pstmt.setString(++index, userId);
			pstmt.setString(++index, petName);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("count");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return result;
	}

	public List<BreedDto> selectKindByName(String name, String animalCode) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BreedDto> list = new ArrayList<BreedDto>();
		String selectKindByNameSQL = 
				"select \r\n" + 
				"    animal_code\r\n" + 
				"    , breed_code\r\n" + 
				"    , breed_name\r\n" + 
				"from plz_breed\r\n" + 
				"where \r\n" + 
				"    animal_code = ? 	\r\n" + 
				"    and breed_name like '%' || ? || '%'";
		
		try {
			conn = DBConnection.makeConnection();
			pstmt = conn.prepareStatement(selectKindByNameSQL);
			int index = 0;
			pstmt.setString(++index, animalCode);
			pstmt.setString(++index, name);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BreedDto breed = new BreedDto();
				breed.setBreed_code(rs.getString("breed_code"));
				breed.setBreed_name(rs.getString("breed_name"));
				list.add(breed);
			}
			
			//System.out.println(list.size());
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		return list;
	}

	public int insert(PetDto pet) {
		Connection conn = null;
		int result = -1;
		
		try {
			conn = DBConnection.makeConnection();
			conn.setAutoCommit(false);
			
			if(pet.getPet_type().equals("T")) {
				updateMainPet(conn, pet.getUserDto().getUser_id());
			}
			
			insertPet(conn, pet);
			insertTakeVaccin(conn, pet);
			
			conn.commit();
			result = 1;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBClose.close(conn, null);
		}
		
		return result;
	}

	private void insertTakeVaccin(Connection conn, PetDto pet) throws SQLException {
		List<TakeVaccinDto> list = pet.getTakeVaccinList();
		PreparedStatement pstmt = null;
		String insertTakeVaccinSQL = 
				"insert into plz_takevaccin(	\r\n" + 
				"    user_id	\r\n" + 
				"    , pet_name	\r\n" + 
				"    , vaccin_code	\r\n" + 
				"    , take_vaccin_date	\r\n" + 
				")values(	\r\n" + 
				"    ?	\r\n" + 
				"    , ?	\r\n" + 
				"    , ?	\r\n" + 
				"    , to_date(?, 'YYYY/MM/DD')\r\n" + 
				")";
		
		pstmt = conn.prepareStatement(insertTakeVaccinSQL);
		for(TakeVaccinDto take : list) {
			int index = 0;
			pstmt.setString(++index, pet.getUserDto().getUser_id());
			pstmt.setString(++index, pet.getPet_name());
			pstmt.setString(++index, take.getVaccinationDto().getVaccin_code());
			pstmt.setString(++index, take.getTakeVaccinDate());
			
			pstmt.executeUpdate();
		}
	}

	private void insertPet(Connection conn, PetDto pet) throws SQLException {
		PreparedStatement pstmt = null;
		String insertPetSQL = 
				"insert into plz_pet(	\r\n" + 
				"    user_id	\r\n" + 
				"    , pet_name	\r\n" + 
				"    , animal_code	\r\n" + 
				"    , breed_code	\r\n" + 
				"    , pet_gender	\r\n" + 
				"    , birth_date	\r\n" + 
				"    , pet_type	\r\n" + 
				"    , pet_img	\r\n" + 
				")values(	\r\n" + 
				"    ?	\r\n" + 
				"    , ?	\r\n" + 
				"    , ?	\r\n" + 
				"    , ?	\r\n" + 
				"    , ?	\r\n" + 
				"    , to_date(?, 'YYYY/MM/DD')\r\n" + 
				"    , ?	\r\n" + 
				"    , ?	\r\n" + 
				")";
		
		pstmt = conn.prepareStatement(insertPetSQL);
		int index = 0;
		pstmt.setString(++index, pet.getUserDto().getUser_id());
		pstmt.setString(++index, pet.getPet_name());
		pstmt.setString(++index, pet.getBreedDto().getAnimalDto().getAnimal_code());
		pstmt.setString(++index, pet.getBreedDto().getBreed_code());
		pstmt.setString(++index, pet.getPet_gender());
		pstmt.setString(++index, pet.getBirth_date());
		pstmt.setString(++index, pet.getPet_type());
		pstmt.setString(++index, pet.getPet_img());
		pstmt.executeUpdate();
	}

	public List<PetDto> selectPetByUserId(UserDto user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PetDto> petList = new ArrayList<PetDto>();
		String selectPetByUserIdSQL = 
				"select \r\n" + 
				"    pet.user_id\r\n" + 
				"    , pet.pet_name\r\n" + 
				"    , pet.animal_code\r\n" + 
				"    , animal.animal_name\r\n" + 
				"    , pet.breed_code\r\n" + 
				"    , breed.breed_name\r\n" + 
				"    , pet.pet_gender\r\n" + 
				"    , to_char(pet.birth_date, 'yyyy/mm/dd') as birth_date\r\n" + 
				"    , pet.pet_type\r\n" + 
				"    , pet.pet_img\r\n" + 
				"    , takevaccin.vaccin_code\r\n" + 
				"    , vaccin.vaccin_name\r\n" + 
				"    , to_char(takevaccin.take_vaccin_date, 'yyyy/mm/dd') as take_vaccin_date \r\n" + 
				"    , vaccin.vaccin_cycle\r\n" + 
				"from \r\n" + 
				"    plz_pet pet\r\n" + 
				"    left outer join plz_breed breed\r\n" + 
				"        on pet.animal_code = breed.animal_code\r\n" + 
				"        and pet.breed_code = breed.breed_code\r\n" + 
				"    left outer join plz_animal animal\r\n" + 
				"        on breed.animal_code = animal.animal_code\r\n" + 
				"    left outer join plz_takevaccin takevaccin\r\n" + 
				"        on pet.user_id = takevaccin.user_id\r\n" + 
				"        and pet.pet_name = takevaccin.pet_name\r\n" + 
				"    left outer join plz_vaccination vaccin\r\n" + 
				"        on takevaccin.vaccin_code = vaccin.vaccin_code\r\n" + 
				"where \r\n" + 
				"    pet.user_id = ?\r\n" + 
				"order by \r\n" + 
				"    user_id \r\n" + 
				"    , pet_type desc";
		try {
			conn = DBConnection.makeConnection();
			pstmt = conn.prepareStatement(selectPetByUserIdSQL);
			pstmt.setString(1, user.getUser_id());
			rs = pstmt.executeQuery();
			
			//데이터 준비
			PetDto petDto = null;
			List<TakeVaccinDto> takeVaccinList = null;
			String oldPetName = "";
			while(rs.next()) {
				String petName = rs.getString("pet_name");
				if(!oldPetName.equals(petName)) {
					petDto = new PetDto();
					petList.add(petDto);
					oldPetName = petName;
					
					//펫데이터 세팅
					petDto.setPet_name(rs.getString("pet_name"));
					petDto.setPet_gender(rs.getString("pet_gender"));
					petDto.setBirth_date(rs.getString("birth_date"));
					petDto.setPet_type(rs.getString("pet_type"));
					petDto.setPet_img(rs.getString("pet_img"));
					
					//품종
					BreedDto breedDto = new BreedDto();
					petDto.setBreedDto(breedDto);
					breedDto.setBreed_code(rs.getString("breed_code"));
					breedDto.setBreed_name(rs.getString("breed_name"));
					
					//동물
					AnimalDto animalDto = new AnimalDto();
					breedDto.setAnimalDto(animalDto);
					animalDto.setAnimal_code(rs.getString("animal_code"));
					animalDto.setAnimal_name(rs.getString("animal_name"));
				
					//백신리스트 세팅
					takeVaccinList = new ArrayList<TakeVaccinDto>();
					petDto.setTakeVaccinList(takeVaccinList);
				}
				
				//백신
				TakeVaccinDto takeVaccinDto = new TakeVaccinDto();
				takeVaccinDto.setTakeVaccinDate(rs.getString("take_vaccin_date"));
				
				VaccinationDto vaccinationDto = new VaccinationDto();
				takeVaccinDto.setVaccinationDto(vaccinationDto);
				vaccinationDto.setVaccin_code(rs.getString("vaccin_code"));
				vaccinationDto.setVaccin_name(rs.getString("vaccin_name"));
				vaccinationDto.setVaccin_cycle(rs.getInt("vaccin_cycle"));
				
				//백신리스트 추가
				takeVaccinList.add(takeVaccinDto);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		return petList;
	}

	public PetDto selectByPetName(String user_id, String petName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PetDto petDto = null;
		String selectPetByUserIdSQL = 
				"select \r\n" + 
				"    pet.user_id\r\n" + 
				"    , pet.pet_name\r\n" + 
				"    , pet.animal_code\r\n" + 
				"    , animal.animal_name\r\n" + 
				"    , pet.breed_code\r\n" + 
				"    , breed.breed_name\r\n" + 
				"    , pet.pet_gender\r\n" + 
				"    , to_char(pet.birth_date, 'yyyy/mm/dd') as birth_date\r\n" + 
				"    , pet.pet_type\r\n" + 
				"    , pet.pet_img\r\n" + 
				"    , takevaccin.vaccin_code\r\n" + 
				"    , vaccin.vaccin_name\r\n" + 
				"    , to_char(takevaccin.take_vaccin_date, 'yyyy/mm/dd') as take_vaccin_date \r\n" + 
				"    , vaccin.vaccin_cycle\r\n" + 
				"from \r\n" + 
				"    plz_pet pet\r\n" + 
				"    left outer join plz_breed breed\r\n" + 
				"        on pet.animal_code = breed.animal_code\r\n" + 
				"        and pet.breed_code = breed.breed_code\r\n" + 
				"    left outer join plz_animal animal\r\n" + 
				"        on breed.animal_code = animal.animal_code\r\n" + 
				"    left outer join plz_takevaccin takevaccin\r\n" + 
				"        on pet.user_id = takevaccin.user_id\r\n" + 
				"        and pet.pet_name = takevaccin.pet_name\r\n" + 
				"    left outer join plz_vaccination vaccin\r\n" + 
				"        on takevaccin.vaccin_code = vaccin.vaccin_code\r\n" + 
				"where \r\n" + 
				"    pet.user_id = ?\r\n" + 
				"	 and pet.pet_name = ?\r\n" +
				"order by \r\n" + 
				"    user_id \r\n" + 
				"    , pet_type desc";
		try {
			conn = DBConnection.makeConnection();
			pstmt = conn.prepareStatement(selectPetByUserIdSQL);
			pstmt.setString(1, user_id);
			pstmt.setString(2, petName);
			rs = pstmt.executeQuery();
			
			//데이터 준비
			List<TakeVaccinDto> takeVaccinList = null;
			String oldPetName = "";
			while(rs.next()) {
				if(petDto == null) {
					petDto = new PetDto();
					
					//펫데이터 세팅
					petDto.setPet_name(rs.getString("pet_name"));
					petDto.setPet_gender(rs.getString("pet_gender"));
					petDto.setBirth_date(rs.getString("birth_date"));
					petDto.setPet_type(rs.getString("pet_type"));
					petDto.setPet_img(rs.getString("pet_img"));
					
					//품종
					BreedDto breedDto = new BreedDto();
					petDto.setBreedDto(breedDto);
					breedDto.setBreed_code(rs.getString("breed_code"));
					breedDto.setBreed_name(rs.getString("breed_name"));
					
					//동물
					AnimalDto animalDto = new AnimalDto();
					breedDto.setAnimalDto(animalDto);
					animalDto.setAnimal_code(rs.getString("animal_code"));
					animalDto.setAnimal_name(rs.getString("animal_name"));
				
					//백신리스트 세팅
					takeVaccinList = new ArrayList<TakeVaccinDto>();
					petDto.setTakeVaccinList(takeVaccinList);
				}
				
				//백신
				TakeVaccinDto takeVaccinDto = new TakeVaccinDto();
				takeVaccinDto.setTakeVaccinDate(rs.getString("take_vaccin_date"));
				
				VaccinationDto vaccinationDto = new VaccinationDto();
				takeVaccinDto.setVaccinationDto(vaccinationDto);
				vaccinationDto.setVaccin_code(rs.getString("vaccin_code"));
				vaccinationDto.setVaccin_name(rs.getString("vaccin_name"));
				vaccinationDto.setVaccin_cycle(rs.getInt("vaccin_cycle"));
				
				//백신리스트 추가
				takeVaccinList.add(takeVaccinDto);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		return petDto;
	}

	public int update(PetDto pet) {
		Connection conn = null;
		int result = -1;
		try {
			conn = DBConnection.makeConnection();
			conn.setAutoCommit(false);
			
			if(pet.getPet_type().equals("T")) {
				updateMainPet(conn, pet.getUserDto().getUser_id());
			}
			
			//펫 업데이트
			updatePet(conn, pet);
			
			//백신여부 업데이트
			deleteTakeVaccin(conn, pet);
			insertTakeVaccin(conn, pet);
			
			conn.commit();
			result = 1;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBClose.close(conn, null);
		}
		
		//백신업데이트
		
		
		return result;
	}
	private void updateMainPet(Connection conn, String user_id) throws SQLException {
		PreparedStatement pstmt = null;
		String updateMainPetSQL = 
				"update plz_pet\r\n" + 
				"set\r\n" + 
				"    pet_type = 'F'\r\n" + 
				"where\r\n" + 
				"    user_id = ?";
		pstmt = conn.prepareStatement(updateMainPetSQL);
		pstmt.setString(1, user_id);
		pstmt.executeUpdate();
	}
	

	private void deleteTakeVaccin(Connection conn, PetDto pet) throws SQLException {
		PreparedStatement pstmt = null;
		String deleteSQL = 
				"delete plz_takevaccin\r\n" + 
				"where \r\n" + 
				"    user_id = ?	\r\n" + 
				"    and pet_name = ? 	";
		pstmt = conn.prepareStatement(deleteSQL);
		pstmt.setString(1, pet.getUserDto().getUser_id());
		pstmt.setString(2, pet.getPet_name());
		pstmt.executeUpdate();
		
	}

	private void updatePet(Connection conn, PetDto pet) throws SQLException {
		PreparedStatement pstmt = null;
		String updatePetSQL = 
				"update plz_pet\r\n" + 
				"set\r\n" + 
				"    animal_code = ?	\r\n" + 
				"    , breed_code = ?	\r\n" + 
				"    , pet_gender = UPPER(?)	\r\n" + 
				"    , birth_date = to_date(?, 'yyyy/mm/dd')\r\n" + 
				"    , pet_type = UPPER(NVL(?, 'F'))\r\n" + 
				"    , pet_img = NVL(?, pet_img)\r\n" + 
				"where\r\n" + 
				"    user_id = ?\r\n" + 
				"    and pet_name = ?\r\n";
		pstmt = conn.prepareStatement(updatePetSQL);
		int index = 0;
		pstmt.setString(++index, pet.getBreedDto().getAnimalDto().getAnimal_code());
		pstmt.setString(++index, pet.getBreedDto().getBreed_code());
		pstmt.setString(++index, pet.getPet_gender());
		pstmt.setString(++index, pet.getBirth_date());
		pstmt.setString(++index, pet.getPet_type());
		pstmt.setString(++index, pet.getPet_img());
		pstmt.setString(++index, pet.getUserDto().getUser_id());
		pstmt.setString(++index, pet.getPet_name());
		
		pstmt.executeUpdate();
		
	}

}
