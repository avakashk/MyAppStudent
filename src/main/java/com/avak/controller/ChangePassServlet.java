package com.avak.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChangePassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		if (req.getParameter("npassword").equals(req.getParameter("cpassword"))) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				String url = "jdbc:mysql://localhost:3306/jdbcservletdb?user=root&password=devilxworkshop";
				con = DriverManager.getConnection(url);

				String query = "update stud_otherinfo set password=? where regno=? and password = ?";
				pstmt = con.prepareStatement(query);

				pstmt.setString(1, req.getParameter("npassword"));
				pstmt.setInt(2, Integer.parseInt(req.getParameter("regno")));
				pstmt.setString(3, req.getParameter("password"));

				int i = pstmt.executeUpdate();
				if (i > 0) {
					out.print("Password change Successful");
				} else {
					out.print("Failed to Change Password");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					con.close();
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
		}else
		{
			out.print("The new passwords doesn't match");
		}

	}

}
