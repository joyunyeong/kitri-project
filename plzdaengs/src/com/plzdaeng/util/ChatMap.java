package com.plzdaeng.util;

import java.util.*;

import javax.websocket.Session;

public class ChatMap{
	private static Map<Integer, List<Session>> chatMap;
	
	static {
		chatMap = new HashMap<Integer, List<Session>>();
	}
	private ChatMap() {}
	
	public static Map<Integer, List<Session>> getChaMap(){
		return chatMap;
	}
}
