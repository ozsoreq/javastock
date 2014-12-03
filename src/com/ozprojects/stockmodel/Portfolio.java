package com.ozprojects.stockmodel;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.javacourse.Stock;

public class Portfolio
{
	private Stock[] stocks;
	private StockStatus[] stocksStatus;
	private int portfolioSize;
	private static final int MAX_PORTFOLIO_SIZE = 5;
	private String title;
	
	public class StockStatus
	{
		private String symbol;
		private float currentBid;
		private float currentAsk;
		private Date date;
		private int recommendation;
		private int stockQuantity;
		private static final int DO_NOTHING = 0;
		private static final int BUY = 1;
		private static final int SELL = 2;
	}

	public Portfolio()
	{
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		portfolioSize=0;
		title=("");
	}
	public void addStock(Stock recievedStock)
	{
		stocks[portfolioSize] = recievedStock;
		portfolioSize++;
	}
	public Stock[] getStocks()
	{
		return stocks;
	}
	public String getHtmlString(){
		 String HtmlString= new String("<h1>"+title+"</h1>");
		 for (int i = 0; i < portfolioSize; i++) { 
			 HtmlString=HtmlString+(stocks[i].getHtmlDescription()+"<br>");
		 }
		 
		 return HtmlString;
	 }
	
}       
	

