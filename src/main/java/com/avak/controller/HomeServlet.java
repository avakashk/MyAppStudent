package com.avak.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();

		// Cookie way of identity verification
		
		/*Cookie[] cookies = request.getCookies();
		int regno=0;
		boolean foundCookie = false;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("sid")) {
				regno = Integer.parseInt(cookie.getValue());
				foundCookie = true;
			} 
		}
		if (foundCookie) {*/
		
		// Session way of identity verification
		
		
		HttpSession session=request.getSession(false);
		if(session!=null)
		{
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int regno=(Integer)session.getAttribute("regno");

			try {
				Class.forName(getServletContext().getInitParameter("dname")).newInstance();
				con = DriverManager.getConnection(getServletContext().getInitParameter("dburl"),
						getServletContext().getInitParameter("user"), getServletContext().getInitParameter("password"));
				String sql = "select s.regno,s.firstname,s.lastname,g.gfirstname,g.glastname,o.isadmin from student_info s inner join guardian_info g on s.regno=g.regno inner join stud_otherinfo o on g.regno=o.regno where s.regno=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, regno);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					String fname = rs.getString(2);
					String lname = rs.getString(3);
					String gfname = rs.getString(4);
					String glname = rs.getString(5);
					String isadmin = rs.getString(6);
					request.getRequestDispatcher("header.jsp").include(request, response);

					String output = "<html>\r\n" + "<head>\r\n" + "<meta charset=\"ISO-8859-1\">\r\n"
							+ "<title>Insert title here</title>\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n"
							+ "<table>\r\n" + " <tr>\r\n" + "	<th>Reg No</th>\r\n" + "	<th>First Name</th>\r\n"
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
		}else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}
}
