package com.ozprojects.stockservlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.javacourse.Stock;
import com.ozprojects.stockmodel.Portfolio;
import com.ozprojects.stockservice.StockService;

public class PortfolioServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		resp.setContentType("text/html");
		resp.getWriter().println("<b><u>Exercise 5</u></b><br>");
		StockService portfolioService = new StockService();
		Portfolio portfolio = portfolioService.getPorfolio();
		Stock[] stocks = portfolio.getStocks();
		resp.getWriter().println(portfolio.getHtmlString());
	}
}