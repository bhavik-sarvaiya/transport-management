package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.connection.DatabaseConnection;

/**
 * Servlet implementation class DeleteServelt
 */
@WebServlet("/delete-user")
public class DeleteServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer userId = Integer.valueOf(request.getParameter("userid"));
		
		Connection con = new DatabaseConnection().getConnction();
		
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM `transport-management`.`user` WHERE (`id` = ?)");
			ps.setInt(1, userId);
			
			int value = ps.executeUpdate();
			
			PrintWriter pw = response.getWriter();
			pw.append("<html><body> " + value + " user is deleted</body></html>");

			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.include(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
