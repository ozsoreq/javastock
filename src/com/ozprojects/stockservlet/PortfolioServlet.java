package com.ozprojects.stockservlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ozprojects.stockmodel.Portfolio;
import com.ozprojects.stockmodel.Stock;
import com.ozprojects.stockservice.StockService;
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
		resp.getWriter().println("<b><u>Exercise 5</u></b><br>");
		StockService portfolioService = new StockService();
		Portfolio portfolio1 = portfolioService.getPorfolio();
		Stock[] stocks = portfolio1.getStocks();
		Portfolio portfolio2 = new Portfolio(portfolio1); // Using the copy c'tor
		portfolio2.title = "Portfolio#2";
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		portfolio1.removeStock(portfolio1);
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		portfolio2.stocks[2].setBid((float) 55.55);
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		
		
		
	}
}
