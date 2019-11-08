package com.avak.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AllStudentStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

//			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
//			String url="jdbc:mysql://localhost:3306/jdbcservletdb?user=root&password=devilxworkshop";
//			con=DriverManager.getConnection(url);

		// Verifying cookies

		boolean foundCookie = false;

		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("sid")) {
				foundCookie = true;
			}
		}
		if (foundCookie) {

			try {

				ServletContext context = getServletContext();
				Class.forName(context.getInitParameter("dname")).newInstance();
				con = DriverManager.getConnection(context.getInitParameter("dburl"), context.getInitParameter("user"),
						context.getInitParameter("password"));

				String query = "select s.regno,s.firstname,s.lastname,g.gfirstname,g.glastname,o.isadmin from Student_info s inner join guardian_info g on s.regno=g.regno inner join stud_otherinfo o on s.regno=o.regno";
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);

				response.setContentType("text/html");
				PrintWriter out = response.getWriter();

				RequestDispatcher dispatcher = request.getRequestDispatcher("header.jsp");
				dispatcher.include(request, response);

				String table = "<html>\r\n" + "<head>\r\n" + "<meta charset=\"ISO-8859-1\">\r\n"
						+ "<title>Insert title here</title>\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n" + "<table>\r\n"
						+ " <tr>\r\n" + "	<th>Reg No</th>\r\n" + "	<th>First Name</th>\r\n"
						+ "	<th>Last Name</th>\r\n" + "	<th>Guardian First Name</th>\r\n"
						+ "	<th>Guardian Last name</th>\r\n" + "	<th>Admin</th>\r\n" + "\r\n" + " </tr>";

				out.print(table);
				while (rs.next()) {
					int regno = rs.getInt("regno");
					String fname = rs.getString(2);
					String lname = rs.getString(3);
					String gfname = rs.getString(4);
					String glname = rs.getString(5);
					String isadmin = rs.getString(6);

					String s = "<tr>\r\n" + "	<td>" + regno + "</td>\r\n" + "	<td>" + fname + "</td>\r\n" + "	<td>"
							+ lname + "</td>\r\n" + "	<td>" + gfname + "</td>\r\n" + "	<td>" + glname + "</td>\r\n"
							+ "	<td>" + isadmin + "</td>\r\n" + " </tr>\r\n";

					out.println(s);
				}
				out.print("</table>\r\n" + "\r\n" + "</body>\r\n" + "</html>");

				request.getRequestDispatcher("footer.jsp").include(request, response);

			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					con.close();
					stmt.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

}
