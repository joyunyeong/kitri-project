package com.plzdaeng.chat.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plzdaeng.chat.service.ChatService;
import com.plzdaeng.dto.ChatDto;
import com.plzdaeng.util.ChatMap;

@ServerEndpoint("/chatserver")
public class ChatServer{
	private Map<Integer, List<Session>> chatMap;
	private ChatService service;
	
    public ChatServer() {
        super();
        service = new ChatService();
        chatMap = ChatMap.getChaMap();
    }
	
	@OnMessage
	public void onMessage(String msg, Session session) {
		System.out.println(msg);
		ObjectMapper mapper = new ObjectMapper();
		try {
			ChatDto chatDto = mapper.readValue(msg, ChatDto.class);
			//System.out.println(chatDto);
			int result = service.insertMsg(chatDto);
			if(result == 1) {
				//정상작동
				List<Session> list = chatMap.get(chatDto.getGroup_id());
				System.out.println(list);
				String sendMsg = mapper.writeValueAsString(chatDto);
				for(Session client : list) {
					client.getBasicRemote().sendText(sendMsg);
				}	
			} else {
				//비정상
			}
		} catch (JsonParseException e1) {
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@OnOpen
	public void handleOpen(Session session) {
		System.out.println("클라이언트 입장 : " + session);
		String groupId = session.getRequestParameterMap().get("groupid").get(0);
		//System.out.println("chatmap : " + chatMap);
		List<Session> sessionList = chatMap.get(Integer.parseInt(groupId));
		//System.out.println("sessionList : " + sessionList);
		if(sessionList == null) {
			sessionList = new ArrayList<Session>();
			chatMap.put(Integer.parseInt(groupId), sessionList);
		}
		sessionList.add(session);
		
		//System.out.println("chatMap : " + chatMap);
		//System.out.println("session groupid : " + session.getQueryString());
		
		//시작할때 기존의 대화를 불러온다
		List<ChatDto> chatHistory = service.chatHistory(groupId);
		ObjectMapper mapper = new ObjectMapper();
		try {
			if(chatHistory.size() == 0) {
				return;
			}
			String historyJSON = mapper.writeValueAsString(chatHistory);
			System.out.println(historyJSON);
			session.getBasicRemote().sendText(historyJSON);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@OnClose
	public void handleClose(Session session) {
		System.out.println("클라이언트 퇴장 : "+session);
		String groupId = session.getRequestParameterMap().get("groupid").get(0);
		//System.out.println("그룹id : " + groupId);
		List<Session> list = chatMap.get(Integer.parseInt(groupId));
		list.remove(session);
	}
	
	
}
