package com.naresh.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DisplayCartList extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		pw.println("<body bgcolor='cyan'>");

		pw.println("<h4 style='text-align:center;color:red;'>Welcome for Session Tracking using Session Object</h4>");

		// getting the existing session object
		HttpSession session = request.getSession(false);

		pw.println("<table align='center' border='2'>");
		pw.println(
				"<tr style='color:red;'><td>Prdouct Name</td><td> Product Quantity</td><td> Product Price</td></tr>");

		// Displaying the session object information related logic

		if (session != null) {

			// reading the all data from the session object

			Enumeration e = session.getAttributeNames();

			int totalPrice = 0;

			while (e.hasMoreElements()) {

				String productName = (String) e.nextElement();

				String productQuantity = (String) session.getAttribute(productName);
				int priceValue = Integer.parseInt(productQuantity) * 100;
				totalPrice += priceValue;

				pw.println("<tr>");

				pw.println("<td>" + productName + "</td><td>" + productQuantity + "</td><td>" + priceValue + "</td>");
				pw.println("</tr>");

			}
			pw.println("<tr><td colspan='2'>total Amount</td><td>" + totalPrice + "</td></tr>");
			pw.println("<tr><td colspan='2'>Session ID</td><td>" + session.getId() + "</td></tr>");

			pw.println("/table>");

		} else {

			RequestDispatcher rd = request.getRequestDispatcher("sessionInvalidate.html");
			rd.forward(request, response);

		}
		
		pw.println("<div style='text-align:center;'>");
		pw.println("<a href= 'index.html'>HomePage</a>");
		
		pw.println("</div>");
		pw.println("</body>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
