package com.avak.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LogoutServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter out=resp.getWriter();
		
		// Cookie method of log out
		
		/*Cookie[] cookies=req.getCookies();
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		for(Cookie cookie:cookies)
		{
			if(cookie.getName().equals("sid"))
			{
				cookie.setMaxAge(0);
				resp.addCookie(cookie);
				
				out.println("You have successfully logged out of the website");
				break;
			}
		}
		req.getRequestDispatcher("login.jsp").include(req, resp);*/
		
		// Session method of log out
		
		HttpSession session=req.getSession(false);
		if(session!=null)
		{
			session.invalidate();
			out.print("You have successfully logged out");
		}else
		{
			out.print("You have not logged in yet");
		}
		
		req.getRequestDispatcher("login.jsp").forward(req, resp);


		
	}

}
