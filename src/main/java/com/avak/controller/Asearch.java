package com.avak.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Asearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String keyword = req.getParameter("keyword");
		String provider = req.getParameter("provider");
		String url=null;
		switch (provider) {
		case "Google": {
			url = "https://www.google.co.in/search?q=";
			url+=keyword;
			break;
			}
		case "Yahoo": {
			url = "https://search.yahoo.com/search?p=";
			url+=keyword;
			break;
		}
		case "Bing": {
			url = "https://www.bing.com/search?q=";
			url+=keyword;
			break;
		}
		}
		resp.sendRedirect(url);

	}

}
