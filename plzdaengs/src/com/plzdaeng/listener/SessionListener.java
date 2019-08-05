package com.plzdaeng.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class SessionListener implements HttpSessionAttributeListener {

    public SessionListener() {
        // TODO Auto-generated constructor stub
    }

    public void attributeAdded(HttpSessionBindingEvent e)  { 
//    	String attrName = e.getName();
//    	if(attrName.equals("loginInfo")) {
//    		loginCnt++;
//    		System.out.println("현재 로그인 인원 : " + loginCnt);
//    		System.out.println(e.getValue() + "님이 로그인하셨습니다!");
//    	}
    }

    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
