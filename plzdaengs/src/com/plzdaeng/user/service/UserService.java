package com.plzdaeng.user.service;

import java.io.*;
import java.net.*;

import org.apache.tomcat.jdbc.pool.ConnectionPool;

import com.plzdaeng.dto.UserDetailDto;
import com.plzdaeng.dto.UserDto;
import com.plzdaeng.user.dao.UserDao;
import com.plzdaeng.util.ProfileCreate;

public class UserService {
	private UserDao dao;
	
	public UserService(){
		dao = new UserDao();
	}
	
	public int userJoin(UserDto userDto) {
		
		String authority = userDto.getAuthority();
		String userImg = userDto.getUser_img();
		int result = -1;
		//유저로 세팅
		if(authority == null) {
			userDto.setAuthority("U");
		}
		//디비에 데이터 입력
		result = dao.insert(userDto);
		return result;
		
	}

	//id 체크
	public int idCheck(String id) {
		return dao.selectById(id);
	}

	public StringBuffer zipSearchWeb(String doro, int currentPage) {
		String key = "g066YY%2F%2Fd4D1%2FKBNzd4UniRDi8znS%2B9CpbjpSk25vo4Luk%2BdPR7sn%2FYr0WDMx1uMOlOa5mEkAvQJ85tWVP0XKw%3D%3D";
		int countPerPage = 50;
		StringBuffer resultXML = new StringBuffer();
		String apiUrl = "http://openapi.epost.go.kr/postal/retrieveNewAdressAreaCdSearchAllService/retrieveNewAdressAreaCdSearchAllService/getNewAddressListAreaCdSearchAll?ServiceKey=g066YY%2F%2Fd4D1%2FKBNzd4UniRDi8znS%2B9CpbjpSk25vo4Luk%2BdPR7sn%2FYr0WDMx1uMOlOa5mEkAvQJ85tWVP0XKw%3D%3D"
				+ "&countPerPage=" +  countPerPage 
				+ "&currentPage=" + currentPage
				+ "&srchwrd=";
		
		BufferedReader in = null;
		try {
			System.out.println(apiUrl + URLEncoder.encode(doro, "UTF-8"));
			URL url = new URL(apiUrl + URLEncoder.encode(doro, "UTF-8"));
			in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			String temp = null;
			while(true) {
				temp = in.readLine();
				if(temp == null) {
					break;
				}
				resultXML.append(temp);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return resultXML;
	}
	
	//로그인 프로세스
	public UserDto userLogin(UserDto userDto) {
		
		return dao.selectById(userDto);
	}

	public UserDto userModify(UserDto userDto) {
		return dao.selectDetailById(userDto);
	}

	public int userUpdate(UserDto userDto) {		
		return dao.update(userDto);
	}
}
