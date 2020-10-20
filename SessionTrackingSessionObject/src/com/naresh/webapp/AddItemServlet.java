package com.naresh.webapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		// gathering the request paramters

		String productName = request.getParameter("products"); // selected product

		String noOfQuantities = request.getParameter("quantity"); // no of items input value

		// creating the session object

		if (productName.length() > 0 && noOfQuantities.length() > 0) {

			// creating the new session object for respective user

			HttpSession session = request.getSession(true);

			// session.setmaxInactiveInterval(30);

			if (session != null) {

				session.setAttribute(productName, noOfQuantities);

				pw.println("<div style ='text-align:center;'>");

				pw.println("<a href= 'index.html'>|Home Page|</a> &nbsb; &nbsb; &nbsb;");
				pw.println("<a href='DisplayCartList'>|CheckOut Item|</a>");
				pw.println("</div>");

			} else {

				RequestDispatcher rd = request.getRequestDispatcher("sessionInvalidate.html");

				rd.forward(request, response);
			}

		}

		pw.println();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
