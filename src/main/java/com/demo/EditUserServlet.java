package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.connection.DatabaseConnection;

/**
 * Servlet implementation class EditUserServlet
 */
@WebServlet("/edit-user")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer userId = Integer.valueOf(request.getParameter("userid"));
		// Integer loginid = Integer.valueOf(request.getParameter("loginid"));

		try {
			HttpSession session = request.getSession(false);
			String name = (String) session.getAttribute("name");
			Integer sessionLoginId = (Integer) session.getAttribute("sessionLoginId");

			DatabaseConnection dbConnection = new DatabaseConnection();
			Connection conSession = dbConnection.getConnction();

			PreparedStatement psSession = conSession
					.prepareStatement("INSERT INTO `transport-management`.`session_tracing` "
							+ "(`session_id`, `created_time`, `last_access_time`, `action`, `email`) "
							+ "VALUES (?, ?, ?, ?, ?)");
			psSession.setString(1, session.getId());
			psSession.setString(2, String.valueOf(session.getCreationTime()));
			psSession.setString(3, String.valueOf(session.getLastAccessedTime()));
			psSession.setString(4, "edit user");
			psSession.setString(5, name);

			psSession.execute();

			// Register the Driver class
			Class.forName("com.mysql.cj.jdbc.Driver");

			// create connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/transport-management", "root",
					"admin@123");

			Statement st = con.createStatement();
			ResultSet row = st.executeQuery("SELECT * FROM `transport-management`.user where id = " + userId);

			if (row.next()) {
				PrintWriter ps = response.getWriter();
				ps.append("<!doctype html>\r\n" + "<html lang=\"en\">\r\n" + "  <head>\r\n"
						+ "    <meta charset=\"utf-8\">\r\n"
						+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
						+ "    <title>Bootstrap demo</title>\r\n"
						+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB\" crossorigin=\"anonymous\">\r\n"
						+ "  </head><body>");
				ps.append("<div class=\"container\">" + "<ul class=\"nav\">\r\n" + "  <li class=\"nav-item\">\r\n"
						+ "    <a class=\"nav-link active\" aria-current=\"page\" href=\"#\">Home</a>\r\n"
						+ "  </li></ul>");
				ps.append("<h1>Welcome, " + name + "!</h1>");
				ps.append("<h1>Edit User</h1>");

				ps.append("<form action=\"/transport-management/edit-user\" method =\"post\" >\r\n"
						+ "  <div class=\"mb-3\">\r\n"
						+ "    <label for=\"exampleInputEmail1\" class=\"form-label\">Email </label>\r\n"
						+ "    <input type=\"email\" class=\"form-control\" id=\"exampleInputEmail1\" name=\"email\" value ="
						+ row.getString(4) + ">\r\n"
						+ "    <div id=\"emailHelp\" class=\"form-text\">We'll never share your email with anyone else.</div>\r\n"
						+ "  </div>\r\n" + " " + " <div class=\"mb-3\">"
						+ "    <label for=\"exampleInputPassword1\" class=\"form-label\">FristName</label>"
						+ "    <input type=\"text\" class=\"form-control\" id=\"exampleInputPassword1\" name=\"fristname\" value= "
						+ row.getString(2) + ">" + "  </div>" + " <div class=\"mb-3\">"
						+ "    <label for=\"lastname\" class=\"form-label\">LastName</label>"
						+ "    <input type=\"text\" class=\"form-control\" id=\"lastname\" name= \"lastname\" value= "
						+ row.getString(3) + ">" + "  </div>" + " <div class=\"mb-3\">"
						+ "    <label for=\"contactno\" class=\"form-label\">Contact No</label>"
						+ "    <input type=\"text\" class=\"form-control\" id=\"contactno\" name= \"contactno\" value= "
						+ row.getString(5) + ">" + "  </div>" + " <div class=\"mb-3\">"
						+ "    <label for=\"password\" class=\"form-label\">Password</label>"
						+ "    <input type=\"password\" class=\"form-control\" id=\"password\" name= \"password\" value= "
						+ row.getString(6) + ">" + "  </div>" + " <div class=\"mb-3\">"
						+ "    <input type=\"hidden\" class=\"form-control\" id=\"loginid\" name= \"loginid\" value= "
						+ sessionLoginId + ">"
						+ "    <input type=\"hidden\" class=\"form-control\" id=\"userId\" name= \"userId\" value= "
						+ userId + ">" + "  </div>"
						+ "  <button type=\"submit\" class=\"btn btn-primary\">Submit</button>\r\n" + "</form>");
				ps.append(
						"    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI\" crossorigin=\"anonymous\"></script>\r\n");
				ps.append("</div></body></html>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String firstName = request.getParameter("fristname");
			String lastName = request.getParameter("lastname");
			String emailId = request.getParameter("email");
			String contact_no = request.getParameter("contactno");
			String password = request.getParameter("password");
			Integer userId = Integer.valueOf(request.getParameter("userId"));
			Integer loginid = Integer.valueOf(request.getParameter("loginid"));

			// Register the Driver class
			Class.forName("com.mysql.cj.jdbc.Driver");

			// create connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/transport-management", "root",
					"admin@123");

			// insert operation
			PreparedStatement ps2 = con
					.prepareStatement("update `transport-management`.user set email=?, password=?, contact_no=?, "
							+ "fname=?, lname=? where id=?");
			ps2.setString(1, emailId);
			ps2.setString(2, password);
			ps2.setString(3, contact_no);
			ps2.setString(4, firstName);
			ps2.setString(5, lastName);
			ps2.setInt(6, userId);

			ps2.executeUpdate();

			PrintWriter ps = response.getWriter();
			ps.append("<html><body> " + firstName + " is edited</body></html>");

			RequestDispatcher rd = request.getRequestDispatcher("user-list");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
