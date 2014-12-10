package com.ozprojects.stockmodel;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * An instance of this class represents a portfolio
 * @author Oz Soreq
 * @since 8/12/2014
 * date 8/12/2014
 */
public class Portfolio
{
	public Stock[] stocks;
	private StockStatus[] stocksStatus;
	private int portfolioSize;
	private static final int MAX_PORTFOLIO_SIZE = 5;
	public String title;

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
		
		/**
		 * This constructs a StockStatus with a specified 
		 * symbol, currentBid, currentAsk, date, recommendation, stockQuantity
		 */
		public StockStatus()
		{
			this.symbol = "";
			this.currentBid = 0;
			this.currentAsk = 0;
			this.date = new Date();
			this.recommendation = 0;
			this.stockQuantity = 0;
		}
		
		/**
		 * This is a copy constructor of StockStatus
		 * @param stockStatus - An instance of StockStatus
		 */
		public StockStatus(StockStatus stockStatus)
		{
			this.symbol = stockStatus.symbol;
			this.currentBid = stockStatus.currentBid;
			this.currentAsk = stockStatus.currentAsk;
			this.date = stockStatus.date;
			this.recommendation = stockStatus.recommendation;
			this.stockQuantity = stockStatus.stockQuantity;
		}
		
	}
	
	
	
	/**
	 * This constructs a Portfolio with a specified 
	 * stocks, stocksStatus, portfolioSize, title
	 */
	
	public Portfolio()
	{
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		portfolioSize=0;
		title=("Portfolio#1");
	}
	
	/**
	 * This is a copy constructor of portfolio
	 * @param portfolio - An instance of Portfolio
	 */
	
	public Portfolio(Portfolio portoflio){

		this();
		for (int i = 0; i < portoflio.portfolioSize; i++) {
			this.stocks[i] = new Stock(portoflio.stocks[i]); 
		}
		this.stocksStatus=portoflio.stocksStatus;
		this.title=portoflio.title;
		this.portfolioSize=portoflio.portfolioSize;
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
	/**
	 * This method remove a stock from a Portfolio instance
	 * @param portfolio - An instance of Portfolio
	 */
	public void removeStock(Portfolio portfolio)
	{
		for (int i=0 ; i <= portfolio.portfolioSize ; i++)
		{
			portfolio.stocks[i] = portfolio.stocks[i+1];
		}
		portfolio.portfolioSize--;
	}
}       


