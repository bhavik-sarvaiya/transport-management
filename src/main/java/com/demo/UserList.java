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

/**
 * Servlet implementation class UserList
 */
@WebServlet("/user-list")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer loginid = Integer.valueOf(request.getParameter("loginid"));
		System.out.println("login id"+ loginid);
		try {
			// Register the Driver class
			Class.forName("com.mysql.cj.jdbc.Driver");

			// create connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/transport-management", "root",
					"admin@123");

			// insert operation
			PreparedStatement ps2 = con
					.prepareStatement("SELECT * FROM `transport-management`.user where id = ?");
			ps2.setInt(1, loginid);
			

			ResultSet rs = ps2.executeQuery();
			PrintWriter ps = response.getWriter();
			if (rs.next()) {

				ps.append("<!doctype html>\r\n" + "<html lang=\"en\">\r\n" + "  <head>\r\n"
						+ "    <meta charset=\"utf-8\">\r\n"
						+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
						+ "    <title>Bootstrap demo</title>\r\n"
						+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB\" crossorigin=\"anonymous\">\r\n"
						+ "  </head><body>");
				ps.append("<div class=\"container\">" + "<ul class=\"nav\">\r\n" + "  <li class=\"nav-item\">\r\n"
						+ "    <a class=\"nav-link active\" aria-current=\"page\" href=\"#\">Home</a>\r\n"
						+ "  </li></ul>");
				ps.append("<h1>Welcome, " + rs.getString(2) + "!</h1>");

				Statement st = con.createStatement();
				ResultSet row = st.executeQuery("SELECT * FROM `transport-management`.user");

				ps.append("<table class=\"table table-dark table-hover\">\r\n" + "  <thead>\r\n" + "    <tr class=\"table-info\">\r\n"
						+ "      <th scope=\"col\">#</th>\r\n" + "      <th scope=\"col\">User Name</th>\r\n"
						+ "      <th scope=\"col\">Email</th>\r\n" 
						+ "      <th scope=\"col\">Contact NO</th>\r\n"
						+ "      <th scope=\"col\" colspan=\"2\">Action</th>\r\n"
						+ "    </tr>\r\n" + "  </thead><tbody>");
				while (row.next()) {
					ps.append("    <tr>\r\n" 
							+ "      <th scope=\"row\">"+row.getInt(1)+"</th>\r\n"
							+ "      <td>"+row.getString(2)+" "+row.getString(3)+"</td>\r\n" 
							+ "      <td>"+row.getString(4)+"</td>\r\n" 
							+ "      <td>"+row.getString(5)+"</td>\r\n"
							+ "<td><a href=\"edit-user?userid="+row.getInt(1)+"&loginid="+rs.getInt(1)+"\"><button type=\"button\" class=\"btn btn-success\">Edit</button></a></td>"
							+ "<td><a href=\"delete-user?userid="+row.getInt(1)+"&loginid="+rs.getInt(1)+"\"><button type=\"button\" class=\"btn btn-danger\">Delete</button></a></td>"
							);
				}
				ps.append("</tbody> </table>");
				ps.append(
						"    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI\" crossorigin=\"anonymous\"></script>\r\n");
				ps.append("</div></body></html>");
			} else {
				ps.append("<html><body><center><b  style=\" color: red; padding :5px\"> "
						+ "please check email and password.</b></center></body></html>");

				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.include(request, response);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
