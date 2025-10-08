package com.demo.cookies;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FristServlet
 */
@WebServlet("/frist-servlet")
public class FristServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FristServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		//String userName = Base64.getUrlEncoder().encodeToString(request.getParameter("userName").getBytes(StandardCharsets.UTF_8));
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("Welcome " + userName);
		try {
			Cookie ck=new Cookie("name",userName);  
            response.addCookie(ck);  
			// creating submit button
			out.print("<html><body>" + "<form action='/transport-management/second-servlet' method=\"post\">");
			out.print("<input type='submit' value='go'>");
			out.print("</form></body></html>");

			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
