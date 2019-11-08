package com.avak.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LogoutServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Cookie[] cookies=req.getCookies();
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
		req.getRequestDispatcher("login.jsp").include(req, resp);

	}

}
