package com.ozprojects.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ozprojects.model.Portfolio;
import com.ozprojects.model.Stock;
import com.ozprojects.service.PortfolioService;
/**
 * This class outputs to Http all required outcomes
 * from related classes and methods
 * @author Oz Soreq
 * @since 8/12/2014
 * date 8/12/2014
 */
public class PortfolioServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		resp.setContentType("text/html");
		resp.getWriter().println("<b><u><h1>Exercise 7</h1></u></b><br>");
		PortfolioService portfolioService = new PortfolioService();
		Portfolio portfolio1 = portfolioService.getPorfolio();
		Stock[] stocks = portfolio1.getStocks();
		Portfolio portfolio2 = new Portfolio(portfolio1); // Using the copy c'tor
		portfolio2.title = "Copy Portfolio";
		
		resp.getWriter().println(portfolio1.getHtmlString());
		
		
		
	}
}
