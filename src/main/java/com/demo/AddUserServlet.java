package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/add-user")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		response.getWriter().append("<h1>Email id : " + email + " <br> Password : " + pwd + "</h1>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String emailId = request.getParameter("email");
		String contact_no = request.getParameter("contact_no");
		String password = request.getParameter("password");

		try {
			// Register the Driver class
			Class.forName("com.mysql.cj.jdbc.Driver");

			// create connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/transport-management", "root",
					"admin@123");

			// insert operation
			PreparedStatement ps2 = con.prepareStatement(
					"INSERT INTO `transport-management`.`user`(`fname`,`lname`,`email`,`contact_no`,`password`) VALUES(?,?,?,?,?)");
			ps2.setString(1, firstName);
			ps2.setString(2, lastName);
			ps2.setString(3, emailId);
			ps2.setString(4, contact_no);
			ps2.setString(5, password);

			int inserted = ps2.executeUpdate();

			PrintWriter ps = response.getWriter();
			ps.append("<html><body><center><b  style=\" color: green; padding :5px\">"
					+ inserted + " user register successfully</b></center></body></html>");
			// Navigate you page
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.include(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
