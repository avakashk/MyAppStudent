package com.avak.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Gsearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String keyword=req.getParameter("keyword");
		
		String gainger="https://www.grainger.com/search?searchBar=true&searchQuery=";
		String url=gainger+keyword;
		
		ServletContext context=getServletContext();
		Enumeration<String> att= context.getAttributeNames();
		while(att.hasMoreElements())
		{
			System.out.println(att.nextElement());
		}
		
		Enumeration<String> parm= context.getInitParameterNames();
		while(parm.hasMoreElements())
		{
			System.out.println(parm.nextElement());
		}
		
		String name=context.getInitParameter("author");
		System.out.println(name);
		
		resp.sendRedirect(url);
		
	}
	

}
