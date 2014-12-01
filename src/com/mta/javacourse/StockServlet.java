package com.mta.javacourse;

import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;


public class StockServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	
	resp.setContentType("text/html");
	resp.getWriter().println("<b><u>Exercise 4</u></b>");
	Stock Stock1 = new Stock(("PIH"), (float)12.4, (float)13.1, new Date(114,10,15));
	Stock Stock2 = new Stock(("AAL"), (float)5.5, (float)5.78, new Date(114,10,15));
	Stock Stock3 = new Stock(("CAAS"), (float)31.5, (float)31.2,new Date(114,10,15));
	resp.getWriter().println("<br>");
	resp.getWriter().println(Stock1.getHtmlDescription()+"<br>");
	resp.getWriter().println(Stock2.getHtmlDescription()+"<br>");
	resp.getWriter().println(Stock3.getHtmlDescription()+"<br>");
	
	}
	

}
