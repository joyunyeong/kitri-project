package com.plzdaeng.chart.controller;

import java.util.List;

import org.json.JSONArray;

public class ChartService {
	private ChartDao dao;
	
	public ChartService() {
		dao = new ChartDao();
	}
	
	public String animalBreedRanking(int maxRanking) {
		JSONArray jsonArray = dao.animalBreedRanking(maxRanking);
		return jsonArray.toString();
	}
	
	public String genderAvgAgeForBreed(String breedName) {
		JSONArray jsonArray = dao.genderAvgAgeForBreed(breedName);
		return jsonArray.toString();
	}

}
