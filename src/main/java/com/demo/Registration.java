package com.demo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class demo
 */
@WebServlet("/registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Registration() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		// TODO Auto-generated method stub
		response.getWriter().append("<html><body  style=\"background-color:powderblue; padding: 10%;\">")
				.append("<center><form style=\"padding: solid;\" action=\"/transport-managenent/add-user\" method =\"post\" >")
				.append("<label for=\"fname\">First name:</label><br>\r\n"
						+ "  <input type=\"text\" id=\"fname\" name=\"fname\" value=\"John\"><br>\r\n"
						+ "  <label for=\"lname\">Last name:</label><br>\r\n"
						+ "  <input type=\"text\" id=\"lname\" name=\"lname\" value=\"Doe\"><br><br>\r\n"

						+ "  <label for=\"email\">Email:</label><br>\r\n"
						+ "  <input type=\"text\" id=\"email\" name=\"email\" value=\"johe@gmail.com\"><br><br>\r\n"

						+ "  <label for=\"contact_no\">Conatct NO:</label><br>\r\n"
						+ "  <input type=\"text\" id=\"conatct_no\" name=\"contact_no\" value=\"+917867542322\"><br><br>\r\n"

						+ "  <label for=\"password\">Password:</label><br>\r\n"
						+ "  <input type=\"password\" id=\"password\" name=\"password\"><br><br>\r\n"

				).append("  <input type=\"submit\" value=\"Submit\">").append("</center></body></html>");

	}

}
