package com.avak.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/jdbcservletdb?user=root&password=devilxworkshop";
			con = DriverManager.getConnection(url);

			String query = " select s.regno,s.firstname,s.lastname,g.gfirstname,g.glastname,o.isadmin from student_info s inner join guardian_info g on s.regno=g.regno inner join stud_otherinfo o on g.regno=o.regno where s.regno=? and o.password=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(request.getParameter("regno")));
			pstmt.setString(2, request.getParameter("password"));

			rs = pstmt.executeQuery();
			
			//Adding Cookie After Sucessfull Log in
			
			Cookie ck=new Cookie("sid",request.getParameter("regno"));
			response.addCookie(ck);

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if (rs.next()) {
				int regno = rs.getInt("regno");
				String fname = rs.getString(2);
				String lname = rs.getString(3);
				String gfname = rs.getString(4);
				String glname = rs.getString(5);
				String isadmin = rs.getString(6);
				request.getRequestDispatcher("header.jsp").include(request, response);

				String output = "<html>\r\n" + "<head>\r\n" + "<meta charset=\"ISO-8859-1\">\r\n"
						+ "<title>Insert title here</title>\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n" + "<table>\r\n"
						+ " <tr>\r\n" + "	<th>Reg No</th>\r\n" + "	<th>First Name</th>\r\n"
						+ "	<th>Last Name</th>\r\n" + "	<th>Guardian First Name</th>\r\n"
						+ "	<th>Guardian Last name</th>\r\n" + "	<th>Admin</th>\r\n" + "\r\n" + " </tr>\r\n"
						+ " <tr>\r\n" + "	<td>" + regno + "</td>\r\n" + "	<td>" + fname + "</td>\r\n" + "	<td>"
						+ lname + "</td>\r\n" + "	<td>" + gfname + "</td>\r\n" + "	<td>" + glname + "</td>\r\n"
						+ "	<td>" + isadmin + "</td>\r\n" + " </tr>\r\n" + "</table>\r\n";
				out.println(output);
				if (isadmin.equalsIgnoreCase("Y")) {
					String s = "<a href=\"/MyAppStudent/allstudent\">Click Here </a> to view all student.";
					out.println(s);
				}
				out.println("</pre>\r\n" + "</body>\r\n" + "</html>");
			} else {
				out.print("Incorrect Credentials");
			}
			request.getRequestDispatcher("header.jsp").include(request, response);

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
	}

}
