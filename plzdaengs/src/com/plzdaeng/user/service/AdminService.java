package com.plzdaeng.user.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.plzdaeng.dto.BreedRegisterDto;
import com.plzdaeng.user.dao.AdminDao;

public class AdminService {
	private AdminDao dao;
	
	public AdminService() {
		dao = new AdminDao();
	}

	public int petKindRegister() {
		/*
		 * 축종코드
		 * 개 : 417000
		 * 고양이 : 422400
		 * 기타 : 429900 
		 * */
		String animalCode = "417000";
		String urlPath = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/kind?up_kind_cd="+ animalCode + "&ServiceKey=";
		String serviceKey = "g066YY%2F%2Fd4D1%2FKBNzd4UniRDi8znS%2B9CpbjpSk25vo4Luk%2BdPR7sn%2FYr0WDMx1uMOlOa5mEkAvQJ85tWVP0XKw%3D%3D";
		
		BufferedReader in = null;
		URLConnection con = null;
		String result = "";
		int rows = 0;
		try {
			//System.out.println(urlPath+serviceKey);
			URL url = new URL(urlPath+serviceKey);
			con = url.openConnection();
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String buffer = null;
			
			while((buffer = in.readLine()) != null) {
				result += buffer;
			}
			JSONObject json = XML.toJSONObject(result);
			JSONObject header = json.getJSONObject("response").getJSONObject("header");
			
			String resultCode = header.getString("resultCode");
			if(resultCode.equals("00")) {
				JSONObject items = json.getJSONObject("response").getJSONObject("body").getJSONObject("items");
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
				List<BreedRegisterDto> list = mapper.readValue(items.getJSONArray("item").toString(), new TypeReference<List<BreedRegisterDto>>() {});
				rows = dao.insertPetKinds(list, animalCode);
				//System.out.println(rows);
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return rows;
	}

}
