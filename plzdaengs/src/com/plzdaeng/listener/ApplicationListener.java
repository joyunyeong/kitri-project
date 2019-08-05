package com.plzdaeng.listener;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

import com.plzdaeng.util.SiteConstance;

@WebListener
public class ApplicationListener implements ServletContextListener {

 
    public ApplicationListener() {
        // TODO Auto-generated constructor stub
    }


    public void contextDestroyed(ServletContextEvent e)  { 
         // TODO Auto-generated method stub
    }

    public void contextInitialized(ServletContextEvent e)  { 
    	ServletContext context = e.getServletContext();
    	context.setAttribute("chatServerUrl", SiteConstance.CHAT_SERVER_URL);
    }
	
}
