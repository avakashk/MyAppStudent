package com.avak.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avak.model.StudentBean;

public class CreateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentBean stu = new StudentBean();
		stu.setRegno(Integer.parseInt(request.getParameter("regno")));
		stu.setFname(request.getParameter("fname"));
		stu.setLname(request.getParameter("lname"));
		stu.setGfname(request.getParameter("gfname"));
		stu.setGlname(request.getParameter("glname"));
		stu.setPassword(request.getParameter("password"));
		stu.setAdmin(request.getParameter("admin"));

		Connection con = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		boolean foundCookie = false;

		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("sid"))
			{
				foundCookie = true;
			}
		}
		if (foundCookie) {

			try {

				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				String url = "jdbc:mysql://localhost:3306/jdbcservletdb?user=root&password=devilxworkshop";
				con = DriverManager.getConnection(url);

				String stquery = "insert into student_info values(?,?,?)";
				String gtquery = "insert into guardian_info values(?,?,?)";
				String otquery = "insert into stud_otherinfo values(?,?,?)";

				stmt = con.prepareStatement(stquery);
				stmt1 = con.prepareStatement(gtquery);
				stmt2 = con.prepareStatement(otquery);

				stmt.setInt(1, Integer.parseInt(request.getParameter("regno")));
				stmt.setString(2, request.getParameter("fname"));
				stmt.setString(3, request.getParameter("lname"));
				stmt1.setInt(1, Integer.parseInt(request.getParameter("regno")));
				stmt1.setString(2, request.getParameter("gfname"));
				stmt1.setString(3, request.getParameter("glname"));
				stmt2.setInt(1, Integer.parseInt(request.getParameter("regno")));
				stmt2.setString(3, request.getParameter("password"));
				stmt2.setString(2, request.getParameter("admin"));

				int i = stmt.executeUpdate();
				int i1 = stmt1.executeUpdate();
				int i2 = stmt2.executeUpdate();

				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				request.getRequestDispatcher("footer.jsp").include(request, response);

				if (i > 0 & i1 > 0 & i2 > 0) {
					out.print("Registration Successful");

				} else {
					System.err.print("Registration Unsuccessful");
				}

				request.getRequestDispatcher("footer.jsp").include(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					con.close();
					stmt.close();
					stmt1.close();
					stmt2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

}
