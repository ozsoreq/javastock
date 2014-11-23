package com.mta.javacourse;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Stock extends HttpServlet{
public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		resp.setContentType("text/html");
		resp.getWriter().println("<b><u>Exercise 4</u></b>");
		StockServlet Stock1 = new StockServlet(("PIH"), (float)12.4, (float)13.1);
		StockServlet Stock2 = new StockServlet(("AAL"), (float)5.5, (float)5.78);
		StockServlet Stock3 = new StockServlet(("CAAS"), (float)31.5, (float)31.2);
		resp.getWriter().println("<br>");
		resp.getWriter().println(Stock1.getHtmlDescription()+"<br>");
		resp.getWriter().println(Stock2.getHtmlDescription()+"<br>");
		resp.getWriter().println(Stock3.getHtmlDescription()+"<br>");
		
	}
}
