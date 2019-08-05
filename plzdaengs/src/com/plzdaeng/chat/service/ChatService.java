package com.plzdaeng.chat.service;

import java.util.List;

import com.plzdaeng.chat.dao.ChatDao;
import com.plzdaeng.dto.ChatDto;

public class ChatService {
	private ChatDao dao;
	
	public ChatService() {
		dao = new ChatDao();
	}
	
	public int insertMsg(ChatDto chatDto) {
		return dao.insert(chatDto);
	}

	public List<ChatDto> chatHistory(String groupId) {
		return dao.selectAllByGroupId(groupId);
	}

}
